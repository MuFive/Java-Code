package file;

import java.io.*;
import javax.swing.*;
import main.MainFrame;
import panel.PaintPanel;
import shape.ShapeSet;

public class SaveFile extends JFileChooser{	
	private MainFrame mainFrame;
	private PaintPanel paintPanel;
	
	public SaveFile(MainFrame mainFrame,PaintPanel paintPanel){
		this.mainFrame = mainFrame;
		this.paintPanel = paintPanel;
	}
	public void save(){
		File file = null;			
		int recieve = this.showSaveDialog(mainFrame);
		if(recieve == APPROVE_OPTION){
			file = this.getSelectedFile();
			String fileName=file.getPath();
			if (file!=null&&file.exists()){
				int n = JOptionPane.showConfirmDialog(this, "请选择一项","是否覆盖源文件?",JOptionPane.YES_NO_OPTION);
				if(n==JOptionPane.NO_OPTION){
					return;
				}
			}
			try{
			    FileOutputStream fileOut = null;
			    if(fileName.indexOf(".cad")!=-1){
			    		fileOut = new FileOutputStream(fileName);
			    }else{
			    		fileOut = new FileOutputStream(fileName+".cad");
			    }
			    	ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);  
			    int e=paintPanel.current.size();
			    objectOut.writeInt(e);
			    for(int j=0;j<e;j++){
				    ShapeSet t=paintPanel.current.get(j);
				    objectOut.writeObject(t);
			    }
			    objectOut.close();         
			}catch (IOException e){   
				e.printStackTrace();    
			}  
		}
	}
}

