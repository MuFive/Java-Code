package panel;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import command.Command;
import file.Myfilter;
import file.OpenFile;
import file.SaveFile;
import main.MainFrame;
import shape.Line;
import shape.Rectangle;
import shape.Circle;
import shape.FontShape;
import shape.ShapeSet;


public class PaintPanel  extends JPanel implements MouseListener,MouseMotionListener{
	
	private Point startPoint;
	private Point endPoint;
	
	public ArrayList<ShapeSet> current = new ArrayList<ShapeSet>();
	public ArrayList<ArrayList<ShapeSet>> history = new ArrayList<ArrayList<ShapeSet>>();
	
	private String str = null;
	
	private int command = Command.SELECT;
	private Color color = Color.black;
	public int totalIndex = -1;
	private int index = -1;
	private int changeIndex = -1;
	private boolean change = false;
	private boolean isPressed = false;
	
	
	public PaintPanel() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public void undo(){
		if(totalIndex==0){
			totalIndex = -1;
			current.clear();
		}
		if(totalIndex>0){
			totalIndex--;
			current.clear();
			for(int i = 0;i<history.get(totalIndex).size();i++){
				current.add(history.get(totalIndex).get(i));
			}
		}
		changeIndex = -1;
		repaint();
	}
	
	public void redo(){
		if(totalIndex<history.size()-1){
			totalIndex++;
			current.clear();
			for(int i = 0;i<history.get(totalIndex).size();i++){
				current.add((ShapeSet)history.get(totalIndex).get(i));
			}
		}
		changeIndex = -1;
		repaint();
	}
	
