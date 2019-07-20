package disposicionesavanzadas;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Lamina extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private Box boxnombre, boxcontrasena, boxbotones, boxsoporte;
	
	private JLabel infoNombre, infoPass;
	
	private JTextField nombre;
	
	private JPasswordField password;

	private JButton aceptar, salir;
	
	public Lamina() 
	{
		setLayout(new BorderLayout());
		
		iniciarElementos();
		
		anadirElementos();
		
		add(boxsoporte, BorderLayout.CENTER);
		
	}
	
	public void iniciarElementos()
	{
		boxnombre = Box.createHorizontalBox();
		boxcontrasena = Box.createHorizontalBox();
		boxbotones = Box.createHorizontalBox();
		
		boxsoporte = Box.createVerticalBox();
		
		infoNombre = new JLabel("Nombre: ");
		
		infoPass = new JLabel("Contraseña: ");
		
		nombre = new JTextField(10);
		nombre.setMaximumSize(nombre.getPreferredSize());
		
		password = new JPasswordField(10);
		password.setMaximumSize(password.getPreferredSize());
		
		aceptar = new JButton("Aceptar");
		
		salir = new JButton("Salir");
		
	}
	
	public void anadirElementos() 
	{
		boxnombre.add(infoNombre);
		boxnombre.add(Box.createHorizontalStrut(15));
		boxnombre.add(nombre);
		
		boxcontrasena.add(infoPass);
		boxcontrasena.add(Box.createHorizontalStrut(15));
		boxcontrasena.add(password);
		
		boxbotones.add(aceptar);
		boxbotones.add(Box.createGlue());
		boxbotones.add(salir);
		
		boxsoporte.add(boxnombre);
		boxsoporte.add(boxcontrasena);
		boxsoporte.add(boxbotones);
		
	}

}
