package movimiento;

import javax.swing.JFrame;

public class MiFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) 
	{
		JFrame miFrame = new JFrame();
		Lamina miLamina = new Lamina();
		
		miFrame.setSize(800, 800);
		miFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		miFrame.setResizable(false);
		miFrame.add(miLamina);
		miFrame.setVisible(true);
	}
	
}
