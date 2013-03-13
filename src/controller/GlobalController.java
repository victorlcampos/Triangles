package controller;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import model.Point;
import view.Window;

public class GlobalController {
	
	private static GlobalController instance;
	
	private GlobalController() {}
	
	public static GlobalController getInstance() {		
		if (instance == null){
			instance = new GlobalController();
		}		
		return instance;
	}
	
	public void loadMeshFromFile(String filename) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;
		Point point;
		String[] split;
		int count = 0, x = 0, y = 0, rgb = 0;
		while ((line = reader.readLine()) != null) {
			count++;
			split = line.split(" +");
			if (split.length != 3) {
				count++;
				throw new IllegalStateException("Formato inválido na linha " + count + ".");
			}
			try {
				x = Integer.valueOf(split[0]);
				y = Integer.valueOf(split[1]);
				rgb = Integer.valueOf(split[2]);
			} catch (NumberFormatException e) {
				count++;
				throw new IllegalStateException("Formato inválido na linha " + count + ".");
			}
			point = new Point(x, y, new Color(rgb));
			Window.canvas.getPoints().add(point);
		}
		reader.close();
		Window.canvas.repaint();
	}
	
	public void saveMeshInFile() throws Exception {
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));  
		if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(Window.canvas) ) {
			File outputfile = fileChooser.getSelectedFile();
			BufferedWriter writter = new BufferedWriter(new FileWriter(outputfile));		
			for (Point point : Window.canvas.getPoints()) {
				writter.write(point.getX()+" "+point.getY()+" "+point.getColor().getRGB()+"\n");
			}
			writter.close();
		}
		
	}
	
}
