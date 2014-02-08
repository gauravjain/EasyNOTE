import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.theme.DarkStar;


class HelloWorld extends JDialog{
	
	public void SaveFile(String FileName,String Content){
		File f=new File(FileName);
		if(!f.exists()){
			SaveFileAs(FileName,Content);
		}
		
		try {
			FileWriter writer=new FileWriter(f);
			writer.write(Content);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SaveFileAs(String FileName,String Content){
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
		}
		
	}
	
	public String OpenFile(){
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
		}
		
		return sb.toString();
	}
	
	public static void main(String args[]){
		Scanner s=new Scanner(System.in);
		String read = s.nextLine();
		HelloWorld hw=new HelloWorld();
		hw.setVisible(true);
		if(read.equals("save")) hw.SaveFile(s.nextLine(),"Hi");
		else if(read.equals("open")) System.out.println(hw.OpenFile());
		else hw.SaveFileAs(s.nextLine(),"Hi");
		s.close();
	}
}