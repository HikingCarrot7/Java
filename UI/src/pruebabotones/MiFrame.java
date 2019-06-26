package pruebabotones;

import javax.swing.JFrame;

public class MiFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	LaminaBotones lamina;
	
	public MiFrame() 
	{
		lamina = new LaminaBotones();
		
		setBounds(0, 0, 500, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(lamina);
		setVisible(true);
	}

	public static void main(String[] args) 
	{
		new MiFrame();

	}

}
