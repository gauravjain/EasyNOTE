

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Locale;

import javax.swing.ButtonModel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.*;

/**
 *
 * @author Raghu
 */
public class FindDialog extends javax.swing.JDialog {
    int select_start,select_end,flag;
    public String word,whole;
    /**
     * Creates new form NewJDialog
     */
        int startIndex,lastIndex;
	String inputText="";
    MixMasala frame;
    boolean caseSensitive,direction,scope,wrap;
	private int nextPosn;
	private int findPosn,offset;	
    
    public FindDialog(MixMasala parent, boolean modal,JTextPane p) {
        super(parent, modal);
        frame=parent;
        textPane=p;
        caseSensitive=scope=false;
        direction=wrap=true;
        offset=nextPosn = findPosn = 0;
        initComponents();
        
    }
    
    public FindDialog(KCMixMasala parent, boolean modal,JTextPane p) {
        super(parent, modal);
        //frame=parent;
        textPane=p;
        caseSensitive=scope=false;
        direction=wrap=true;
        offset=nextPosn = findPosn = 0;
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Find/Replace");
        setResizable(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Find:");

        jTextField1.setText("");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Replace with:");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Options"));

        jCheckBox7.setText("Case Sensitive");  
        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });
        

        jCheckBox8.setText("Wrap around");
        jCheckBox8.setSelected(true);
        jCheckBox8.setEnabled(false);
        jCheckBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox7)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox7)
                    .addComponent(jCheckBox8)))
                
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Direction"));

        buttonGroup1.add(jCheckBox3);
        jCheckBox3.setText("Forward");
        jCheckBox3.setSelected(true);
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jCheckBox4);
        jCheckBox4.setText("Backward");
        //jCheckBox4.setEnabled(false);
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox3)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Scope"));

        buttonGroup3.add(jCheckBox5);
        jCheckBox5.setText("All");
        jCheckBox5.setSelected(true);
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        buttonGroup3.add(jCheckBox6);
        jCheckBox6.setText("Selected Text");     
        jCheckBox6.setEnabled(false);
        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox5)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("Find");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });


        jButton2.setText("Replace");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Replace All");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Find/Replace");
        jLabel3.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jTextField2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        pack();
    }// </editor-fold>
    
    /*private String getInput(String input){
    	String in;
    
    	if(scope){ 
    		in=textPane.getSelectedText();
    		offset=textPane.getSelectionStart();
    	}
    	else{ 
    		in=textPane.getText();
    		int caretPos=textPane.getCaretPosition();
    		System.out.println(caretPos);
    		in=in.substring(caretPos-1>0?caretPos-1:0, in.length()-1);
    		offset=textPane.getCaretPosition();
    	}
    	
    	if(!jCheckBox7.isSelected()){ 
    		in=in.toLowerCase();
    		input=input.toLowerCase();    	
    	}
    	 
    	return in+";"+input;
    }*/

    private void jButton1ActionPerformed(ActionEvent evt) {
	word=	jTextField1.getText();
        try{
        whole = textPane.getDocument().getText(0,textPane.getDocument().getLength());
        }catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}        
	//	DoFindText(jTextField1.getText());
        if(!caseSensitive){
            word=jTextField1.getText().toLowerCase(Locale.ENGLISH);
            whole=whole.toLowerCase(Locale.ENGLISH);
            //System.out.println(whole);
            //System.out.println("word length"+word.length());
            //System.out.println(whole.lastIndexOf(word));
            
         }
        if(direction){
        if(!word.equals(textPane.getSelectedText())){
        select_start = whole.indexOf(word);
        //System.out.println(textPane.getText());
        //System.out.println(word);
        //System.out.println(select_start);
        
        if(select_start == -1)
        {
            startIndex = 0;
            JOptionPane.showMessageDialog(null, "Could not find \"" + word+ "\"!");
            return;
        }
        if(select_start == whole.lastIndexOf(word))
        {
            startIndex = 0;
        }        
        select_end = select_start + word.length();
        lastIndex=select_end-1;
        startIndex=select_start+1;
        if(select_start == whole.lastIndexOf(word))
        {
            startIndex = 0;
        } 
        //System.out.println(select_end);
        textPane.select(select_start, select_end);
    }
    else{
        String selection = textPane.getSelectedText();
        try
        {
            selection.equals("");
        }
        catch(NullPointerException e)
        {
            selection = word;
            try
            {
                selection.equals("");
            }
            catch(NullPointerException e2)
            {
                selection = JOptionPane.showInputDialog("Find:");
                word=selection;
            }
        }
        //System.out.println(selection);
        try
        {
            
         select_start = whole.indexOf(selection, startIndex);

            
            select_end = select_start+selection.length();
            textPane.select(select_start, select_end);
            startIndex = select_start+1;
            lastIndex=select_end-1;
            
            
         flag=whole.lastIndexOf(selection);
         //System.out.println("last="+flag);
        
            
            if(select_start == flag) 
            {
                {
                  startIndex = 0;
                }
            }
        }
        catch(NullPointerException e)
        {}
        }
      	}
        // for backward
        else{
        if(!word.equals(textPane.getSelectedText())){
        select_start = whole.lastIndexOf(word);
        //System.out.println(textPane.getText());
        //System.out.println(word);
        //System.out.println(select_start);
        
        if( select_start== -1)
        {
            lastIndex = whole.length();
            JOptionPane.showMessageDialog(null, "Could not find \"" + word+ "\"!");
            return;
        }
        if(select_start == whole.indexOf(word))
        {
            lastIndex = whole.length();
        }        
        select_end = select_start + word.length();
        lastIndex=select_end-1;
        if(select_start == whole.indexOf(word))
        {
            lastIndex = whole.length();
        }  
        
        //System.out.println(select_end);
        textPane.select(select_start, select_end);
    }
    else{
        String selection = word;
                //textPane.getSelectedText();
        try
        {
            selection.equals("");
        }
        catch(NullPointerException e)
        {
            selection = word;
            try
            {
                selection.equals("");
            }
            catch(NullPointerException e2)
            {
                selection = JOptionPane.showInputDialog("Find:");
                word=selection;
            }
        }
        //System.out.println(selection);
        try
        {
            
         select_start = whole.substring(0, lastIndex).lastIndexOf(selection);

            
            select_end = select_start+selection.length();
            textPane.select(select_start, select_end);
            //System.out.println("start="+select_start+"last="+select_end);
            lastIndex = select_end-1;
            startIndex=select_start+1;
            
            
         flag=whole.indexOf(selection);
         //System.out.println("flag="+flag+"start"+select_start);
        
            
            if(select_start == flag) 
            {
                lastIndex=whole.length();
                //System.out.println("word length="+lastIndex);
            }
        }
        catch(NullPointerException e)
        {}
        }
      	}           
     }
    
   
    
    

	private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
		direction=false;
    }                                          

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    	direction=true;
    }                                          

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    	scope=true;
    }                                          

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    	scope=false;
    }                                          

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        
    }                                           

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    //    ReplaceAll(jTextField1.getText(),jTextField2.getText());
    	//frame.getTextArea().selectAll();
        String s = jTextField2.getText();
        textPane.setText(textPane.getText().replaceAll(jTextField1.getText(), jTextField2.getText()));
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
    	//ReplaceOne(jTextField1.getText(),jTextField2.getText());
    	String s = jTextField2.getText();
        textPane.replaceSelection(s);
    }                                        


	private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
            
    }                                          

          
    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    	caseSensitive=(caseSensitive==true)?false:true;;
    }
    
     private void jCheckBox8ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    	wrap=((wrap==true)?false:true);
        System.out.println();
    }
    
 /*   private void ReplaceAll(String find,String text)
    {    	 
    		int nextPosn = 0; 
    		int findOffset =0 ;   		
    		 
    		int findPosn = 0; 
    		
    		String input=txt.getText();
    		
    		if(scope)
    		{
    			input=txt.getSelectedText();
    			findOffset=txt.getSelectionStart();
    		}
    		
    		while (nextPosn >= 0) { 
    		nextPosn = nextIndex( input, find, findPosn, caseSensitive ); 

    		if ( nextPosn >= 0 ) {  
    		
    		txt.grabFocus();    		  
    		txt.replaceRange( text, nextPosn, nextPosn + find.length() + findOffset );
    		txt.setSelectionStart( nextPosn +findOffset ); 
    		txt.setSelectionEnd( nextPosn + text.length() + findOffset);
    		input=input.replaceFirst(find, text);
    		 
    		findPosn = nextPosn + text.length(); 
    		} 
    	} 
    		 
    }
*/

  /*  private void ReplaceOne(String input,String text) {

	
	}

    

    public void DoFindText(String find) { 
    	String inputText="";
    	int findOffset = 0;
    	
    	if(scope){
    		inputText=frame.getTextArea().getSelectedText();
    		findPosn =0;
    		findOffset = frame.getTextArea().getSelectionStart();
    	}
    	else
    	{
    		inputText=frame.getTextArea().getText();    		
    	}
    	
    	if(direction){
    		findPosn =inputText.length();
    		nextPosn=previousIndex( inputText, find, findPosn, jCheckBox7.isSelected() );
    	}
    	else{
    		nextPosn = nextIndex( inputText , find, findPosn, jCheckBox7.isSelected() );
    	}
    	    	
    	if ( nextPosn >= 0 ) { 
    	frame.getTextArea().setSelectionStart( nextPosn + findOffset); 
    	frame.getTextArea().setSelectionEnd( nextPosn + find.length() +findOffset); 
    	if(direction) findPosn = nextPosn - find.length() + 1;
    	else findPosn = nextPosn + find.length() + 1;
    	jLabel3.setText("\""+find +"\" found!");

    	} else { 
    	findPosn = nextPosn;    	
    	jLabel3.setText("\""+find +"\" not found!");
    	
    	try {
			frame.getTextArea().setCaretPosition(frame.getTextArea().getLineStartOffset(frame.getTextArea().getLineCount()-1));
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	frame.getTextArea().setSelectionStart(frame.getTextArea().getCaretPosition());
    	frame.getTextArea().setSelectionEnd(frame.getTextArea().getCaretPosition());
    	
    	} 
    } 
    
    public int previousIndex(String input, String find, int start,boolean caseSensitive) {
    	int textPosn = -1; 
    	if ( input != null && find != null && start < input.length() ) { 
    	if ( caseSensitive == true ) { // indexOf() returns -1 if not found 
    	textPosn = input.lastIndexOf(find, start);
    	} else { 
    	textPosn = input.toLowerCase().lastIndexOf( find.toLowerCase(), 
    	start ); 
    	} 
    	} 
    	return textPosn; 
	}

	public int nextIndex(String input, String find, int start, boolean caseSensitive ) { 
    	int textPosn = -1; 
    	if ( input != null && find != null && start < input.length() ) { 
    	if ( caseSensitive == true ) { // indexOf() returns -1 if not found 
    	textPosn = input.indexOf( find, start );    	
    	} else { 
    	textPosn = input.toLowerCase().indexOf( find.toLowerCase(), 
    	start ); 
    	} 
    	} 
    	return textPosn; 
    	} 
    	  

    /**
     * @param args the command line arguments
     */
    
   
    // Variables declaration - do not modify                     
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration                   
	private JTextPane textPane;

}
