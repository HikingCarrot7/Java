package grafos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Lamina extends JPanel implements MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = 1L;

	private Timer timer;
	
	protected static ArrayList<Integer> xPos, yPos;
	
	protected static int[][] matrizAdyacencia;
	
	protected int posicion = -1, x, y;
	
	protected boolean moviendoVertice = false;
	protected boolean anadirArista = false;
	
	public Lamina() 
	{
		xPos = new ArrayList<>();
		yPos = new ArrayList<>();
		matrizAdyacencia = new int[50][50];
		
		timer = new Timer(8, (ActionEvent e) ->
		{
			repaint();
		});
		
		timer.start();
	}
	
	
	public void dibujarVertices(Graphics2D g) 
	{
		if(xPos.size() > 0) 
			for(int i = 0; i < xPos.size(); i++) 
				if(Colores.coloresVert[i] == 2) 
				{
					pintarVertices(Color.red, i, g);
					
				}else if(Colores.coloresVert[i] == 3)
				{
					pintarVertices(Color.yellow, i, g);
					
				}else if (Colores.coloresVert[i] == 4)
				{
					pintarVertices(Color.green, i, g);
					
				}else 
				{
					pintarVertices(Color.blue, i, g);
				}
	}
	
	public void pintarVertices(Color color, int i, Graphics2D g) 
	{
		g.setColor(Color.orange);
		g.fillOval(xPos.get(i), yPos.get(i), 50, 50);
		g.setStroke(new BasicStroke(5));
		g.setColor(color);
		g.drawOval(xPos.get(i), yPos.get(i), 50, 50);
	}
	
	public void dibujarAristas(Graphics2D g) 
	{
		g.setColor(Color.red);
		g.setStroke(new BasicStroke(5));

		for(int i = 0; i < xPos.size(); i++)
			for(int j = 0; j < xPos.size(); j++)
				if(matrizAdyacencia[i][j] == 1) 
					g.drawLine(xPos.get(i) + 25, yPos.get(i) + 25, xPos.get(j) + 25, yPos.get(j) + 25);
	}
	
	public void dibujarLinea(Graphics2D g) 
	{
		g.setColor(Color.red);
		g.setStroke(new BasicStroke(5));
		
		if(anadirArista) 
			g.drawLine(xPos.get(posicion) + 25, yPos.get(posicion) + 25 , x, y);
	}
	
	@Override
	public void paint(Graphics g) 
	{
		super.paint(g);
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 800);
		
		dibujarAristas((Graphics2D) g);
		dibujarLinea((Graphics2D) g);
		dibujarVertices((Graphics2D) g);
		
		g.dispose();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		anadirVertice(e);
		anadirArista(e);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		if(moviendoVertice) 
		{
			xPos.set(posicion, e.getX() - 25);
			yPos.set(posicion, e.getY() - 50);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		moverVertice(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		if(moviendoVertice) 
		{
			moviendoVertice = false;
			
			posicion = -1;
		}	
	}
	
	public void anadirVertice(MouseEvent e) 
	{
		if(e.getButton() == 1 && !anadirArista) 
		{
			posicion = DistanciasPuntos.mouseTocandoVert(e.getX() - 25, e.getY() - 50);
			
			if(e.getButton() == 1 && posicion < 0) 
			{
				xPos.add(e.getX()- 25);
				yPos.add(e.getY()- 50);
				
				Colores.colores();	
			}			
		}
	}
	
	public void moverVertice(MouseEvent e) 
	{
		if(e.getButton() == 1 && !anadirArista) 
		{
			posicion = DistanciasPuntos.mouseTocandoVert(e.getX() - 25, e.getY() - 50);
			
			if(posicion >= 0) 
			{
				moviendoVertice = true;
			}
		}
	}
	
	public void anadirArista(MouseEvent e) 
	{
		if(e.getButton() == 3 && !anadirArista) 
		{
			posicion = DistanciasPuntos.mouseTocandoVert(e.getX() - 25, e.getY() - 50);
			
			if(posicion >= 0) 
			{
				anadirArista = true;
				
				x = e.getX();
				y = e.getY();
			}
			
		}else if(e.getButton() == 3 && anadirArista) 
		{
			int vertice2 =  DistanciasPuntos.mouseTocandoVert(e.getX() - 25, e.getY() - 50);
			
			if(vertice2 != posicion && vertice2 >= 0 && vertice2 <= 25) 
			{
				matrizAdyacencia[posicion][vertice2] = 1;
				matrizAdyacencia[vertice2][posicion] = 1;
				
				Colores.colores();
				
				anadirArista = false;
			}
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) 
	{
		if(anadirArista) 
		{
			x = e.getX();
			y = e.getY();
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}
}
