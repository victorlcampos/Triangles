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
		List<Point> triangle = new ArrayList<Point>();
		Point point1, point2;
		for (int i = 0; i < points.size(); i++) {
			triangle.add(points.get(i));
			if (i % 3 == 2) {
				for (int j = 0; j < 3; j++) {
					point1 = triangle.get(j);
					point2 = triangle.get((j + 1) % 3);
					drawLine(point1.getX(), point1.getY(), point2.getX(),
							point2.getY(), g);
				}
				triangle.clear();
			}
		}

		for (Point point : triangle) {
			g.setColor(point.getColor());
			g.drawRect(point.getX(), point.getY(), 1, 1);
		}
	}

	private void drawLine(int x1, int y1, int x2, int y2, Graphics g) {
		int x, y, erro, deltaX, deltaY;
		erro = 0;
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
						g.drawRect(x, y, 1, 1);
						erro += deltaY;
					} else {
						x++;
						y++;
						g.drawRect(x, y, 1, 1);
						erro += deltaY - deltaX;
					}
				}
			} else {
				for (int i = 1; i < Math.abs(deltaY); i++) {
					if (erro < 0) {
						x++;
						y++;
						g.drawRect(x, y, 1, 1);
						erro += deltaY - deltaX;
					} else {
						y++;
						g.drawRect(x, y, 1, 1);
						erro -= deltaX;
					}
				}
			}
		} else {
			if (Math.abs(deltaX) >= Math.abs(deltaY)) {
				for (int i = 1; i < Math.abs(deltaX); i++) {
					if (erro < 0) {
						x--;
						g.drawRect(x, y, 1, 1);
						erro += deltaY;
					} else {
						x--;
						y++;
						g.drawRect(x, y, 1, 1);
						erro += deltaY + deltaX;
					}
				}
			} else {
				for (int i = 1; i < Math.abs(deltaY); i++) {
					if (erro < 0) {
						x--;
						y++;
						g.drawRect(x, y, 1, 1);
						erro += deltaY + deltaX;
					} else {
						y++;
						g.drawRect(x, y, 1, 1);
						erro += deltaX;
					}
				}
			}
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
}