	public void paint(Graphics g){
		Dimension size = getSize();
		int width = size.width;
		int height = size.height;
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		
		if(this.command != Command.SELECT){
			for(int i = 0;i<current.size();i++){
				current.get(i).setState(ShapeSet.UNSELECTED);
			}
		}
		ShapeSet htrShape = null;
		for(int i = 0; i<current.size();i++){
			htrShape = current.get(i);
			htrShape.draw(g,true);
		}
		
		
		if(isPressed){
			switch(this.command){
			case Command.LINE :
				new Line(this.startPoint,this.endPoint,this.color).draw(g,true);
				break;
			case Command.RECTANGLE :
				new Rectangle(this.startPoint,this.endPoint,this.color).draw(g,true);
				break;
			case Command.CIRCLE :
				new Circle(this.startPoint,this.endPoint,this.color).draw(g,true);
				break;
			case Command.FONT :
				new FontShape(this.startPoint,this.endPoint,this.color,this.str).draw(g,true);
				break;
			}
			for(int i = 0;i<current.size();i++){
				current.get(i).setState(ShapeSet.UNSELECTED);
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.endPoint = e.getPoint();
		this.repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.command==Command.SELECT){
			this.showHotZoom(e.getPoint());
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.startPoint = e.getPoint();
		this.isPressed = true;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.endPoint = e.getPoint();
		this.isPressed = false;
		switch(this.command){
		case Command.LINE :
			current.add(new Line(this.startPoint,this.endPoint,this.color));
			removeTail();
			history.add(copyCurrent());
			break;
		case Command.RECTANGLE :
			current.add(new Rectangle(this.startPoint,this.endPoint,this.color));
			removeTail();
			history.add(copyCurrent());
			break;
		case Command.CIRCLE :
			current.add(new Circle(this.startPoint,this.endPoint,this.color));
			removeTail();
			history.add(copyCurrent());
			break;
		case Command.FONT :
			current.add(new FontShape(this.startPoint,this.endPoint,this.color,this.str));
			removeTail();
			history.add(copyCurrent());
			break;
		}
		
		totalIndex = history.size()-1;
		this.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.requestFocus();
		if(this.command!=Command.SELECT){
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}else{
			setCursor(Cursor.getDefaultCursor());
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	public void setFontText(String str) {
		this.str = str;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor(){
		return color;
	}


	public void setCommand(int command) {
		this.command = command;
	}
	
	public int getCommand(){
		return command;
	}

	public ArrayList<ShapeSet> getCurrent() {
		return current;
	}

	public void setCurrent(ArrayList<ShapeSet> current) {
		this.current = current;
	}

	public void setHistory(ArrayList<ArrayList<ShapeSet>> history) {
		this.history = history;
	}

	public boolean isRecordNull(){
		boolean bl = false;
		if(this.current.size()==0){
			this.clearRecord();
			bl = true;
		}
		return bl;
	}

	public void clearRecord(){
		this.changeIndex = -1;
		this.current.clear();
		this.history.clear();
	}
	
	//新建
	public void newPaintPanel(){
		if(!this.isRecordNull()){
			int replay =JOptionPane.showConfirmDialog(this, "是否保存文件?");
			switch(replay){
			case JOptionPane.YES_OPTION :
				this.savePaintPanel();
			case JOptionPane.NO_OPTION :
				this.clearRecord();
				this.repaint();
				break;
			case JOptionPane.CANCEL_OPTION :
				break;
			}
		}
	}
	
	//保存
	public void savePaintPanel(){
		
		SaveFile save = new SaveFile(MainFrame.mainFrame,this);
		save.addChoosableFileFilter(new Myfilter());
		save.save();
		repaint();
		
	}
	
	//关闭
	public void exitPaintPanel(){
		if(!this.isRecordNull()){
			int replay =JOptionPane.showConfirmDialog(this, "是否保存文件?");
			switch(replay){
			case JOptionPane.YES_OPTION :
				this.savePaintPanel();
			case JOptionPane.NO_OPTION :
				System.exit(0);
				break;
			case JOptionPane.CANCEL_OPTION :
				break;
			}
		}else{
			System.exit(0);
		}
	}
	
	//打开
	public void openPaintPanel(){
		if(!this.isRecordNull()){
			int replay =JOptionPane.showConfirmDialog(this, "是否保存文件?");
			switch(replay){
			case JOptionPane.YES_OPTION :
				this.savePaintPanel();
			case JOptionPane.NO_OPTION :
				this.clearRecord();
				OpenFile open = new OpenFile(MainFrame.mainFrame,this);
				open.addChoosableFileFilter(new Myfilter());
				try{
					open.open();
				}catch(Exception e){
					e.getStackTrace();
				}
				
				break;
			case JOptionPane.CANCEL_OPTION :
				break;
			}
		}else{
			OpenFile open = new OpenFile(MainFrame.mainFrame,this);
			open.addChoosableFileFilter(new Myfilter());
			try{
				open.open();
			}catch(Exception e){
				e.getStackTrace();
			}
		}
	}
	
	private int getIndex(Point mousePoint){
		for(int i=0;i<current.size();i++){
			if( current.get(i).isHotPoint(mousePoint)){
				this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
				if(index != i){
					index = i;
					change = true; 
				}else{
					change = false;
				}
				return i;
			}
		}
		setCursor(Cursor.getDefaultCursor());
		return -1;
	}
	
	private void showHotZoom(Point mousePoint){
		for(int i = 0;i<current.size();i++){
			current.get(i).setState(ShapeSet.UNSELECTED);
		}
		
		int index = getIndex(mousePoint);
		if(index!=-1){
			current.get(index).setState(ShapeSet.SELECTED);
		}
		changeIndex = index;
		repaint();
	}
	
	private void removeTail(){
		if(totalIndex<history.size()){
			for(int i = totalIndex+1;i<history.size();){
				history.remove(i);
			}
		}
	}

	public ArrayList<ShapeSet> copyCurrent(){
		ArrayList<ShapeSet> al = new ArrayList<ShapeSet>();
		for(int i = 0;i<current.size();i++){
			current.get(i);
			al.add(current.get(i).catchShape());
			al.get(i).setState(ShapeSet.UNSELECTED);
		}
		return al;
	}
	
	//改变大小
		public void changeSize(){
			ShapeSet shape = null;
			if(changeIndex !=-1){
				shape = current.get(changeIndex).catchShape();
				startPoint.x =  startPoint.x +1;
				startPoint.y =  startPoint.y +1;
				shape.setStartPoint(startPoint);
				current.remove(changeIndex);
				current.add(shape);
				removeTail();
				history.add(copyCurrent());
				totalIndex = history.size()-1;
				changeIndex = current.size()-1;
				current.get(changeIndex).setState(ShapeSet.SELECTED);
				repaint();
			}
		}
	
	//改变颜色
	public void changeColor(){
		ShapeSet shape = null;
		if(changeIndex !=-1){
			shape = current.get(changeIndex).catchShape();
			shape.setColor(getColor());
			current.remove(changeIndex);
			current.add(shape);
			removeTail();
			history.add(copyCurrent());
			totalIndex = history.size()-1;
			changeIndex = current.size()-1;
			current.get(changeIndex).setState(ShapeSet.SELECTED);
			repaint();
		}
	}
	
	//删除图形
	public void deleteShape(){
		if(changeIndex>-1){
			current.remove(changeIndex);
			removeTail();
			history.add(copyCurrent());
			changeIndex = -1;
			totalIndex = history.size()-1;
			repaint();
		}
	}
}
