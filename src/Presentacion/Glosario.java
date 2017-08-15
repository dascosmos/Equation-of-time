package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Glosario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList list;
	private JLabel lblNewLabel;
	private JLabel lblRef;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Glosario dialog = new Glosario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Glosario() {
		setTitle("Glosario");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Glosario.class.getResource("/Images/imgSunjpg.jpg")));
		setBounds(100, 100, 1194, 588);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(29, 102, 202, 328);
		
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		list.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Analema", "Ascensi\u00F3n recta", "Coordenadas ecuatoriales", "Declinaci\u00F3n", "D\u00EDa Juliano", "Ecuaci\u00F3n del tiempo", "Hora sideral"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}		
		});
		list.setSelectedIndex(-1);
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				switch(list.getSelectedIndex()){
				case 0:
					lblNewLabel.setIcon(new ImageIcon(About.class.getResource("/Images/Analema.jpg")));
					textArea.setText("Analema Solar:\n\n"
							+ "El analema muestra el movimiento aparente del sol visto desde\n"
							+ "la tierra. Si se tomara una forograf\u00eda del sol todos\n"
							+ "los d\u00edas a una hora determinada, la im\u00e1gen resultante\n"
							+ "ser\u00eda parecida a la que muestra la gr\u00e1fica.");
					lblRef.setText("http://tingilinde.typepad.com/photos/uncategorized/analemma.jpg");
					break;
				case 1:	
					lblNewLabel.setIcon(new ImageIcon(About.class.getResource("/Images/CoordenadasEc.jpg")));
					textArea.setText("Ascensi\u00F3n recta solar: \n\n"
							+ "La ascensi\u00F3n recta se mide a partir del punto Aries en\n "
							+ "horas (una hora igual a 15 grados), minutos y segundos \n "
							+ "hacia el Este a lo largo del ecuador celeste.\n\n "
							+ "El punto Aries (o punto Vernal) est\u00e1 en la posici\u00F3n del Sol en el \n "
							+ "equinoccio de Primavera o Equinoccio vernal. Su s\u00edmbolo es \u03B1");
					lblRef.setText("http://atacamaviva.cl/Jana/Imagenes/Conceptos/AscensionRectaYDeclinacion2.jpg");
					break;
				case 2:
					lblNewLabel.setIcon(new ImageIcon(About.class.getResource("/Images/Coordenadas_ecuatoriales.png")));
					textArea.setText("Coordenadas ecuatoriales (absolutas):\n\n"
							+ "Son un tipo de coordenadas celestes que muestran la\n"
							+ "posici\u00F3n de un objeto respecto al ecuador celeste\n"
							+ "y al punto aries (momento en el que el sol pasa de sur a norte).\n\n"
							+ "Sus 'ejes' se denominan Ascensi\u00F3n recta y declinaci\u00F3n. ");
					lblRef.setText("http://upload.wikimedia.org/wikipedia/commons/1/1c/Coordenadas_ecuatoriales.png");
					break;
				case 3:
					lblNewLabel.setIcon(new ImageIcon(About.class.getResource("/Images/CoordenadasEc.jpg")));
					textArea.setText("Declinaci\u00F3n solar:\n\n"
							+ "La declinación es el \u00e1ngulo que forma un astro con el ecuador celeste. \n "
							+ "Es una de las dos coordenadas del sistema de coordenadas ecuatoriales.\n"
							+ "La declinaci\u00F3n se mide en grados y es positiva si est\u00e1 al norte del\n"
							+ "ecuador celeste y negativa si est\u00e1 al sur.\n\n "
							+ "La declinaci\u00F3n es comparable a la latitud geogr\u00e1fica\n"
							+ "(que se mide sobre el ecuador terrestre).");
					lblRef.setText("http://atacamaviva.cl/Jana/Imagenes/Conceptos/AscensionRectaYDeclinacion2.jpg");
					break;
				case 4:
					lblNewLabel.setIcon(new ImageIcon(About.class.getResource("/Images/julio.jpg")));
					textArea.setText("D\u00eda Juliano:\n\n"
							+ "La fecha juliana (JD, por sus siglas en ingl\u00e9s) \n"
							+ "es el n\u00famero de d\u00edas y fracci\u00f3n transcurridos \n"
							+ "desde el mediod\u00eda del 1º de enero del año 4713 a. C.\n\n"
							+ "Es fuertemente utilizado en la astronom\u00eda\n"
							+ "debido a que los otros tipos de calendarios\n"
							+ "hacen m\u00e1s complejos los c\u00e1lculos.");
					lblRef.setText("http://www.tuhistory.com/files/julio_cesar-01.jpg");
					break;
				case 5:
					lblNewLabel.setIcon(new ImageIcon(About.class.getResource("/Images/sundial.jpg")));
					textArea.setText("Ecuaci\u00F3n del tiempo:\n\n"
							+ "Se entiende c\u00F3mo la diferencia que hay entre\n"
							+ "el tiempo solar aparente (movimiento real del sol) y\n"
							+ "el tiempo solar medio (el que se mide con un reloj).\n");
					lblRef.setText("http://ccphysics.us/henriques/a105l/sundial.jpg");
					break;
				case 6:
					lblNewLabel.setIcon(new ImageIcon(About.class.getResource("/Images/Tiempo_sidereo.png")));
					textArea.setText("Tiempo sid\u00e9reo:\n\n"
							+ "Es el tiempo que se toma no con respecto\n"
							+ "al sol, sino con respecto a un astro.\n"
							+ "A este punto de referencia se le llama\n"
							+ "punto vernal.\n\n"
							+ "Mientras que un d\u00eda solar dura 24h,\n"
							+ "un d\u00eda sid\u00e9reo dura apenas 23h 56m,\n"
							+ "esto sirve para lograr la medici\u00f3n de la\n"
							+ "posici\u00f3n de los astros.");
					lblRef.setText("http://upload.wikimedia.org/wikipedia/commons/9/9d/Tiempo_sid%C3%A9reo.png");
					break;
				}
			}
		});
		
	
		
		
		
		panel.add(list, BorderLayout.CENTER);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(625, 11, 453, 456);
		lblNewLabel.setIcon(new ImageIcon(About.class.getResource("/Images/imgSunJava.jpg")));
		contentPanel.add(lblNewLabel);
		
		lblRef=new JLabel("");
		lblRef.setBounds(625,458,500,14);
		contentPanel.add(lblRef);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(265, 103, 323, 327);
		contentPanel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblSeleccioneUnElemento = new JLabel("Seleccione un elemento de la lista:");
		lblSeleccioneUnElemento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSeleccioneUnElemento.setBounds(29, 48, 289, 14);
		contentPanel.add(lblSeleccioneUnElemento);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
