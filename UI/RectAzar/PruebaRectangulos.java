package RectAzar;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class PruebaRectangulos {
	
	public static void main(String[] args) 
	{
		
		JFrame miFrame = new JFrame();
		LaminaRect miLamina1 = new LaminaRect();
		
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		
		Dimension tamanoPantalla = miPantalla.getScreenSize();
		
		miFrame.setBounds(tamanoPantalla.width/4, tamanoPantalla.height/4, tamanoPantalla.width/2, tamanoPantalla.height/2);
		miFrame.setVisible(true);
		miFrame.add(miLamina1);
		miFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}

}
