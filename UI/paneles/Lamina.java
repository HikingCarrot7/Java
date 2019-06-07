package paneles;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Lamina extends JPanel{
	
	
	public void paintComponent(Graphics g) 
	{
		
		Color color = new Color(234, 56, 67);
		
		setBackground(color);
		
		super.paintComponent(g);
		
		g.drawString("Aún no podemos hacer mucho", 50, 50);
		
	}
}
