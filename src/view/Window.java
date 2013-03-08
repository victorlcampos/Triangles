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
		
		JRadioButton rdbtnWireframe = new JRadioButton("wireframe");
		rdbtnWireframe.addChangeListener(new ChangeListener() {			
			@Override
			public void stateChanged(ChangeEvent e) {				
				canvas.setWireframe(((JRadioButton) e.getSource()).isSelected());
				canvas.repaint();
			}
		});
		panel.add(rdbtnWireframe);
		btnSelecionarCor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingColorChooserDemo.createAndShowGUI();
			}
		});
	}
}
