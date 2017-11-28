package file;

import java.io.*;
import javax.swing.*;
import main.MainFrame;
import panel.PaintPanel;
import shape.ShapeSet;

public class OpenFile extends JFileChooser implements Serializable{
	private MainFrame mainframe;
	private PaintPanel paintPanel;
	FileInputStream in=null;
	ObjectInputStream objectIn=null;
	
	public OpenFile(MainFrame mainframe,PaintPanel paintPanel){
		this.mainframe = mainframe;
		this.paintPanel = paintPanel;
	}
	
	public void open()throws Exception {
		File file = null;			
		int recieve = this.showOpenDialog(mainframe);
		if(recieve == APPROVE_OPTION){
			file = this.getSelectedFile();
			String fileName=file.getPath();
			try{
				paintPanel.current.clear();
			    in=new FileInputStream(fileName);  
			    objectIn= new ObjectInputStream(in);
			    int i=objectIn.readInt();
			    ShapeSet tt; 
			    for(int j=0;j<i;j++){
			    		tt=  (ShapeSet) objectIn.readObject(); 
			    		paintPanel.current.add(tt);
			    }
			    if(i>0){
			    		paintPanel.history.add(paintPanel.copyCurrent());
			    		paintPanel.totalIndex = paintPanel.history.size()-1;
			    }
			    		paintPanel.repaint();
			    		objectIn.close();
			    }         
			catch (Exception e) {   
				JOptionPane.showMessageDialog(this, "无法打开");   
			}    
		}
	}
}
