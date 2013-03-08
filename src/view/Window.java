package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

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
		
		btnSelecionarCor = new JButton("Selecionar cor");
		btnSelecionarCor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingColorChooserDemo.createAndShowGUI();
			}
		});
		frame.getContentPane().add(btnSelecionarCor, BorderLayout.NORTH);
		
		
		canvas.setBackground(Color.WHITE);
		
		frame.getContentPane().add(canvas, BorderLayout.CENTER);		
		canvas.setLayout(null);
	}
}
