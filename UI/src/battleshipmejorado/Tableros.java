package battleshipmejorado;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Tableros 
{
	
	private int[][] tableroJugador, tableroEnemigo;
	
	private int ladoCasilla, desfaceVert, desfaceHor;
	
	public Tableros() 
	{
		
		tableroJugador = new int[12][22];
		tableroEnemigo = new int[12][22];
		
		initTableros(tableroJugador);
		initTableros(tableroEnemigo);
		
		ladoCasilla = 30;
		
		desfaceVert = 100;
		desfaceHor = 200;

	}
	
	public void dibujar(Graphics2D g) 
	{
		
		dibujarTableros(tableroJugador, desfaceHor, desfaceVert, g);
		
		dibujarTableros(tableroEnemigo, desfaceHor, desfaceVert + 400, g);
		
	}
	
	public void dibujarTableros(int[][] tablero, int desfaceHor, int desfaceVert, Graphics2D g) 
	{
		for(int i = 1; i < tablero.length - 1; i++ ) 
		{
			for(int j = 1; j < tablero[0].length - 1; j++) 
			{
				if(tablero[i][j] == 0) 
				{
					g.setColor(Color.white);
					g.fillRect(j * ladoCasilla + desfaceHor, i * ladoCasilla + desfaceVert, ladoCasilla, ladoCasilla);
					g.setColor(Color.black);
					g.setStroke(new BasicStroke(3));
					g.drawRect(j * ladoCasilla + desfaceHor, i * ladoCasilla + desfaceVert, ladoCasilla, ladoCasilla);
					
				}else if(tablero[i][j] == 1) 
				{
					g.setColor(Color.red);
					g.fillRect(j * ladoCasilla + desfaceHor, i * ladoCasilla + desfaceVert, ladoCasilla, ladoCasilla);
					g.setColor(Color.black);
					g.setStroke(new BasicStroke(3));
					g.drawRect(j * ladoCasilla + desfaceHor, i * ladoCasilla + desfaceVert, ladoCasilla, ladoCasilla);
				}
			}
		}
	}
	
	public int[][] initTableros(int[][] tablero)
	{
		
		for(int i = 0; i < tablero.length; i++ ) 
		{
			for(int j = 0; j < tablero[0].length; j++) 
			{
				tablero[i][j] = 0;
			}
		}
		
		return tablero;
	}
	
	public void modificarTableroEnemigo(int num, int x, int y) 
	{
		
		tableroJugador[x][y] = num;
		
	}
}
