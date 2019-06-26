package gatomejorado;

import javax.swing.JFrame;

public class Marco extends JFrame
{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) 
	{
		Marco marco = new Marco();
		
		Lamina miLamina = new Lamina();
		
		marco.setBounds(0, 0, 1000, 1000);
		marco.setVisible(true);
		marco.setTitle("Tic Tac Toe");
		marco.setResizable(false);
		marco.add(miLamina);
		marco.addKeyListener(miLamina);
		marco.addMouseListener(miLamina);
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
