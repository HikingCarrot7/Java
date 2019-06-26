package grafos;

import javax.swing.JFrame;

public class Principal extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private Lamina lamina;
	
	public Principal() 
	{
		lamina = new Lamina();
		
		setBounds(0, 0, 800, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Grafos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(lamina);
		addMouseMotionListener(lamina);
		add(lamina);
		setVisible(true);
	}
	
	public static void main(String[] args) 
	{	
		new Principal();
	}
}
