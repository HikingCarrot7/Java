package disposicionespropias;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Lamina extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JLabel infonombre, infopassword, infoedad;
	
	private JTextField nombre, edad;
	
	private JPasswordField password;
	
	public Lamina() 
	{
		setLayout(new Distribucion());
		
		iniciarElementos();
		
		ponerElementos();
		
	}
	
	public void iniciarElementos() 
	{
		infonombre = new JLabel("Nombre: ");
		infopassword = new JLabel("Contraseña: ");
		infoedad = new JLabel("Edad: ");
		
		nombre = new JTextField();
		password = new JPasswordField();
		edad = new JTextField();
	}
		
	public void ponerElementos() 
	{
		add(infonombre);
		add(nombre);
		add(infoedad);
		add(edad);
		add(infopassword);
		add(password);
	}
		
}
