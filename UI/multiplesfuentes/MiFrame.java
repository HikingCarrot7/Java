package multiplesfuentes;

import javax.swing.JFrame;

public class MiFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;

	public MiFrame() 
	{
		
		Lamina lamina = new Lamina();
		
		this.setBounds(0, 0, 500, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(lamina);
		
	}

}
