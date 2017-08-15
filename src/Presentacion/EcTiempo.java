package Presentacion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import Logica.CargaDatos;
import Logica.EoT;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.nio.charset.Charset;
import java.util.Vector;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

public class EcTiempo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_9;
	static EcTiempo frame=new EcTiempo();
	EoT obj;
	Dgenerar object1;
	private JTextField textField_8;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private Vector<String> ciudades;
	private JComboBox comboBox;
	CargaDatos cargar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EcTiempo() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(EcTiempo.class.getResource("/Images/imgSunjpg.jpg")));
		
		
		
		obj=new EoT();
		setTitle("Ecuaci\u00f3n del tiempo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 749, 678);
		contentPane = new JPanel();
		
		
		cargar= new CargaDatos();
		
		ciudades = new Vector<String>();
		cargar.cargarDatos();
		cargar.print();
		for (int i = 0;i < cargar.getTamanio(); i++){
			ciudades.add(cargar.getCiudades(i));
		}
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(29, 61, 259, 442);
		panel.setBorder(BorderFactory.createTitledBorder("Datos"));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblIngreseElDa = new JLabel("Ingrese el d\u00eda:");
		lblIngreseElDa.setBounds(22, 44, 97, 14);
		panel.add(lblIngreseElDa);
		
		textField = new JTextField();
		textField.setBounds(22, 69, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblIngreseElMes = new JLabel("Ingrese el mes: ");
		lblIngreseElMes.setBounds(22, 117, 106, 14);
		panel.add(lblIngreseElMes);
		
		textField_1 = new JTextField();
		textField_1.setBounds(22, 142, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblIngreseElAo = new JLabel("Ingrese el a\u00F1o: ");
		lblIngreseElAo.setBounds(22, 191, 86, 14);
		panel.add(lblIngreseElAo);
		
		textField_2 = new JTextField();
		textField_2.setBounds(22, 216, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblIngreseLaHora = new JLabel("Ingrese la hora (formato militar):");
		lblIngreseLaHora.setBounds(22, 260, 196, 14);
		panel.add(lblIngreseLaHora);
		
		textField_3 = new JTextField();
		textField_3.setBounds(22, 285, 32, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel label = new JLabel(":");
		label.setBounds(64, 288, 10, 14);
		panel.add(label);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(76, 285, 32, 20);
		panel.add(textField_4);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(118, 288, 10, 14);
		panel.add(label_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(130, 285, 32, 20);
		panel.add(textField_5);
		
		comboBox = new JComboBox(ciudades);
		comboBox.setBounds(22, 365, 167, 20);
		panel.add(comboBox);
		
		JLabel lblHhmmss = new JLabel("hh:mm:ss");
		lblHhmmss.setBounds(22, 316, 97, 14);
		panel.add(lblHhmmss);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try{
					double dia=Double.parseDouble(textField.getText());
					double mes=Double.parseDouble(textField_1.getText());
					double anio=Double.parseDouble(textField_2.getText());
					double hora=Double.parseDouble(textField_3.getText());
					double min=Double.parseDouble(textField_4.getText());
					double seg=Double.parseDouble(textField_5.getText());
					if(dia>31||mes>12||hora>24||hora<0||min>60||seg>60||anio<1300)
						JOptionPane.showMessageDialog(null, "Formato incorrecto");
					else
					obj.recibirDatos(dia, mes, anio, hora, min, seg);
					textField_6.setText(String.valueOf(obj.calculoJuliano()));
					textField_7.setText(String.valueOf(obj.conversionHora(obj.hGA())));
					textField_8.setText(String.valueOf(obj.conversionHora(obj.ascensionRecta())));
					textField_9.setText(String.valueOf(obj.Conversioneq(obj.eqt((int)anio, (int)mes, (int)dia))));
					textField_10.setText(String.valueOf(obj.conversionGrado(obj.declinacion2())));
					textField_11.setText(String.valueOf(obj.conversionHora(obj.Azimut(cargar.getLongitud(comboBox.getSelectedIndex())))));
					textField_12.setText(String.valueOf(obj.conversionGrado(obj.Altura(cargar.getLongitud(comboBox.getSelectedIndex())))));
					textField_6.setEnabled(false);
					textField_7.setEnabled(false);
					textField_8.setEnabled(false);
					textField_9.setEnabled(false);
					textField_10.setEnabled(false);
					textField_11.setEnabled(false);
					textField_12.setEnabled(false);
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Ingrese solo n�meros");
				}	
				
			}
		});
		btnCalcular.setBounds(19, 408, 123, 23);
		panel.add(btnCalcular);
		
		JLabel lblZonaHoraria = new JLabel("Zona horaria");
		lblZonaHoraria.setBounds(22, 341, 86, 14);
		panel.add(lblZonaHoraria);
		
		
	    
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(380, 61, 259, 206);
		panel_1.setBorder(BorderFactory.createTitledBorder("LST"));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblDaEnJuliano = new JLabel("D\u00EDa en juliano: ");
		lblDaEnJuliano.setBounds(10, 11, 104, 14);
		panel_1.add(lblDaEnJuliano);
		
		textField_6 = new JTextField();
		textField_6.setBounds(10, 36, 167, 20);
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblLsthoraSideral = new JLabel("LST (hora sideral local):");
		lblLsthoraSideral.setBounds(10, 67, 167, 14);
		panel_1.add(lblLsthoraSideral);
		
		textField_7 = new JTextField();
		textField_7.setBounds(10, 92, 86, 20);
		panel_1.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblEcTiempo =new JLabel("Ecuaci\u00F3n del tiempo:");
		lblEcTiempo.setBounds(10, 123, 167, 20);
		panel_1.add(lblEcTiempo);
		
		textField_9 = new JTextField();
		textField_9.setBounds(10, 154, 167, 20);
		panel_1.add(textField_9);
		textField_9.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(29, 533, 610, 96);
		contentPane.add(panel_2);
		panel_2.setBorder(BorderFactory.createLineBorder(getForeground()));
		panel_2.setLayout(null);
		
		JLabel lblParaGenerarDatos = new JLabel("Para generar datos para todos los meses del a\u00F1o, haga click en el siguiente boton:");
		lblParaGenerarDatos.setBounds(79, 11, 468, 14);
		panel_2.add(lblParaGenerarDatos);
		
		JButton btnGenerar = new JButton("Generar");
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				object1=new Dgenerar(frame);
				object1.setVisible(true);
			}
		});
		btnGenerar.setBounds(255, 45, 135, 23);
		panel_2.add(btnGenerar);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 733, 21);
		contentPane.add(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmLimpiarCasillas = new JMenuItem("Limpiar casillas");
		mntmLimpiarCasillas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
				textField_8.setText("");
				textField_9.setText("");
				textField_10.setText("");
				textField_11.setText("");
				textField_12.setText("");
			}
		});
		mnArchivo.add(mntmLimpiarCasillas);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				System.exit(0);
			}
		});
		mnArchivo.add(mntmSalir);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				About ventana=new About(frame);
				ventana.setVisible(true);
			}
		});
		mnAyuda.add(mntmAcercaDe);
		
		JMenuItem mntmGlosarioDeTerminos = new JMenuItem("Glosario de t\u00E9rminos");
		mntmGlosarioDeTerminos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Glosario glossary = new Glosario();
				glossary.setVisible(true);
			}
		});
		mnAyuda.add(mntmGlosarioDeTerminos);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Coordenadas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(380, 287, 259, 235);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblEcuacinDelTiempo = new JLabel("Ascensi\u00F3n recta del sol:");
		lblEcuacinDelTiempo.setBounds(10, 22, 189, 14);
		panel_3.add(lblEcuacinDelTiempo);
		
		textField_8 = new JTextField();
		textField_8.setBounds(10, 45, 167, 20);
		panel_3.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Declinaci\u00F3n del sol");
		lblNewLabel.setBounds(10, 76, 140, 14);
		panel_3.add(lblNewLabel);
		
		textField_10 = new JTextField();
		textField_10.setBounds(10, 99, 167, 20);
		panel_3.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblAzimutDelSol = new JLabel("Azimut del sol");
		lblAzimutDelSol.setBounds(10, 133, 106, 14);
		panel_3.add(lblAzimutDelSol);
		
		textField_11 = new JTextField();
		textField_11.setBounds(10, 152, 167, 20);
		panel_3.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel lblAlturaDelSol = new JLabel("Altura del sol");
		lblAlturaDelSol.setBounds(10, 183, 106, 14);
		panel_3.add(lblAlturaDelSol);
		
		textField_12 = new JTextField();
		textField_12.setBounds(10, 204, 167, 20);
		panel_3.add(textField_12);
		textField_12.setColumns(10);
		
	}
}