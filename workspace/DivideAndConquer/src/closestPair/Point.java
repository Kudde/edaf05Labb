package closestPair;

import java.util.Comparator;

public class Point {
	private double x;
	private double y;

	public Point(Double x, Double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public static Comparator<Point> getCompByX()
	{   
	 Comparator<Point> comp = new Comparator<Point>(){
	     @Override
	     public int compare(Point p, Point p2)
	     {
	         return Double.compare(p.getX(), p.getX());
	     }        
	 };
	 return comp;
	}  
	
	public static Comparator<Point> getCompByY()
	{   
	 Comparator<Point> comp = new Comparator<Point>(){
	     @Override
	     public int compare(Point p, Point p2)
	     {
	         return Double.compare(p.getY(), p2.getY());
	     }        
	 };
	 return comp;
	} 
}
