package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JLabel;

import org.math.plot.Plot2DPanel;
import org.math.plot.Plot3DPanel;
import org.math.array.DoubleArray.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Grafica extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JRadioButton rdbtnGrficad;
	private JRadioButton rdbtnNewRadioButton;
	private double[] x;
	private double[] y;
	private double[][] z;
	private double[] x1;
	private double[] y1;
	private double[][] z1;
	private double[] x2;
	private double[] y2;
	private double[][] z2;
	private double[][] prueb;
	Plot2DPanel grafica2D = new Plot2DPanel();
	Plot3DPanel grafica3D = new Plot3DPanel();
	private JTabbedPane tabbedPane;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	public Grafica(final EcTiempo frame) {
		setTitle("Gr\u00E1ficas");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1144, 825);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton btnAscensinRecta = new JButton("Ascensi\u00F3n recta");
		btnAscensinRecta.setBounds(813, 74, 147, 23);
		contentPanel.add(btnAscensinRecta);

		JButton btnDeclinacin = new JButton("Declinaci\u00F3n");
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 25, 654, 718);
		contentPanel.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Grafica 2D", null, panel, null);
		panel.setLayout(null);
		grafica2D.setBounds(10, 74, 631, 607);
		grafica2D.setAdjustBounds(true);
		
		panel.add(grafica2D);
		
		lblNewLabel = new JLabel("Gr\u00e1ficas 2D y 3D de la posici\u00F3n del sol");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new java.awt.Font("Tahoma", 0, 30)); 
		lblNewLabel.setBounds(10, 11, 629, 52);
		panel.add(lblNewLabel);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(726, 489, 350, 152);
		contentPanel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setText("Aqui se muestra la informaci\u00F3n de cada una \n"
				+ " de las gr\u00e1ficas.");
		
		
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Grafica 3D", null, panel_2, null);
		panel_2.setLayout(null);
		
		panel_2.add(grafica3D);
		grafica3D.setBounds(10, 75, 629, 604);
		
		lblNewLabel_1 = new JLabel("Gr\u00e1ficas 2D y 3D de la posici\u00F3n del sol");
		lblNewLabel_1.setBounds(10, 11, 629, 52);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new java.awt.Font("Tahoma", 0, 30));
		panel_2.add(lblNewLabel_1);

	
		
		btnAscensinRecta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					if(rdbtnGrficad.isSelected()==true){
						
						grafica3D.removeAllPlots();
						grafica2D.removeAllPlots();
						tabbedPane.setSelectedIndex(0);
						grafica2D.addScatterPlot("Ascensi\u00F3n recta solar", x1, y1);
						grafica2D.setAxisLabels("Tiempo(d\u00edas)","Ascensi\u00F3n recta(horas)");
						lblNewLabel.setText("Ascensi\u00F3n recta solar");
						textArea.setEditable(false);
						textArea.setText("Ascensi\u00F3n recta solar: \n"
								+ "La ascensi\u00F3n recta se mide a partir del punto Aries en\n "
								+ "horas (una hora igual a 15 grados), minutos y segundos \n "
								+ "hacia el Este a lo largo del ecuador celeste.\n\n "
								+ "El punto Aries (o punto Vernal) est\u00e1 en la posici\u00F3n del Sol en el \n "
								+ "equinoccio de Primavera o Equinoccio vernal. Su s\u00edmbolo es \u03B1");
					
					}else{
						
						grafica2D.removeAllPlots();
						grafica3D.removeAllPlots();
						tabbedPane.setSelectedIndex(1);
						lblNewLabel_1.setText("Ascensi\u00F3n recta solar");
						grafica3D.addGridPlot("Ascensi\u00F3n recta solar", x1, y1, z1);
						grafica3D.setAxisLabels("Tiempo(d\u00edas)","Ascesi\u00F3n recta(horas)","Ascensi\u00F3n recta(horas)");
						textArea.setEditable(false);
						textArea.setText("Ascensi\u00F3n recta solar: \n"
								+ "La ascensi\u00F3n recta se mide a partir del punto Aries en\n "
								+ "horas (una hora igual a 15 grados), minutos y segundos \n "
								+ "hacia el Este a lo largo del ecuador celeste.\n\n "
								+ "El punto Aries (o punto Vernal) est\u00e1 en la posici\u00F3n del Sol en el \n "
								+ "equinoccio de Primavera o Equinoccio vernal. Su s\u00edmbolo es \u03B1");
					}
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null,
							"No ha insertado valores en la tabla");
				} catch (NumberFormatException e) {
					JOptionPane
							.showMessageDialog(null,
									"Elija la opci\u00F3n 'decimal' de la tabla para generar valores");
				} catch (IndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null,
							"No ha insertado valores en la tabla");
				}
				
			}
		});
		
		
		btnDeclinacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if(rdbtnGrficad.isSelected()==true){
						
						
						grafica2D.removeAllPlots();
						grafica3D.removeAllPlots();
						tabbedPane.setSelectedIndex(0);
						lblNewLabel.setText("Declinaci\u00F3n solar");
						grafica2D.addScatterPlot("Declinaci\u00F3n solar", x, y);
						grafica2D.setAxisLabels("Tiempo(d\u00edas)","Declinaci\u00F3n(grados)");
						textArea.setEditable(false);
						textArea.setText("Declinaci\u00F3n solar:\n"
								+ "La declinación es el \u00e1ngulo que forma un astro con el ecuador celeste. \n "
								+ "Es una de las dos coordenadas del sistema de coordenadas ecuatoriales.\n"
								+ "La declinaci\u00F3n se mide en grados y es positiva si est\u00e1 al norte del\n"
								+ "ecuador celeste y negativa si est\u00e1 al sur.\n\n "
								+ "La declinaci\u00F3n es comparable a la latitud geogr\u00e1fica\n"
								+ "(que se mide sobre el ecuador terrestre).");
					
					}else{

						grafica2D.removeAllPlots();
						grafica3D.removeAllPlots();
						tabbedPane.setSelectedIndex(1);
						lblNewLabel_1.setText("Declinaci\u00F3n solar");
						grafica3D.addGridPlot("Declinaci\u00F3n solar", x, y, z);
						grafica3D.setAxisLabels("Tiempo(d\u00edas)","Declinaci\u00F3n(grados)","Declinaci\u00F3n(grados)");
						textArea.setEditable(false);
						textArea.setText("Declinaci\u00F3n solar:\n"
								+ "La declinación es el \u00e1ngulo que forma un astro con el ecuador celeste. \n "
								+ "Es una de las dos coordenadas del sistema de coordenadas ecuatoriales.\n"
								+ "La declinaci\u00F3n se mide en grados y es positiva si est\u00e1 al norte del\n"
								+ "ecuador celeste y negativa si est\u00e1 al sur.\n\n "
								+ "La declinaci\u00F3n es comparable a la latitud geogr\u00e1fica\n"
								+ "(que se mide sobre el ecuador terrestre).");
					}
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null,
							"No ha insertado valores en la tabla");
				} catch (NumberFormatException e) {
					JOptionPane
							.showMessageDialog(null,
									"Elija la opci\u00F3n 'decimal' de la tabla para generar valores");
				} catch (IndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null,
							"No ha insertado valores en la tabla");
				}

			}
		});
		btnDeclinacin.setBounds(813, 121, 147, 23);
		contentPanel.add(btnDeclinacin);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(813, 330, 124, 127);
		panel_1.setBorder(BorderFactory.createTitledBorder("Control"));
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		rdbtnNewRadioButton = new JRadioButton("Gr\u00E1fica 3D");
		rdbtnNewRadioButton.setBounds(22, 73, 96, 28);
		panel_1.add(rdbtnNewRadioButton);

		rdbtnGrficad = new JRadioButton("Gr\u00E1fica 2D");
		rdbtnGrficad.setBounds(22, 24, 94, 28);
		panel_1.add(rdbtnGrficad);
		rdbtnGrficad.setSelected(true);
		
		ButtonGroup grupo1=new ButtonGroup();
		grupo1.add(rdbtnGrficad);
		grupo1.add(rdbtnNewRadioButton);
		
		
		JButton btnAnalema = new JButton("Ec Tiempo");
		btnAnalema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(rdbtnGrficad.isSelected()==true){
						
						
						grafica2D.removeAllPlots();
						grafica3D.removeAllPlots();
						tabbedPane.setSelectedIndex(0);
						lblNewLabel.setText("Ecuaci\u00F3n del tiempo");
						grafica2D.addScatterPlot("Ec Tiempo", x2, y2);
						grafica2D.setAxisLabels("Tiempo(d\u00edas)","Eq tiempo(minutos)");
						textArea.setEditable(false);
						textArea.setText("Ecuaci\u00F3n del tiempo:\n"
								+ "Se entiende c\u00F3mo la diferencia que hay entre\n"
								+ "el tiempo solar aparente (movimiento real del sol) y\n"
								+ "el tiempo solar medio (el que se mide con un reloj).\n");
						
					
					}else{

						grafica2D.removeAllPlots();
						grafica3D.removeAllPlots();
						tabbedPane.setSelectedIndex(1);
						lblNewLabel_1.setText("Ecuaci\u00F3n del tiempo");
						grafica3D.addGridPlot("Ec Tiempo", x2, y2, z2);
						grafica3D.setAxisLabels("Tiempo (d\u00edas)","Eq tiempo(minutos)","Eq tiempo(minutos)");
						textArea.setEditable(false);
						textArea.setText("Ecuaci\u00F3n del tiempo:\n"
								+ "Se entiende c\u00F3mo la diferencia que hay entre\n"
								+ "el tiempo solar aparente (movimiento real del sol) y\n"
								+ "el tiempo solar medio (el que se mide con un reloj).\n");
					}
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null,
							"No ha insertado valores en la tabla");
				} catch (NumberFormatException e) {
					JOptionPane
							.showMessageDialog(null,
									"Elija la opci\u00F3n 'decimal' de la tabla para generar valores");
				} catch (IndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null,
							"Valores incorrectos");
				}
				
			}
		});
		btnAnalema.setBounds(813, 211, 147, 23);
		contentPanel.add(btnAnalema);
		
		JButton btnNewButton = new JButton("Analema");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(rdbtnGrficad.isSelected()==true){
						
						
						grafica2D.removeAllPlots();
						grafica3D.removeAllPlots();
						tabbedPane.setSelectedIndex(0);
						lblNewLabel.setText("Analema solar");
						grafica2D.addScatterPlot("Analema", y2, y);
						grafica2D.setAxisLabels("Eq tiempo (min)","Declinaci\u00f3n(grados)");
						textArea.setEditable(false);
						textArea.setText("Analema Solar:\n"
								+ "El analema muestra el movimiento aparente del sol visto desde\n"
								+ "la tierra. Si se tomara una forograf\u00eda del sol todos\n"
								+ "los d\u00edas a una hora determinada, la im\u00e1gen resultante\n"
								+ "ser\u00eda parecida a la que muestra la gr\u00e1fica.");
					
					}else{

						grafica2D.removeAllPlots();
						grafica3D.removeAllPlots();
						tabbedPane.setSelectedIndex(1);
						lblNewLabel_1.setText("Analema solar");
						grafica3D.addGridPlot("Analema", y2, y, z);
						grafica3D.setAxisLabels("Eq tiempo(min)","Declinaci\u00F3n (grados)","Declinaci\u00F3n (grados)");
						textArea.setEditable(false);
						textArea.setText("Analema Solar:\n"
								+ "El analema muestra el movimiento aparente del sol visto desde\n"
								+ "la tierra. Si se tomara una forograf\u00eda del sol todos\n"
								+ "los d\u00edas a una hora determinada, la im\u00e1gen resultante\n"
								+ "ser\u00eda parecida a la que muestra la gr\u00e1fica.");
					}
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null,
							"No ha insertado valores en la tabla");
				} catch (NumberFormatException e) {
					JOptionPane
							.showMessageDialog(null,
									"Elija la opci\u00F3n 'decimal' de la tabla para generar valores");
				} catch (IndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null,
							"Valores incorrectos");
				}
			}
		});
		btnNewButton.setBounds(813, 255, 147, 23);
		contentPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("AscR/Dec");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					if(rdbtnGrficad.isSelected()==true){
						
						
						grafica2D.removeAllPlots();
						grafica3D.removeAllPlots();
						tabbedPane.setSelectedIndex(0);
						lblNewLabel.setText("Ascensi\u00F3n recta/Declinaci\u00F3n");
						grafica2D.addScatterPlot("AcsR/Dec", y1, y);
						grafica2D.setAxisLabels("Ascensi\u00F3n (horas)","Declinaci\u00F3n(grados)");textArea.setEditable(false);
						textArea.setEditable(false);
						textArea.setText("Coordenadas ecuatoriales (absolutas):\n"
								+ "Son un tipo de coordenadas celestes que muestran la\n"
								+ "posici\u00F3n de un objeto respecto al ecuador celeste\n"
								+ "y al punto aries (momento en el que el sol pasa de sur a norte).\n\n"
								+ "Sus 'ejes' se denominan Ascensi\u00F3n recta y declinaci\u00F3n. ");
					
					}else{

						grafica2D.removeAllPlots();
						grafica3D.removeAllPlots();
						tabbedPane.setSelectedIndex(1);
						lblNewLabel_1.setText("Ascensi\u00F3n recta/Declinaci\u00F3n");
						grafica3D.addScatterPlot("AcsR/Dec", x1, y1, y);
						grafica3D.setAxisLabels("Tiempo(d\u00edas)","Ascensi\u00F3n (horas)", "Declinaci\u00F3n(grados)");
						textArea.setEditable(false);
						textArea.setText("Coordenadas ecuatoriales (absolutas):\n"
								+ "Son un tipo de coordenadas celestes que muestran la\n"
								+ "posici\u00F3n de un objeto respecto al ecuador celeste\n"
								+ "y al punto aries (momento en el que el sol pasa de sur a norte).\n\n"
								+ "Sus 'ejes' se denominan Ascensi\u00F3n recta y declinaci\u00F3n. ");
					}
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null,
							"No ha insertado valores en la tabla");
				} catch (NumberFormatException e) {
					JOptionPane
							.showMessageDialog(null,
									"Elija la opci\u00F3n 'decimal' de la tabla para generar valores");
				} catch (IndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null,
							"Valores incorrectos");
				}
				
			}
		});
		btnNewButton_1.setBounds(813, 165, 147, 23);
		contentPanel.add(btnNewButton_1);
		
		
		

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void getDec(double[] x1) {
		x = new double[x1.length];
		y = new double[x1.length];
		z = new double[x1.length][x1.length];
		
		for(int i = 0; i < x.length; i++){
			x[i]=i+1;
		}
		for (int i = 0; i < y.length; i++) {
			y[i] = x1[i];
			
		}
		
		for (int i = 0; i < z.length; i++) {
			for(int j=0;j<z.length;j++){
				z[j][i]=x1[i];
			}
			
		}
	}
	
	public void getAc(double[] x2) {
		x1 = new double[x2.length];
		y1 = new double[x2.length];
		z1 = new double[x2.length][x2.length];
		for(int i = 0; i < x1.length; i++){
			x1[i]=i+1;
		}
		for (int i = 0; i < y1.length; i++) {
			y1[i] = x2[i];
			
		}
		
		for (int i = 0; i < z1.length; i++) {
			for(int j=0;j<z1.length;j++){
				z1[j][i]=x2[i];
			}
			
		}
	}
	
	public void getEq(double[] x3) {
		x2 = new double[x3.length];
		y2 = new double[x3.length];
		z2 = new double[x3.length][x3.length];
		for(int i = 0; i < x1.length; i++){
			x2[i]=i+1;
		}
		for (int i = 0; i < y1.length; i++) {
			y2[i] = x3[i];
			
		}
		
		for (int i = 0; i < z1.length; i++) {
			for(int j=0;j<z1.length;j++){
				z2[j][i]=x3[i];
			}
			
		}
	}

	public static double[] f(double[] x) {
		double[] z = new double[x.length];
		for (int i = 0; i < x.length; i++) {
			z[i] = x[i];
		}

		return z;
	}// end method
}
