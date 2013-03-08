package model;

import java.awt.Color;

public class Point{
	private int x, y;
	private Color color;
	
	public Point(int x, int y, Color color){
		this.setX(x);
		this.setY(y);
		this.setColor(color);
	}
	
	public double dist(Point p) {		
		return Math.sqrt(Math.pow((x-p.getX()), 2) + Math.pow((y-p.getY()), 2));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
