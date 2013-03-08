package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Window {

	private JFrame frame;
	public static JButton btnSelecionarCor;
	public final static Canvas canvas = new Canvas();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		canvas.setBackground(Color.WHITE);
		
		frame.getContentPane().add(canvas, BorderLayout.CENTER);		
		canvas.setLayout(null);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JButton btnLimparCanvas = new JButton("Limpar Canvas");
		btnLimparCanvas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas.getPoints().clear();
				canvas.repaint();
			}
		});
		panel.add(btnLimparCanvas);
		
		btnSelecionarCor = new JButton("Selecionar cor");
		panel.add(btnSelecionarCor);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rasteriza\u00E7\u00E3o", "Wireframe", "Ambos"}));
		comboBox.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JComboBox comboBox = (JComboBox) arg0.getSource();
				
				switch (comboBox.getSelectedIndex()) {
				case 0:
					canvas.setWireframe(false);
					canvas.setRasterization(true);
					break;
				case 1:
					canvas.setWireframe(true);
					canvas.setRasterization(false);
					break;
				case 2:
					canvas.setWireframe(true);
					canvas.setRasterization(true);
					break;				
				}
				canvas.repaint();
			}
		});
		panel.add(comboBox);
		btnSelecionarCor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingColorChooserDemo.createAndShowGUI();
			}
		});
	}
}
