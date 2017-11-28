package shape;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class FontShape extends ShapeSet{
	String str;
	
	public FontShape(Point startPoint,Point endPoint,Color color,String str) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.color = color;
		this.str = str;
	}

	@Override
	public void drawShape(Graphics g) {
		g.setColor(color);
		Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Microsoft YaHei", Font.PLAIN, (int) (Math.abs(startPoint.y-endPoint.y)*0.75));
        g2d.setFont(font);
        g2d.drawString(str, startPoint.x, startPoint.y);;
	}
	
	@Override
	public ArrayList<Shape> getRealShape() {
		ArrayList<Shape> realShape  = new ArrayList<Shape>();
		realShape.clear();
		realShape.add(new Line2D.Double(startPoint.getX(),startPoint.getY(),endPoint.getX(),startPoint.getY()));
		realShape.add(new Line2D.Double(startPoint.getX(),startPoint.getY(),startPoint.getX(),endPoint.getY()));
		realShape.add(new Line2D.Double(endPoint.getX(),startPoint.getY(),endPoint.getX(),endPoint.getY()));
		realShape.add(new Line2D.Double(startPoint.getX(),endPoint.getY(),endPoint.getX(),endPoint.getY()));
		return realShape;
	}

	@Override
	public boolean isHotPoint(Point mousePoint) {
		boolean bl = false;
		ArrayList<Shape> realShape  =	getRealShape();
		for(int i=0;i<realShape.size();i++){
			if(((Line2D.Double)realShape.get(i)).ptSegDist(mousePoint.getX(),mousePoint.getY())<5){
				bl = true;
				break;
			}
		}
		return bl;
	}

	@Override
	public Point[] getHotPoints() {
		Point[] HotPoints = {this.startPoint,new Point(this.startPoint.x,this.endPoint.y),this.endPoint,new Point(this.endPoint.x,this.startPoint.y)};
		return HotPoints;
	}

	@Override
	public ShapeSet catchShape() {
		Rectangle rectangle = new Rectangle(startPoint,endPoint,color);
		return rectangle;
	}
}
