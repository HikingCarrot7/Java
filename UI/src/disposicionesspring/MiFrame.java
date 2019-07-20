package disposicionesspring;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MiFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	Lamina lamina;
	
	public MiFrame() 
	{
		lamina = new Lamina();
		
		setBounds(0, 0, 500, 500);
		setLocationRelativeTo(null);
		setResizable(true);
		setTitle("Disposiciones Spring");
		setMinimumSize(new Dimension(300, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(lamina);
		setVisible(true);
	}

	public static void main(String[] args) 
	{
		new MiFrame();

	}

}