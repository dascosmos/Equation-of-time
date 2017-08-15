package Presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class About extends JFrame {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 * @param frame 
	 */
	public About(EcTiempo frame) {
		setTitle("About");
		this.setResizable(false);
		setBounds(100, 100, 880, 479);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(About.class.getResource("/Images/imgSunJava.jpg")));
		lblNewLabel.setBounds(24, 39, 340, 340);
		contentPanel.add(lblNewLabel);
		
		JLabel lblAcercaDe = new JLabel("Acerca de");
		lblAcercaDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcercaDe.setFont(new java.awt.Font("Tahoma", 0, 30)); 
		lblAcercaDe.setBounds(463, 41, 178, 74);
		contentPanel.add(lblAcercaDe);
		
		JLabel lblProgramaRealizadoPor = new JLabel("Programa realizado por: David Alejandro Salazar Sarmiento");
		lblProgramaRealizadoPor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProgramaRealizadoPor.setBounds(393, 125, 406, 30);
		contentPanel.add(lblProgramaRealizadoPor);
		
		JLabel lblObserv=new JLabel("Observatorio astron\u00F3mico Universidad Sergio Arboleda");
		lblObserv.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblObserv.setBounds(393, 155, 406, 22);
		contentPanel.add(lblObserv);
		
		JLabel lblAgr=new JLabel("Agradecimientos");
		lblAgr.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAgr.setBounds(393, 190, 406, 22);
		contentPanel.add(lblAgr);
		
		JLabel lblJuan=new JLabel("Ing. Juan Carlos Cuervo Marulanda - Planetarista del observatorio");
		lblJuan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJuan.setBounds(393, 215, 450, 22);
		contentPanel.add(lblJuan);
		
		JLabel lblRaul=new JLabel("Ing. Ra\u00fal Andr\u00e9s Joya Olarte - Director del observatorio");
		lblRaul.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRaul.setBounds(393, 240, 406, 22);
		contentPanel.add(lblRaul);
		
		JLabel lblReferencedLibraries = new JLabel("Referenced Libraries");
		lblReferencedLibraries.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReferencedLibraries.setBounds(393, 280, 194, 22);
		contentPanel.add(lblReferencedLibraries);
		
		JLabel lblNewLabel_1 = new JLabel("JExcel api - http://jexcelapi.sourceforge.net/");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(393, 300, 406, 30);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblJmathplotHttpscodegooglecompjmathplot = new JLabel("JMathPlot - https://code.google.com/p/jmathplot/");
		lblJmathplotHttpscodegooglecompjmathplot.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJmathplotHttpscodegooglecompjmathplot.setBounds(393, 323, 406, 22);
		contentPanel.add(lblJmathplotHttpscodegooglecompjmathplot);
		
		JLabel lblNewLabel_2 = new JLabel("JMathArray - https://code.google.com/p/jmatharray/");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(393, 340, 354, 22);
		contentPanel.add(lblNewLabel_2);
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
