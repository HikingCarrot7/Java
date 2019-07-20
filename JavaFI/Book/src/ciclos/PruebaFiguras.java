package ciclos;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PruebaFiguras
{
	public static void main(String[] args) 
	{
		String in = JOptionPane.showInputDialog("Escriba 1 para dibujar rectánguloa\n" + "Escriba 2 para dibujar óvalos");
		
		int option = Integer.parseInt(in);
		
		Figuras panel = new Figuras(option);
		
		JFrame aplication = new JFrame();
		
		aplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aplication.add(panel);
		aplication.setVisible(true);
		aplication.setSize(300, 300);
	}
}
