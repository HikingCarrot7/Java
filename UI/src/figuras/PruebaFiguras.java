package figuras;

import javax.swing.JFrame;

public class PruebaFiguras extends JFrame
{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) 
	{
		
		JFrame miFrame = new JFrame();
		Lamina miLamina = new Lamina();
		
		miFrame.setResizable(false);
		miFrame.setSize(800, 800);
		miFrame.setLocationRelativeTo(null);
		miFrame.setVisible(true);
		miFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		miFrame.add(miLamina);

	}

}
