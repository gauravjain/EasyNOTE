



import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.StyledEditorKit.FontSizeAction;
import javax.swing.text.html.HTMLDocument;
import javax.swing.undo.UndoManager;


import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.languagetool.JLanguageTool;
//import org.languagetool.rules.RuleMatch;

/*
 * Creating and using TabComponentsDemo example  
 */ 
public class KCMixMasala extends JFrame {    

    private final int tabNumber = 5;
    private final JTabbedPane pane = new JTabbedPane();
    private JMenuItem tabComponentsItem;
    private JMenuItem scrollLayoutItem;
    private JMenuItem copy_text, cut_text, paste_text;
	private JButton jButton6;
	private JButton jButton5;
	private JButton jButton11;
	private JButton jButton10;
	private JButton jButton12;
        private JButton jButton13;
        private JButton jButton14;
        private StringSelection data;
    private String buffer_contents;
    private Clipboard universal_buffer;
	private JToolBar jToolBar1;
	private JMenuItem undo_text;
	private JMenuItem redo_text;
	private Tab currentTab;
	int TabCounter;
	
    
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                //Turn off metal's use of bold fonts
	        UIManager.put("swing.boldMetal", Boolean.FALSE);
                new KCMixMasala("Text Editor").runTest();
            }
        });
    }
    
    public KCMixMasala(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0,0));
        TabCounter=0;
        initMenu();        
        universal_buffer = Toolkit.getDefaultToolkit().getSystemClipboard();
        add(pane,BorderLayout.CENTER);     
        
        Thread t=new Thread(new checker());
        t.start();
    }
    
    public void runTest() {
        pane.removeAll();
        /*for (int i = 0; i < tabNumber; i++) {
            String title = "Untitled";
            pane.add(title, new Tab("Untitled"));
            initTabComponent(i);
        }*/
        //tabComponentsItem.setSelected(true);
        pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        //scrollLayoutItem.setSelected(false);
        setSize(new Dimension(600, 600));
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
    private void initTabComponent(int i) {
        pane.setTabComponentAt(i,new ButtonTabComponent(pane));
        //pane.addTab("Untitled", new JTextPane());
    }    

    //Setting menu
    
    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        //create Options menu
       
        JMenu fontMenu = new JMenu("Font");
        JMenu optionsMenu = new JMenu("Options");
        JMenu fileMenu = new JMenu("File");
        
        //optionsMenu.add(tabComponentsItem);
        //optionsMenu.add(scrollLayoutItem);
        //optionsMenu.add(resetItem);
        Action boldAction = new BoldAction();
        boldAction.putValue(Action.NAME, "Bold");
        fontMenu.add(boldAction);

        Action italicAction = new ItalicAction();
        italicAction.putValue(Action.NAME, "Italic");
        fontMenu.add(italicAction);
        
        Action underlineAction = new UnderlineAction();
        underlineAction.putValue(Action.NAME, "Underline");
        fontMenu.add(underlineAction);

        Action foregroundAction = new ForegroundAction();
        foregroundAction.putValue(Action.NAME, "Color");
        fontMenu.add(foregroundAction);

        Action formatTextAction = new FontAndSizeAction();
        formatTextAction.putValue(Action.NAME, "Font and Size");
        fontMenu.add(formatTextAction);
        
        JMenuItem newTab= new JMenuItem("New Tab");
        newTab.addActionListener(new TabListener());
        fileMenu.add(newTab);
        
        JMenuItem open= new JMenuItem("Open");
        open.addActionListener(new FileListener());
        fileMenu.add(open);
        
        JMenuItem save= new JMenuItem("Save");
        save.addActionListener(new FileListener());
        fileMenu.add(save);
        
        JMenuItem saveAs= new JMenuItem("Save As");
        saveAs.addActionListener(new FileListener());
        fileMenu.add(saveAs);
        
        JMenuItem closeTab= new JMenuItem("Close Tab");
        closeTab.addActionListener(new TabListener());
        fileMenu.add(closeTab);        

        JMenuItem findReplace= new JMenuItem("Find/Replace");
        findReplace.addActionListener(new FindReplaceListener());
        optionsMenu.add(findReplace);
        
        JMenuItem spellCheck= new JMenuItem("Spell Check");
        spellCheck.addActionListener(new SpellCheckListener());
        optionsMenu.add(spellCheck);
        
        
        copy_text = new JMenuItem("Copy");
        cut_text = new JMenuItem("Cut");
        paste_text = new JMenuItem("Paste");
        undo_text = new JMenuItem("Undo");
        redo_text = new JMenuItem("Redo");
        
        optionsMenu.add(cut_text);
        optionsMenu.add(copy_text);
        optionsMenu.add(paste_text);
        optionsMenu.add(undo_text);
        optionsMenu.add(redo_text);
        
        undo_text.addActionListener(new UndoListener());
        redo_text.addActionListener(new UndoListener());
        cut_text.addActionListener(new MenuListener());
        copy_text.addActionListener(new MenuListener());
        paste_text.addActionListener(new MenuListener());
        
        copy_text.setAccelerator(KeyStroke.getKeyStroke('C', Toolkit.getDefaultToolkit(  ).getMenuShortcutKeyMask(  ), false));
        cut_text.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit(  ).getMenuShortcutKeyMask(  ), false));
        paste_text.setAccelerator(KeyStroke.getKeyStroke('P', Toolkit.getDefaultToolkit(  ).getMenuShortcutKeyMask(  ), false));
        undo_text.setAccelerator(KeyStroke.getKeyStroke('Z', Toolkit.getDefaultToolkit(  ).getMenuShortcutKeyMask(  ), false));
        redo_text.setAccelerator(KeyStroke.getKeyStroke('Y', Toolkit.getDefaultToolkit(  ).getMenuShortcutKeyMask(  ), false));
        open.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit(  ).getMenuShortcutKeyMask(  ), false));
        save.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit(  ).getMenuShortcutKeyMask(  ), false));

        initToolBar();
  
        menuBar.add(fileMenu);
        menuBar.add(optionsMenu);
        menuBar.add(fontMenu);
        
        
        setJMenuBar(menuBar);
    }
    
    
    public void initToolBar(){

       
        jToolBar1 = new javax.swing.JToolBar();
   
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        
        jToolBar1.setRollover(true);
        jToolBar1.setFloatable(false);

        jButton10.setIcon(new javax.swing.ImageIcon("open.png")); // NOI18N
        jToolBar1.add(jButton10);

        jButton12.setIcon(new javax.swing.ImageIcon("save.png")); // NOI18N
        jToolBar1.add(jButton12);

        jButton6.setIcon(new javax.swing.ImageIcon("cut.png")); // NOI18N
        jToolBar1.add(jButton6);

        jButton5.setIcon(new javax.swing.ImageIcon("copy.png")); // NOI18N
        jToolBar1.add(jButton5);

        jButton11.setIcon(new javax.swing.ImageIcon("paste.png")); // NOI18N
        jToolBar1.add(jButton11);
        
        jButton13.setIcon(new javax.swing.ImageIcon("undo.png")); // NOI18N
        jToolBar1.add(jButton13);
        
        jButton14.setIcon(new javax.swing.ImageIcon("redo.png")); // NOI18N
        jToolBar1.add(jButton14);
        
        add(jToolBar1,BorderLayout.PAGE_START);
        pack();
        
        ToolbarButtonListener b6 = new ToolbarButtonListener();
        
        jButton6.addActionListener(b6);
        jButton5.addActionListener(b6);
        jButton11.addActionListener(b6);
        jButton10.addActionListener(b6);
        jButton12.addActionListener(b6);
        jButton13.addActionListener(b6);
        jButton14.addActionListener(b6);
    }

    private class checker implements Runnable {

        public checker() {
        }

        @Override
        public void run() {
            while(true){
            KeyboardFocusManager x = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			if ( x.getPermanentFocusOwner() != null && x.getPermanentFocusOwner().getClass().toString().equals("class javax.swing.JTextPane") ) {
				JTextPane p=(JTextPane)x.getPermanentFocusOwner();
				currentTab = (Tab) p.getParent().getParent().getParent();
                                
                        }
            }
        }         
    }
    /*
    class TabKeyListener implements KeyListener {

    public TabKeyListener() {
    }

    @Override
    public void keyTyped(KeyEvent e) {
                System.out.println("Here");
			KeyboardFocusManager x = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			if ( x.getPermanentFocusOwner() != null && x.getPermanentFocusOwner().getClass().toString().equals("class javax.swing.JTextPane") ) {
				JTextPane p=(JTextPane)x.getPermanentFocusOwner();
				currentTab = (Tab) p.getParent().getParent().getParent();
                                
                        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        KeyboardFocusManager x = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			if ( x.getPermanentFocusOwner() != null && x.getPermanentFocusOwner().getClass().toString().equals("class javax.swing.JTextPane") ) {
				JTextPane p=(JTextPane)x.getPermanentFocusOwner();
				currentTab = (Tab) p.getParent().getParent().getParent();
                                
                        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
       
        KeyboardFocusManager x = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			if ( x.getPermanentFocusOwner() != null && x.getPermanentFocusOwner().getClass().toString().equals("class javax.swing.JTextPane") ) {
				JTextPane p=(JTextPane)x.getPermanentFocusOwner();
				currentTab = (Tab) p.getParent().getParent().getParent();
                                
                        }
    }
    
}*/
    
    public class TabListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(arg0.getActionCommand().equals("New Tab")){
				pane.add("Untitled", new Tab("Untitled"));
	            initTabComponent(TabCounter);
	            ++TabCounter;
	            System.out.println(TabCounter);
			}
		}
    	
    }
    
    public class UndoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			UndoManager manager;
			KeyboardFocusManager x = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			if ( x.getPermanentFocusOwner() != null && x.getPermanentFocusOwner().getClass().toString().equals("class javax.swing.JTextPane") ) {
				JTextPane p=(JTextPane)x.getPermanentFocusOwner();
				Tab t = (Tab) p.getParent().getParent().getParent();
				manager=t.manager;
				if(arg0.getActionCommand().equals("Undo")){
					if(manager.canUndo())
						manager.undo();					
					
				}
				else{
					if(manager.canRedo())
						manager.redo();									
				}
			}
			
			
		}
    	
    }
    
    public class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
        	JTextPane jTextPane1=null;
        	KeyboardFocusManager x = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			if ( x.getPermanentFocusOwner() != null && x.getPermanentFocusOwner().getClass().toString().equals("class javax.swing.JTextPane") ) {
				jTextPane1=(JTextPane)x.getPermanentFocusOwner();
			}
			else{
				return;
			}
			
        JButton input_click = (JButton)e.getSource();
        StringSelection data;
		if(input_click == jButton6)
        {
            //CUT
            buffer_contents = jTextPane1.getSelectedText();
            data = new StringSelection(buffer_contents);
            universal_buffer.setContents(data, data);
            jTextPane1.replaceSelection("");
        }
        else if(input_click == jButton5)
        {
            //COPY
            buffer_contents = jTextPane1.getSelectedText();
            data = new StringSelection(buffer_contents);
            universal_buffer.setContents(data, data);
        }
        else if(input_click == jButton11)
        {
            //PASTE
            try {
                buffer_contents = (String) universal_buffer.getContents(null).getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            jTextPane1.replaceSelection(buffer_contents);
        }


       }
    }
    
    class FileListener implements ActionListener{
    	
    	Tab t;

    	public void SaveFile(String TabName,String Content){
    		String FileName=t.fileLoc;
			File f=new File(FileName);
			System.out.println(FileName);
			if(TabName.equals("Untitled"))
				SaveFileAs(Content);
			else{
				try {
					FileWriter writer=new FileWriter(f);
					writer.write(Content);
					writer.write(Content);
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	public void SaveFileAs(String Content){
			File f=null;
			JFileChooser chooser=new JFileChooser();
			JFrame jf=new JFrame();
			/*try {
				Plastic3DLookAndFeel.setCurrentTheme(new DarkStar());
				UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
				
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			if(chooser.showSaveDialog(jf)== JFileChooser.APPROVE_OPTION){
				f=chooser.getSelectedFile();
				try {
					FileWriter writer=new FileWriter(f);
					writer.write(Content);
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(t.tabName.equals("Untitled")){ 
					t.tabName=f.getName();
					t.fileLoc=f.getAbsolutePath();
					t.prevSave=Content;
				}
			}
    	}
			
		public void OpenFile(){
			StringBuilder sb=new StringBuilder();
			File f=null;
			JFileChooser chooser=new JFileChooser();
			JFrame jf=new JFrame();
			
			if(chooser.showOpenDialog(jf)== JFileChooser.APPROVE_OPTION){
				f=chooser.getSelectedFile();
				try {
					BufferedReader reader=new BufferedReader(new FileReader(f));
					String rd;
					while((rd=reader.readLine())!=null){
						sb.append(rd);
						sb.append("\n");
					}
										
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String title=f.getName();
				Tab t=new Tab(title);
				pane.add(title, t);
	            initTabComponent(pane.getTabCount()-1);
				String str=sb.toString();
				str=str.trim();
	            t.textPane.setText(str);
	            ++TabCounter;
	   /*         try {
	            	
	  /         	System.out.println(t.textPane.getText());
	            	//String str=t.textPane.getDocument().getText(0, t.textPane.getDocument().getLength());
	            	System.out.println(t.textPane.getText().charAt(t.textPane.getText().length()-1));
					System.out.println(t.textPane.getDocument().getText(0, t.textPane.getDocument().getLength()));
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
	            t.fileLoc=f.getAbsolutePath();
			}
			
			
		}
		
	
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(arg0.getActionCommand().equals("Open")){
				OpenFile();
				return;
			}
			KeyboardFocusManager x = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			if ( x.getPermanentFocusOwner() != null && x.getPermanentFocusOwner().getClass().toString().equals("class javax.swing.JTextPane") ) {
				JTextPane p=(JTextPane)x.getPermanentFocusOwner();
				t = (Tab) p.getParent().getParent().getParent();
			
/*				try {
					String content = (p.getDocument().getText(0, p.getDocument().getLength()) );
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				if(arg0.getActionCommand().equals("Save")){
					SaveFile(t.tabName,p.getText());
					p.setText(p.getText());
					try {
						System.out.println(p.getDocument().getText(0, p.getDocument().getLength()));
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else{ 
					SaveFileAs(p.getText().trim());				
				}
			}
		}
    	
    }
    
    class FindReplaceListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			KeyboardFocusManager x = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			if ( x.getPermanentFocusOwner() != null && x.getPermanentFocusOwner().getClass().toString().equals("class javax.swing.JTextPane") ) {
				JTextPane p=(JTextPane)x.getPermanentFocusOwner();
				FindDialog diag=new FindDialog(new KCMixMasala("Nothing"),false,p);
				diag.setVisible(true);
				
			}
		}
    	
    }
    
    class SpellCheckListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			KeyboardFocusManager x = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			
			if ( x.getPermanentFocusOwner() != null  && x.getPermanentFocusOwner().getClass().toString().equals("class javax.swing.JTextPane")) {
					JTextPane p=(JTextPane)x.getPermanentFocusOwner();
					/*
					try {
					JLanguageTool langTool = new JLanguageTool(new BritishEnglish());
					   langTool.activateDefaultPatternRules();
					   String str=p.getDocument().getText(0, p.getDocument().getLength());
					   StringBuilder res=new StringBuilder();
					   List<RuleMatch> matches = langTool.check(str);
					   for (RuleMatch match : matches) {
					           System.out.println("Potential error at line " +
					              match.getEndLine() + ", column " +
					              match.getColumn() + ": " + match.getMessage());
					           
					           System.out.println("Suggested correction: " +
					              match.getSuggestedReplacements());
					           res.append("Potential error at line ");
					           res.append(match.getEndLine());
					           res.append(",column ");
					           res.append(match.getColumn());
					           res.append(": ");
					           res.append(match.getMessage());
					           res.append("-->");					           
					           res.append(match.getSuggestedReplacements().toString().equals("[]")?"":match.getSuggestedReplacements());
					           res.append("\n");
						   //System.out.println(match.getSuggestedReplacements());
					       
						   System.out.println(str.substring(match.getColumn()-1, match.getEndColumn()-1));
					          
					    }
					   JOptionPane.showMessageDialog(null,
				    	          res.toString(), "Spell Check Result",
				    	          JOptionPane.INFORMATION_MESSAGE);

				} catch (IOException | BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

					*/
			}
			else{
				JOptionPane.showMessageDialog(null,
		    	          "You need to select the editor pane before you can spell check.", "Error",
		    	          JOptionPane.ERROR_MESSAGE);
			}
		}
    	
    }
    
    class BoldAction extends StyledEditorKit.StyledTextAction {
    	  private static final long serialVersionUID = 9174670038684056758L;

    	  public BoldAction() {
    	    super("font-bold");
    	  }

    	  public String toString() {
    	    return "Bold";
    	  }

    	  public void actionPerformed(ActionEvent e) {
    	    JEditorPane editor = getEditor(e);
    	    if (editor != null) {
    	      StyledEditorKit kit = getStyledEditorKit(editor);
    	      MutableAttributeSet attr = kit.getInputAttributes();
    	      boolean bold = (StyleConstants.isBold(attr)) ? false : true;
    	      SimpleAttributeSet sas = new SimpleAttributeSet();
    	      StyleConstants.setBold(sas, bold);
    	      setCharacterAttributes(editor, sas, false);
    	      
    	    }
    	  }
    	}

    	class UnderlineAction extends StyledEditorKit.StyledTextAction {
    	  private static final long serialVersionUID = 9174670038684056758L;

    	  public UnderlineAction() {
    	    super("font-underline");
    	  }

    	  public String toString() {
    	    return "Underline";
    	  }

    	  public void actionPerformed(ActionEvent e) {
    	    JEditorPane editor = getEditor(e);
    	    if (editor != null) {
    	      StyledEditorKit kit = getStyledEditorKit(editor);
    	      MutableAttributeSet attr = kit.getInputAttributes();
    	      boolean underline = (StyleConstants.isUnderline(attr)) ? false : true;
    	      SimpleAttributeSet sas = new SimpleAttributeSet();
    	      StyleConstants.setUnderline(sas, underline);
    	      setCharacterAttributes(editor, sas, false);

    	    }
    	  }
    	}

    	class ItalicAction extends StyledEditorKit.StyledTextAction {

    	  private static final long serialVersionUID = -1428340091100055456L;

    	  public ItalicAction() {
    	    super("font-italic");
    	  }

    	  public String toString() {
    	    return "Italic";
    	  }

    	  public void actionPerformed(ActionEvent e) {
    	    JEditorPane editor = getEditor(e);
    	    if (editor != null) {
    	      StyledEditorKit kit = getStyledEditorKit(editor);
    	      MutableAttributeSet attr = kit.getInputAttributes();
    	      boolean italic = (StyleConstants.isItalic(attr)) ? false : true;
    	      SimpleAttributeSet sas = new SimpleAttributeSet();
    	      StyleConstants.setItalic(sas, italic);
    	      setCharacterAttributes(editor, sas, false);
    	    }
    	  }
    	}
    	class ForegroundAction extends StyledEditorKit.StyledTextAction {

    	  private static final long serialVersionUID = 6384632651737400352L;

    	  JColorChooser colorChooser = new JColorChooser();

    	  JDialog dialog = new JDialog();

    	  boolean noChange = false;

    	  boolean cancelled = false;

    	  public ForegroundAction() {
    	    super("foreground");

    	  }

    	  public void actionPerformed(ActionEvent e) {
    	    JTextPane editor = (JTextPane) getEditor(e);

    	    if (editor == null) {
    	      JOptionPane.showMessageDialog(null,
    	          "You need to select the editor pane before you can change the color.", "Error",
    	          JOptionPane.ERROR_MESSAGE);
    	      return;
    	    }
    	    int p0 = editor.getSelectionStart();
    	    StyledDocument doc = getStyledDocument(editor);
    	    Element paragraph = doc.getCharacterElement(p0);
    	    AttributeSet as = paragraph.getAttributes();
    	    fg = StyleConstants.getForeground(as);
    	    if (fg == null) {
    	      fg = Color.BLACK;
    	    }
    	    colorChooser.setColor(fg);

    	    JButton accept = new JButton("OK");
    	    accept.addActionListener(new ActionListener() {
    	      public void actionPerformed(ActionEvent ae) {
    	        fg = colorChooser.getColor();
    	        dialog.dispose();
    	      }
    	    });

    	    JButton cancel = new JButton("Cancel");
    	    cancel.addActionListener(new ActionListener() {
    	      public void actionPerformed(ActionEvent ae) {
    	        cancelled = true;
    	        dialog.dispose();
    	      }
    	    });

    	    JButton none = new JButton("None");
    	    none.addActionListener(new ActionListener() {
    	      public void actionPerformed(ActionEvent ae) {
    	        noChange = true;
    	        dialog.dispose();
    	      }
    	    });

    	    JPanel buttons = new JPanel();
    	    buttons.add(accept);
    	    buttons.add(none);
    	    buttons.add(cancel);

    	    dialog.getContentPane().setLayout(new BorderLayout());
    	    dialog.getContentPane().add(colorChooser, BorderLayout.CENTER);
    	    dialog.getContentPane().add(buttons, BorderLayout.SOUTH);
    	    dialog.setModal(true);
    	    dialog.pack();
    	    dialog.setVisible(true);

    	    if (!cancelled) {

    	      MutableAttributeSet attr = null;
    	      if (editor != null) {
    	        if (fg != null && !noChange) {
    	          attr = new SimpleAttributeSet();
    	          StyleConstants.setForeground(attr, fg);
    	          setCharacterAttributes(editor, attr, false);
    	        }
    	      }
    	    }// end if color != null
    	    noChange = false;
    	    cancelled = false;
    	  }

    	  private Color fg;
    	}

    	class FontAndSizeAction extends StyledEditorKit.StyledTextAction {

    	  private static final long serialVersionUID = 584531387732416339L;

    	  private String family;

    	  private float fontSize;

    	  JDialog formatText;

    	  private boolean accept = false;

    	  JComboBox fontFamilyChooser;

    	  JComboBox fontSizeChooser;

    	  public FontAndSizeAction() {
    	    super("Font and Size");
    	  }

    	  public String toString() {
    	    return "Font and Size";
    	  }

    	  public void actionPerformed(ActionEvent e) {
    	    JTextPane editor = (JTextPane) getEditor(e);
    	    int p0 = editor.getSelectionStart();
    	    StyledDocument doc = getStyledDocument(editor);
    	    Element paragraph = doc.getCharacterElement(p0);
    	    AttributeSet as = paragraph.getAttributes();

    	    family = StyleConstants.getFontFamily(as);
    	    fontSize = StyleConstants.getFontSize(as);

    	    formatText = new JDialog(new JFrame(), "Font and Size", true);
    	    formatText.getContentPane().setLayout(new BorderLayout());

    	    JPanel choosers = new JPanel();
    	    choosers.setLayout(new GridLayout(2, 1));

    	    JPanel fontFamilyPanel = new JPanel();
    	    fontFamilyPanel.add(new JLabel("Font"));

    	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	    String[] fontNames = ge.getAvailableFontFamilyNames();

    	    fontFamilyChooser = new JComboBox();
    	    for (int i = 0; i < fontNames.length; i++) {
    	      fontFamilyChooser.addItem(fontNames[i]);
    	    }
    	    fontFamilyChooser.setSelectedItem(family);
    	    fontFamilyPanel.add(fontFamilyChooser);
    	    choosers.add(fontFamilyPanel);

    	    JPanel fontSizePanel = new JPanel();
    	    fontSizePanel.add(new JLabel("Size"));
    	    fontSizeChooser = new JComboBox();
    	    fontSizeChooser.setEditable(true);
    	    fontSizeChooser.addItem(new Float(4));
    	    fontSizeChooser.addItem(new Float(8));
    	    fontSizeChooser.addItem(new Float(12));
    	    fontSizeChooser.addItem(new Float(16));
    	    fontSizeChooser.addItem(new Float(20));
    	    fontSizeChooser.addItem(new Float(24));
    	    fontSizeChooser.setSelectedItem(new Float(fontSize));
    	    fontSizePanel.add(fontSizeChooser);
    	    choosers.add(fontSizePanel);

    	    JButton ok = new JButton("OK");
    	    ok.addActionListener(new ActionListener() {
    	      public void actionPerformed(ActionEvent ae) {
    	        accept = true;
    	        formatText.dispose();
    	        family = (String) fontFamilyChooser.getSelectedItem();
    	        fontSize = Float.parseFloat(fontSizeChooser.getSelectedItem().toString());
    	      }
    	    });

    	    JButton cancel = new JButton("Cancel");
    	    cancel.addActionListener(new ActionListener() {
    	      public void actionPerformed(ActionEvent ae) {
    	        formatText.dispose();
    	      }
    	    });

    	    JPanel buttons = new JPanel();
    	    buttons.add(ok);
    	    buttons.add(cancel);
    	    formatText.getContentPane().add(choosers, BorderLayout.CENTER);
    	    formatText.getContentPane().add(buttons, BorderLayout.SOUTH);
    	    formatText.pack();
    	    formatText.setVisible(true);

    	    MutableAttributeSet attr = null;
    	    if (editor != null && accept) {
    	      attr = new SimpleAttributeSet();
    	      StyleConstants.setFontFamily(attr, family);
    	      StyleConstants.setFontSize(attr, (int) fontSize);
    	      setCharacterAttributes(editor, attr, false);
    	    }

    	  }
    	 
    	}
        
        public class MenuListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            KeyboardFocusManager x = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			
			if ( x.getPermanentFocusOwner() != null  && x.getPermanentFocusOwner().getClass().toString().equals("class javax.swing.JTextPane")) {
					JTextPane p=(JTextPane)x.getPermanentFocusOwner();
                        
            JMenuItem input = (JMenuItem)e.getSource();
            if (input == copy_text)
            {
                buffer_contents = p.getSelectedText();
                data = new StringSelection(buffer_contents);
                universal_buffer.setContents(data, data);
            }
            else if(input == cut_text)
            {
                    buffer_contents = p.getSelectedText();
                    data = new StringSelection(buffer_contents);
                    universal_buffer.setContents(data, data);
                    p.replaceSelection("");
            }
            else if(input == paste_text)
            {
                try {
                buffer_contents = (String) universal_buffer.getContents(null).getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            p.replaceSelection(buffer_contents);
            }
                        }
            }
        }
        
    public class ToolbarButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
               Tab t;
               JTextPane p;
             KeyboardFocusManager x = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        //     System.out.println(x);
	System.out.print(currentTab.tabName);		
			
            
				t = currentTab;
                                p = currentTab.textPane;
        JButton input_click = (JButton)e.getSource();
        if(input_click == jButton6)
        {
            //CUT
            buffer_contents = p.getSelectedText();
            data = new StringSelection(buffer_contents);
            universal_buffer.setContents(data, data);
            p.replaceSelection("");
        }
        else if(input_click == jButton5)
        {
            //COPY
            buffer_contents = p.getSelectedText();
            data = new StringSelection(buffer_contents);
            universal_buffer.setContents(data, data);
        }
        else if(input_click == jButton11)
        {
            //PASTE
            try {
                buffer_contents = (String) universal_buffer.getContents(null).getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            p.replaceSelection(buffer_contents);
        }
        else if (input_click == jButton12)
        {
            //SAVE
            String TabName, Content;
            TabName = t.tabName;
            Content = p.getText();
            String FileName=t.fileLoc;
			File f=new File(FileName);
			System.out.println(FileName);
			if(TabName.equals("Untitled"))
                        {
                            //SAVE AS
                            
			JFileChooser chooser=new JFileChooser();
			JFrame jf=new JFrame();
			/*try {
				Plastic3DLookAndFeel.setCurrentTheme(new DarkStar());
				UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
				
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			if(chooser.showSaveDialog(jf)== JFileChooser.APPROVE_OPTION){
				f=chooser.getSelectedFile();
				try {
					FileWriter writer=new FileWriter(f);
					writer.write(Content);
					writer.close();
				} catch (IOException z) {
					// TODO Auto-generated catch block
					z.printStackTrace();
				}
				
				if(t.tabName.equals("Untitled")){ 
					t.tabName=f.getName();
					t.fileLoc=f.getAbsolutePath();
					t.prevSave=Content;
				}
			}
                        }
			else{
				try {
					FileWriter writer=new FileWriter(f);
					writer.write(Content);
					writer.write(Content);
					writer.close();
				} catch (IOException z) {
					// TODO Auto-generated catch block
					z.printStackTrace();
				}
			}
        }
        else if(input_click == jButton10)
        {
            //OPEN
             
			StringBuilder sb=new StringBuilder();
			File f=null;
			JFileChooser chooser=new JFileChooser();
			JFrame jf=new JFrame();
			
			if(chooser.showOpenDialog(jf)== JFileChooser.APPROVE_OPTION){
				f=chooser.getSelectedFile();
				try {
					BufferedReader reader=new BufferedReader(new FileReader(f));
					String rd;
					while((rd=reader.readLine())!=null){
						sb.append(rd);
						sb.append("\n");
					}
										
					reader.close();
				} catch (IOException z) {
					// TODO Auto-generated catch block
					z.printStackTrace();
				}
				String title=f.getName();
				t=new Tab(title);
				pane.add(title, t);
	            initTabComponent(pane.getTabCount()-1);
				String str=sb.toString();
				str=str.trim();
	            t.textPane.setText(str);
	            ++TabCounter;
	   /*         try {
	            	
	  /         	System.out.println(t.textPane.getText());
	            	//String str=t.textPane.getDocument().getText(0, t.textPane.getDocument().getLength());
	            	System.out.println(t.textPane.getText().charAt(t.textPane.getText().length()-1));
					System.out.println(t.textPane.getDocument().getText(0, t.textPane.getDocument().getLength()));
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
	            t.fileLoc=f.getAbsolutePath();
			}
			
        }
        else if(input_click == jButton13)
        {
            UndoManager manager;
            manager=t.manager;
            if(manager.canUndo())
            {
                manager.undo();
            }
        }
        else if(input_click == jButton14)
        {
            UndoManager manager;
            manager=t.manager;
            if(manager.canRedo())
            {
                manager.redo();
            }
        }
        }
       
    }
}
