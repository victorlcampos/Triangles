package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.Point;

public class Canvas extends JPanel {
	private List<Point> points = new ArrayList<Point>();
	private Color color;
	private Boolean wireframe = false;
	private Boolean rasterization = true;
	private Integer numEdges = 3;

	public Canvas() {
		color = Color.black;

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				points.add(new Point(e.getX(), e.getY(), color));
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
					if (rasterization) {
						drawLine(point1, point2, polygon.get(0), g, false);
					}
					if (wireframe) {
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

	private void drawLine(Point point1, Point point2, Point firstPoint, Graphics g, Boolean wireframe) {
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
						drawLinePoint(point1, point2, firstPoint, g, x, y, fullDist, wireframe);
						erro += deltaY;
					} else {
						x++;
						y++;
						drawLinePoint(point1, point2, firstPoint, g, x, y, fullDist, wireframe);
						erro += deltaY - deltaX;
					}
				}
			} else {
				for (int i = 1; i < Math.abs(deltaY); i++) {
					if (erro < 0) {
						x++;
						y++;
						drawLinePoint(point1, point2, firstPoint, g, x, y, fullDist, wireframe);
						erro += deltaY - deltaX;
					} else {
						y++;
						drawLinePoint(point1, point2, firstPoint, g, x, y, fullDist, wireframe);
						erro -= deltaX;
					}
				}
			}
		} else {
			if (Math.abs(deltaX) >= Math.abs(deltaY)) {
				for (int i = 1; i < Math.abs(deltaX); i++) {
					if (erro < 0) {
						x--;
						drawLinePoint(point1, point2, firstPoint, g, x, y, fullDist, wireframe);
						erro += deltaY;
					} else {
						x--;
						y++;
						drawLinePoint(point1, point2, firstPoint, g, x, y, fullDist, wireframe);
						erro += deltaY + deltaX;
					}
				}
			} else {
				for (int i = 1; i < Math.abs(deltaY); i++) {
					if (erro < 0) {
						x--;
						y++;
						drawLinePoint(point1, point2, firstPoint, g, x, y, fullDist, wireframe);
						erro += deltaY + deltaX;
					} else {
						y++;
						drawLinePoint(point1, point2, firstPoint, g, x, y, fullDist, wireframe);
						erro += deltaX;
					}
				}
			}
		}
	}

	private void drawLinePoint(Point point1, Point point2, Point firstPoint, Graphics g, int x,
			int y, double fullDist, Boolean wireframe) {
		double dist;
		double percent_color2;
		dist = point1.dist(new Point(x, y, null));
		percent_color2 = dist/fullDist;
		
		float red = new Float((point1.getColor().getRed()*(1-percent_color2)+point2.getColor().getRed()*percent_color2)/255);
		float green = new Float((point1.getColor().getGreen()*(1-percent_color2)+point2.getColor().getGreen()*percent_color2)/255);
		float blue = new Float((point1.getColor().getBlue()*(1-percent_color2)+point2.getColor().getBlue()*percent_color2)/255);
		Color color = new Color(red, green, blue);
		Point point = new Point(x, y, color);		
		if (wireframe) {
			g.setColor(Color.BLACK);
		}else{
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

	public Boolean getWireframe() {
		return wireframe;
	}

	public void setWireframe(Boolean wireframe) {
		this.wireframe = wireframe;
	}

	public Boolean getRasterization() {
		return rasterization;
	}

	public void setRasterization(Boolean rasterization) {
		this.rasterization = rasterization;
	}
}
