package figuras;

import java.awt.Color;
import java.awt.Graphics;

public class Cuadrados extends Posiciones{
	
	private int lado;
	
	public Cuadrados(int x, int y, int lado, Color color) 
	{
		
		super(x, y, color);
		
		this.lado = lado;
		
	}
	
	public void dibujar(Graphics g) 
	{
		g.setColor(getColor());
		g.drawRect(getXPos(), getYPos(), lado, lado);
		g.drawString(getContFig(), getXPos() + 10, getYPos() + 10);
		
	}
	

}
