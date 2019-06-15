package administracion;

import javax.swing.JFrame;

public class Principal extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private LaminaMaestro laminaMaestro;
	
	protected static Principal principal;
	
	public Principal() 
	{
		
		laminaMaestro = new LaminaMaestro();
		
		setBounds(0, 0, 800, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Gestionar alumnos");
		add(laminaMaestro);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(false);
		new Login();
	}

	public static void main(String[] args) 
	{
		principal = new Principal();

	}

}
