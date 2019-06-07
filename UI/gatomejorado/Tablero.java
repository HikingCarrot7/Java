package gatomejorado;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class Tablero {
	
	protected int tablero[][];
	private int lado;
	private int desfase;
	
	private ImageIcon player1;
	private ImageIcon player2;
	
	public Tablero()
	{
		tablero = new int [3][3];
		
		tablero = initTablero(tablero);
		
		lado = 200;
		desfase = 200;
		
	}
	
	public void dibujar(Graphics2D g) 
	{
		
		for(int i = 0; i < tablero.length; i++) 
		{
			for(int j = 0; j < tablero.length; j++) 
			{
				if(tablero[i][j] == 0) 
				{
					g.setColor(Color.white);
					g.fillRect(i * lado + desfase , j * lado + desfase, lado, lado);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(i * lado + desfase , j * lado + desfase, lado, lado);
					
				}else if(tablero[i][j] == 1) 
				{
					player1 = new ImageIcon("src/gatomejorado/player1.png");
					g.setColor(Color.white);
					g.fillRect(i * lado + desfase , j * lado + desfase, lado, lado);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(i * lado + desfase , j * lado + desfase, lado, lado);
					player1.paintIcon(null, (Graphics) g, i * lado + desfase, j * lado + desfase);
					
				}else 
				{
					player2 = new ImageIcon("src/gatomejorado/player2.png");
					g.setColor(Color.white);
					g.fillRect(i * lado + desfase , j * lado + desfase, lado, lado);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(i * lado + desfase , j * lado + desfase, lado, lado);
					player2.paintIcon(null, (Graphics) g, i * lado + desfase, j * lado + desfase);
				}
			}
		}
	}
	
	private int[][] initTablero(int tablero[][])
	{
		for(int i = 0; i < tablero.length; i++) 
		{
			for(int j = 0; j < tablero.length; j++) 
			{
				tablero[i][j] = 0;
			}
		}
		
		return tablero;
		
	}
	
	public void modificarTablero(int x, int y, int num) 
	{
		
		tablero[x][y] = num;
		
	}

}
