package file;

import java.io.*;
import javax.swing.filechooser.FileFilter;

public class Myfilter extends FileFilter {
	@Override
	public boolean accept(File f) {
		if(f.isDirectory()){
			return true;
		}
		String extension = getExtension(f);
		if(extension !=null){
			if(extension.equals("cad")){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	@Override
	public String getDescription() {
		return "cad";
	}
	public static String getExtension(File f){
		String ext = null;
		String s = f.getName();
		int i= s.lastIndexOf('.');
		if(i>0&&i<s.length()-1){
			ext = s.substring(i+1).toLowerCase();
		}
		return ext;
	}
}
