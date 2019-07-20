package figuras;

import java.awt.Color;
import java.awt.Graphics;

public class Circulos extends Posiciones
{
	
	private int radio;
	
	public Circulos(int x, int y, int radio, Color color) 
	{
		super(x, y, color);
		
		this.radio = radio;
		
	}
	
	public void dibujar(Graphics g) 
	{
		g.setColor(getColor());
		g.drawOval(getXPos(), getYPos(), radio, radio);
		g.drawString(getContFig(), getXPos() + 10, getYPos() + 10);
		
	}

}
