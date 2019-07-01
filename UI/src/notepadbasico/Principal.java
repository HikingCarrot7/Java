package notepadbasico;

import javax.swing.JFrame;

public class Principal extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	Lamina lamina;
	
	public Principal() 
	{
		lamina = new Lamina();
		
		setBounds(0, 0, 500, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Notepad básico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(lamina);
		setVisible(true);
	}

	public static void main(String[] args) 
	{
		new Principal();

	}

}
