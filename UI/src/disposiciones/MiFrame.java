package disposiciones;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MiFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	Lamina lamina;
	Lamina2 lamina2;
	
	public MiFrame()
	{
		lamina = new Lamina();
		lamina2 = new Lamina2();
		
		setBounds(0, 0, 500, 500);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(true);
		add(lamina, BorderLayout.NORTH);
		add(lamina2, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public static void main(String[] args) 
	{

		new MiFrame();

	}

}
