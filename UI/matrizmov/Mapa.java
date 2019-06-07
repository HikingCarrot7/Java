package matrizmov;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Mapa 
{
	
	private int map[][];
	private int ladrilloLargo;
	private int ladrilloAlto;
	
	public Mapa()
	{
		map = new int [10][20];
		map = rellenarMatriz(map);
		ladrilloLargo = 20;
		ladrilloAlto = 20;
		
	}
	
	public void draw(Graphics2D g, int mov) 
	{
		for(int i = 0; i < map.length; i++) 
		{
			for(int j = 0; j < map[0].length; j++) 
			{
				if(map[i][j] > 0) 
				{
					g.setColor(Color.black);
					g.fillRect(j * ladrilloLargo, i * ladrilloAlto + mov, ladrilloLargo, ladrilloAlto);
					
					g.setColor(Color.pink);
					g.setStroke(new BasicStroke(3));
					g.drawRect(j * ladrilloLargo , i * ladrilloAlto + mov, ladrilloLargo, ladrilloAlto);
					
				}else 
				{
					g.setColor(Color.green);
					g.fillRect(j * ladrilloLargo, i * ladrilloAlto + mov, ladrilloLargo, ladrilloAlto);
				}
				
			}
		}
		
		g.dispose();
		
	}
	
	public int[][] rellenarMatriz(int map[][]) 
	{
		for(int i = 0; i < map.length; i++) 
		{
			for(int j = 0; j < map[0].length; j++) 
			{
				map[i][j] = 1;
			}
		}
		
		return map;
		
	}
	
	public void modificarMapa(int x, int y)
	{
		if(x < 20 && x >= 0 && y < 10 && y >= 0) 
		{
			map[y][x] = 0;
		}
		
	}
	

}


