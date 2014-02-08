import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.html.HTMLDocument;
import javax.swing.undo.UndoManager;


public class Tab extends JPanel{
	JTextPane textPane;
	JScrollPane scrollPane;
	JTextArea lines;
	StatusBar sb;
	String tabName,fileLoc="Untitled";
	String prevSave="";
	UndoManager manager;
	
	public Tab(String tabName){
		textPane=new JTextPane();
		scrollPane=new JScrollPane(textPane);
		manager = new UndoManager();
		textPane.setContentType("text/html");
        textPane.setStyledDocument(new HTMLDocument());
		this.tabName=tabName;
	    this.setBorder(new EmptyBorder(5, 5, 5, 5));
	    this.setLayout(new BorderLayout(0, 0));
	    this.setPreferredSize( new Dimension(500,500) );
	      
	    this.add(scrollPane, BorderLayout.CENTER);
	    sb = new StatusBar();
        add(BorderLayout.SOUTH, sb); 
        
        lines=new JTextArea("1     ");
        lines.setBorder(new EtchedBorder());
        scrollPane.setRowHeaderView(lines);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        lines.setEditable(false);
        lines.setBackground(Color.decode("#E4E5E7"));
        
        textPane.getDocument().addUndoableEditListener(new UndoableEditListener() {

            @Override
            public void undoableEditHappened(UndoableEditEvent e) { 
                manager.addEdit(e.getEdit());
            }
        });
        
        
        textPane.addCaretListener(new CaretListener() {
        	int lineCount,columnNo,totLength,wordcount;
        	
        	public void SetCounters(){
        		
        		try {
					String str=textPane.getDocument().getText(0, textPane.getDocument().getLength());
					System.out.println(str);
					lineCount=1;
					columnNo=1;
                                        totLength=0;
                                        wordcount=-10;
					totLength=textPane.getDocument().getLength();
                                        int caretPosition=textPane.getCaretPosition();
					caretPosition=Math.min(caretPosition, str.length());
					for(int i=0;i<caretPosition;++i){
						if(str.charAt(i)=='\n'){
							++lineCount;
							columnNo=1;
						}
						else{
							++columnNo;
						}
					}
                                        String sen2 = textPane.getText();
                                        //wordcount=new StringTokenizer(sen2).countTokens();
                                        StringTokenizer t1 = new StringTokenizer(sen2);
                                        while(t1.hasMoreTokens())
					{
						wordcount++;
						String word1 = t1.nextToken();

                                        }
                                        
                                        
                                        for(int i=0;i<totLength-1;++i)
                                        {
                                            if(str.charAt(i)=='\n')
                                                wordcount-=4;
                                        }
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        	}
            public void caretUpdate(CaretEvent e) {

             SetCounters();
             sb.setStatus("Word Count:" + wordcount + "          |    Length: " + totLength + "          |    Line: " + lineCount + "    Col: " + columnNo);
            }
          });
        
       
        
        textPane.getDocument().addDocumentListener(new DocumentListener(){
        	
        	
       	
        	
            public String getText(){
				StringBuilder res=new StringBuilder();
				try {
					//caretPosition = textPane.getDocument().getText(0, textPane.getDocument().getLength()).length();
					String str=textPane.getDocument().getText(0, textPane.getDocument().getLength());
					
				//System.out.println(str+" "+str.length());
				res.append("1     ");				
				res.append(System.getProperty("line.separator"));				
				int counter=2;
				for(int i=0;i<str.length();++i){
					if(str.charAt(i)=='\n'){
						res.append(counter);
					    res.append("     ");
					    res.append(System.getProperty("line.separator"));
					    ++counter;
					}
				}
				/*Element root = textPane.getDocument().getDefaultRootElement();
				//System.out.println(root);
				text = "1     " + System.getProperty("line.separator");
				for(int i = 2; i < root.getElementIndex( caretPosition ) + 2; i++){
					text += i + System.getProperty("line.separator");
				}*/
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return res.toString();
			}
			@Override
			public void changedUpdate(DocumentEvent de) {
				lines.setText(getText());
                        }
 
			@Override 
			public void insertUpdate(DocumentEvent de) {
				lines.setText(getText());

			}
 
			@Override
			public void removeUpdate(DocumentEvent de) {
				lines.setText(getText());

			}
 
		});
	}
	
	public void SetName(String tabName){
		this.tabName=tabName;
	}
	public void SetPath(String path){
		this.fileLoc=path;
	}
}

class StatusBar extends JPanel
{
    private JLabel statusLabel;
    public StatusBar()
    {
        setLayout(new BorderLayout(1, 1));
        statusLabel = new JLabel("Ready");
        statusLabel.setBorder(BorderFactory.createLoweredBevelBorder());
        statusLabel.setForeground(Color.black);
        add(BorderLayout.CENTER, statusLabel);
    }
    public void setStatus(String status)
    {
        statusLabel.setHorizontalAlignment(statusLabel.RIGHT);
        if (status.equals(""))
            statusLabel.setText("Ready");
        else
            statusLabel.setText(status);
    }
    public String getStatus()
    {
        return statusLabel.getText();
    }
}