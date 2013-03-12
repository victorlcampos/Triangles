package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;

import model.Wireframe;
import controller.GlobalController;

public class Window {

	public static JFrame mainFrame;
	public static JButton btnSelecionarCor;
	public static JTextField txtFieldNumEdges;
	public static Canvas canvas = new Canvas();
	private static JTextField tfRotation;
	private static JTextField tfTranslateX;
	private static JTextField tfTranslateY;
	private static JTextField tfScale;
	private static JTextField tfTexture;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window.initialize();
					Window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() {
		mainFrame = new JFrame();
		mainFrame.setTitle("UFRJ - IM/DCC - CG - 2012.2 - Victor Campos & Thales Pires");
		mainFrame.setBounds(100, 100, 655, 572);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{593, 0};
		gridBagLayout.rowHeights = new int[]{35, 0, 29, 148, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 2.0, Double.MIN_VALUE};
		mainFrame.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		mainFrame.getContentPane().add(panel, gbc_panel);
		
		btnSelecionarCor = new JButton("Selecionar Cor");
		
		JButton btnLoadFile = new JButton("Carregar Arquivo");
		btnLoadFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				String defaultDir = Window.class.getResource("Window.class").getFile();
                JFileChooser fileChooser = new JFileChooser(defaultDir);
                fileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public String getDescription() {
                            return "Text file (.txt)";
                    }
                    @Override
                    public boolean accept(File f) {
                            return f.getName().toLowerCase().endsWith(".txt") || f.isDirectory();
                    }
                });
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooser.getSelectedFile().getAbsolutePath();
                    try {
						GlobalController.getInstance().loadMeshFromFile(filename);
					} catch (Exception e) {
						e.printStackTrace(); 
						JOptionPane.showMessageDialog(mainFrame, e.getMessage());
					}
                }
			}
		});
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		panel.add(btnSelecionarCor);
		panel.add(btnLoadFile);
		
		JButton btnLimparCanvas = new JButton("Limpar Canvas");
		btnLimparCanvas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas.getPoints().clear();
				canvas.repaint();
			}
		});
		
		JButton btnSalvarArquivo = new JButton("Salvar Arquivo");
		btnSalvarArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					GlobalController.getInstance().saveMeshInFile();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		panel.add(btnSalvarArquivo);
		panel.add(btnLimparCanvas);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnSelecionarCor, btnLoadFile, btnLimparCanvas}));
		btnSelecionarCor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingColorChooserDemo.createAndShowGUI();
			}
		});
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		mainFrame.getContentPane().add(panel_2, gbc_panel_2);
		
		JButton btnTransladar = new JButton("Transladar X");
		btnTransladar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.transalteX(new Integer(tfTranslateX.getText()));
			}
		});
		panel_2.add(btnTransladar);
		
		tfTranslateX = new JTextField();
		tfTranslateX.setText("10");
		panel_2.add(tfTranslateX);
		tfTranslateX.setColumns(10);
		
		JButton btnTransladarY = new JButton("Transladar Y");
		btnTransladarY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.transalteY(new Integer(tfTranslateY.getText()));
			}
		});
		panel_2.add(btnTransladarY);
		
		tfTranslateY = new JTextField();
		tfTranslateY.setText("10");
		panel_2.add(tfTranslateY);
		tfTranslateY.setColumns(10);
		
		JButton btnRotacionar = new JButton("Rotacionar Imagem");
		btnRotacionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.rotation(new Double(tfRotation.getText()));
			}
		});
		panel_2.add(btnRotacionar);
		
		tfRotation = new JTextField();
		tfRotation.setText("90\n");
		panel_2.add(tfRotation);
		tfRotation.setColumns(10);
		
		JButton btnEscala = new JButton("Escala");
		btnEscala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.scale(new Integer(tfScale.getText()));
			}
		});
		panel_2.add(btnEscala);
		
		tfScale = new JTextField();
		tfScale.setText("2");
		panel_2.add(tfScale);
		tfScale.setColumns(10);
		
		JButton btnNewButton = new JButton("Carregar Textura");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(canvas);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		        	File file = fc.getSelectedFile();
		            canvas.setTexture(file);
		            tfTexture.setText(file.getAbsolutePath());
		        } else {
		        }
			}
		});
		panel_2.add(btnNewButton);
		
		tfTexture = new JTextField();
		tfTexture.setEditable(false);
		panel_2.add(tfTexture);
		tfTexture.setColumns(10);
		
		JButton btnSalvarImagem = new JButton("Salvar Imagem");
		btnSalvarImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas.saveImage();
			}
		});
		panel_2.add(btnSalvarImagem);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setHgap(10);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.SOUTH;
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		mainFrame.getContentPane().add(panel_1, gbc_panel_1);
		
		JLabel lblNewLabel = new JLabel("#Vértices:");
		panel_1.add(lblNewLabel);
		
		txtFieldNumEdges = new JTextField();
		txtFieldNumEdges.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(txtFieldNumEdges);
		txtFieldNumEdges.setText("3");
		txtFieldNumEdges.setColumns(10);
		
		final JCheckBox chckbxRasterizacap = new JCheckBox("Rasterização");
		chckbxRasterizacap.setSelected(true);
		panel_1.add(chckbxRasterizacap);
		
		JComboBox cboxWireframe = new JComboBox();
		panel_1.add(cboxWireframe);
		cboxWireframe.setModel(new DefaultComboBoxModel(new String[] {"Nenhum Wireframe", "Wireframe 1", "Wireframe 2", "Wireframe 3", "Wireframe 4"}));
		
		final JCheckBox chckbxTextura = new JCheckBox("Textura");
		
		chckbxTextura.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				canvas.setTextureEnable(chckbxTextura.isSelected());
				canvas.repaint();
			}
		});
		
		panel_1.add(chckbxTextura);
		cboxWireframe.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JComboBox comboBox = (JComboBox) arg0.getSource();
				
				canvas.setWireframeEnable(true);
				switch (comboBox.getSelectedIndex()) {
				case 0:
					canvas.setWireframeEnable(false);
					break;
				case 1:
					canvas.setWireframe(Wireframe.getDefaultType());
					break;
				case 2:
					canvas.setWireframe(Wireframe.getTypeOne());
					break;	
				case 3:
					canvas.setWireframe(Wireframe.getTypeTwo());
					break;
				case 4:
					canvas.setWireframe(Wireframe.getTypeThree());
					break;
				}
				canvas.repaint();
			}
		});
		chckbxRasterizacap.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				canvas.setRasterizationEnable(chckbxRasterizacap.isSelected());
				canvas.repaint();
			}
		});
		txtFieldNumEdges.getDocument().addDocumentListener(new DocumentListener() {
			@Override public void removeUpdate(DocumentEvent arg0) {}
			@Override public void changedUpdate(DocumentEvent arg0) {}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				Integer numEdges = Integer.valueOf(txtFieldNumEdges.getText());
				canvas.setNumEdges(numEdges);
				canvas.repaint();
			}
			
		});
		
		canvas.setBackground(Color.WHITE);
		canvas.setLayout(null);
		GridBagConstraints gbc_canvas = new GridBagConstraints();
		gbc_canvas.fill = GridBagConstraints.BOTH;
		gbc_canvas.gridx = 0;
		gbc_canvas.gridy = 3;
		mainFrame.getContentPane().add(canvas, gbc_canvas);
	}
	
	public static void clearCanvas() {
		canvas.getPoints().clear();
		canvas.repaint();
	}
	
	public static void setNumEdges(Integer num) {
		txtFieldNumEdges.setText(num.toString());
	}
}
