import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.RuleMatch;

public class Tabs extends JFrame{
	
	final Component someComponent = new JScrollPane(); //Whatever component is being added
	final JTabbedPane tabbedPane = new JTabbedPane();
	//I had my own subclass of AbstractButton, but that's irrelevant in this case
	JButton closeButton = new JButton("X");

	/*
	 * titlePanel is initialized containing a JLabel with the tab title, 
	 * and closeButton. (I don't recall the tabbed pane showing a title itself after 
	 * setTabComponentAt() is called)
	 */
	
	public Tabs(){
		
		setSize(600,600);
		
		add(tabbedPane);
	
		JPanel titlePanel = new JPanel();
		titlePanel.add(new JLabel("Hello"));
		titlePanel.add(closeButton);
		tabbedPane.add(someComponent);
		tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(someComponent), titlePanel);

		closeButton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        tabbedPane.remove(someComponent);
		    } 
		    
		 });

	}
	
	public static void main(String args[]){
		//Tabs t=new Tabs();
//		/t.setVisible(true);
		
		try {
			JLanguageTool langTool = new JLanguageTool(new BritishEnglish());
			   langTool.activateDefaultPatternRules();
			   String str="A sentence " + 
				       "with a error in the Hitchhiker's Guide tot he Galaxy";
			   List<RuleMatch> matches = langTool.check(str);
			   for (RuleMatch match : matches) {
			           /*System.out.println("Potential error at line " +
			              match.getEndLine() + ", column " +
			              match.getColumn() + ": " + match.getMessage());
			           
			           System.out.println("Suggested correction: " +
			              match.getSuggestedReplacements());*/
				   System.out.println(match.getSuggestedReplacements());
				   System.out.println(str.substring(match.getColumn()-1, match.getEndColumn()-1));
			    }

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			}
	
}
