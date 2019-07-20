package graphics2d;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class PruebaShape2D
{

	public static void main(String[] args) 
	{
		
		JFrame miFrame = new JFrame();
		Lamina miLamina = new Lamina();
		
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		
		Dimension tamanoPantalla = miPantalla.getScreenSize();
		
		miFrame.setBounds(tamanoPantalla.width/4, tamanoPantalla.height/4, tamanoPantalla.width/2, tamanoPantalla.height/2);
		miFrame.setResizable(false);
		miFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		miFrame.setVisible(true);
		miFrame.add(miLamina);
		

	}

}
