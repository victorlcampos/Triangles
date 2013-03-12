package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.Point;
import model.Wireframe;

@SuppressWarnings("serial")
public class Canvas extends JPanel {

	private List<Point> points = new ArrayList<Point>();
	private Color color;
	private Wireframe wireframe = Wireframe.getDefaultType();
	private Boolean wireframeEnable = false;
	private Boolean rasterizationEnable = true;
	private Integer numEdges = 3;

	public Canvas() {
		color = Color.black;

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Point point = new Point(e.getX(), e.getY(), color);
				points.add(point);
				repaint();
			}
		});
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		List<Point> polygon = new ArrayList<Point>();
		Point point1, point2;
		for (int i = 0; i < points.size(); i++) {
			polygon.add(points.get(i));
			if (i % numEdges == (numEdges - 1)) {
				for (int j = 0; j < numEdges; j++) {
					point1 = polygon.get(j);
					point2 = polygon.get((j + 1) % numEdges);
					if (rasterizationEnable) {
						drawLine(point1, point2, polygon.get(0), g, false);
					}
					if (wireframeEnable) {
						drawLine(point1, point2, polygon.get(0), g, true);
					}
				}
				polygon.clear();
			}
		}

		for (Point point : polygon) {
			g.setColor(point.getColor());
			g.drawRect(point.getX(), point.getY(), 1, 1);
		}
	}

	public Integer getNumEdges() {
		return numEdges;
	}

	public void setNumEdges(Integer numEdges) {
		this.numEdges = numEdges;
	}

	private void drawLine(Point point1, Point point2, Point firstPoint,
			Graphics g, Boolean wireframe) {
		int x, y, erro, deltaX, deltaY;
		erro = 0;
		int x1 = point1.getX();
		int x2 = point2.getX();
		int y1 = point1.getY();
		int y2 = point2.getY();

		double fullDist = point1.dist(point2);
		x = x1;
		y = y1;
		deltaX = x2 - x1;
		deltaY = y2 - y1;

		if ((Math.abs(deltaY) >= Math.abs(deltaX) && y1 > y2)
				|| (Math.abs(deltaY) < Math.abs(deltaX) && deltaY < 0)) {

			x = x2;
			y = y2;
			deltaX = x1 - x2;
			deltaY = y1 - y2;
		}
		if (deltaX >= 0) {
			if (Math.abs(deltaX) >= Math.abs(deltaY)) {
				for (int i = 1; i < Math.abs(deltaX); i++) {
					if (erro < 0) {
						x++;
						drawLinePoint(point1, point2, firstPoint, g, x, y,
								fullDist, wireframe);
						erro += deltaY;
					} else {
						x++;
						y++;
						drawLinePoint(point1, point2, firstPoint, g, x, y,
								fullDist, wireframe);
						erro += deltaY - deltaX;
					}
				}
			} else {
				for (int i = 1; i < Math.abs(deltaY); i++) {
					if (erro < 0) {
						x++;
						y++;
						drawLinePoint(point1, point2, firstPoint, g, x, y,
								fullDist, wireframe);
						erro += deltaY - deltaX;
					} else {
						y++;
						drawLinePoint(point1, point2, firstPoint, g, x, y,
								fullDist, wireframe);
						erro -= deltaX;
					}
				}
			}
		} else {
			if (Math.abs(deltaX) >= Math.abs(deltaY)) {
				for (int i = 1; i < Math.abs(deltaX); i++) {
					if (erro < 0) {
						x--;
						drawLinePoint(point1, point2, firstPoint, g, x, y,
								fullDist, wireframe);
						erro += deltaY;
					} else {
						x--;
						y++;
						drawLinePoint(point1, point2, firstPoint, g, x, y,
								fullDist, wireframe);
						erro += deltaY + deltaX;
					}
				}
			} else {
				for (int i = 1; i < Math.abs(deltaY); i++) {
					if (erro < 0) {
						x--;
						y++;
						drawLinePoint(point1, point2, firstPoint, g, x, y,
								fullDist, wireframe);
						erro += deltaY + deltaX;
					} else {
						y++;
						drawLinePoint(point1, point2, firstPoint, g, x, y,
								fullDist, wireframe);
						erro += deltaX;
					}
				}
			}
		}
	}

	private void drawLinePoint(Point point1, Point point2, Point firstPoint,
			Graphics g, int x, int y, double fullDist, Boolean wireframe) {
		if (wireframe && !this.wireframe.shouldDraw())
			return;

		double dist;
		double percent_color2;
		dist = point1.dist(new Point(x, y, null));
		percent_color2 = dist / fullDist;

		float red = new Float((point1.getColor().getRed()
				* (1 - percent_color2) + point2.getColor().getRed()
				* percent_color2) / 255);
		float green = new Float((point1.getColor().getGreen()
				* (1 - percent_color2) + point2.getColor().getGreen()
				* percent_color2) / 255);
		float blue = new Float((point1.getColor().getBlue()
				* (1 - percent_color2) + point2.getColor().getBlue()
				* percent_color2) / 255);
		Color color = new Color(red, green, blue);
		Point point = new Point(x, y, color);
		if (wireframe) {
			g.setColor(Color.BLACK);
		} else {
			g.setColor(point.getColor());
		}

		g.drawRect(x, y, 1, 1);

		if (!wireframe && !point1.equals(firstPoint)) {
			drawLine(firstPoint, point, firstPoint, g, false);
		}
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Boolean isWireframeEnable() {
		return wireframeEnable;
	}

	public void setWireframeEnable(Boolean wireframe) {
		this.wireframeEnable = wireframe;
	}

	public void setWireframe(Wireframe wireframe) {
		this.wireframe = wireframe;
	}

	public Boolean isRasterizationEnable() {
		return rasterizationEnable;
	}

	public void setRasterizationEnable(Boolean rasterization) {
		this.rasterizationEnable = rasterization;
	}

	public void rotation(Double degree) {
		Double rads = degree*Math.PI/180;
		Double cosB = Math.cos(rads);
		Double senB = Math.sin(rads);
		int x, y;
		Integer x1 = null, y1 = null;
		for (Point point : getPoints()) {
			if (x1 == null || y1 == null) {
				x1 = point.getX();
				y1 = point.getY();
			}
			x = point.getX() - x1;
			y = point.getY() - y1;
			
			point.setX(Math.round(new Float(x*cosB - y*senB)) + x1);
			point.setY(Math.round(new Float(y*cosB + x*senB)) + y1);
		}
		repaint();
	}

	public void transalteX(Integer x) {		
		for (Point point : getPoints()) {			
			point.setX(point.getX() + x);
		}
		repaint();
	}
	
	public void transalteY(Integer y) {		
		for (Point point : getPoints()) {			
			point.setY(point.getY() + y);
		}
		repaint();
	}

	public void scale(Integer scale) {
		int x, y;
		Integer x1 = null, y1 = null;
		for (Point point : getPoints()) {
			
			if (x1 == null || y1 == null) {
				x1 = point.getX();
				y1 = point.getY();
			}
			x = point.getX() - x1;
			y = point.getY() - y1;
			
			point.setX(x*scale + x1);
			point.setY(y*scale + y1);
		}
		repaint();
	}
}
