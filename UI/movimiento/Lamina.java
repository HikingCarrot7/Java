package movimiento;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Lamina extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	//Circular
	private int rx = 50;
	private int ry = 50;
	
	Point centro;
	Point movible;
	
	private double t = 0; 
	
	//Seno Horizontal
	private int velocidad;
	private int x;
	private int y;
	
	//Seno Vertical
	private int velocidad2;
	private int x2;
	private int y2;
	
	private Rectangle rebote;
	private int velocidadX;
	private int velocidadY;
	
	//Cambiar Tamaño
	private int cambio;
	private int razon;
	
	private Timer timer;
	
	public Lamina()
	{
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(60, this);
		
		//Circular
		centro = new Point(700, 100);
		
		//Seno horizontal
		x = 0;
		y = 400;
		velocidad = 10;
		
		//Seno vertical
		x2 = 400;
		y2 = 0;
		velocidad2 = 5;
		
		//Rebote
		rebote = new Rectangle(10, 10, 10, 10);
		velocidadX = 10;
		velocidadY = 15;
		
		//Cambiar Tamaño
		cambio = 20;
		razon = 2;

		timer.start();
		
	}
	
	public void paint(Graphics g1) 
	{
		
		Graphics2D g = (Graphics2D) g1;
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 800);
		
		movCircular(g);
		
		movSenoHor(g);
		
		movSenoVer(g);
		
		rebotar(g);
		
		cambiarTamano(g);
		
		g.dispose();
	}
	
	public void movCircular(Graphics2D g) 
	{
		g.setColor(Color.green);
		
		g.drawOval((int) (centro.x - rx), (int) (centro.y -ry), 2 * rx, 2 * ry);
		
		movible = trayectoria(t -= 0.1);
		
		g.fillOval(movible.x, movible.y, 10, 10);
	}
	
	public void movSenoHor(Graphics2D g) 
	{
		g.setColor(Color.yellow);
		g.fillOval(x, (int) (y - Math.sin(Math.toRadians(x)) * 100), 10, 10);
		
		if(x > 780 || x < 0) 
		{
			velocidad *= -1;
			
		}
		
		x += velocidad;
	}
	
	public void movSenoVer(Graphics2D g)
	{
		g.setColor(Color.blue);
		g.fillOval((int) (x2 - Math.sin(Math.toRadians(y2)) * 100), y2, 10, 10);
		
		if(y2 > 780 || y2 < 0) 
		{
			velocidad2 *= -1;
			
		}
		
		y2 += velocidad2;
	}
	
	public void rebotar(Graphics2D g) 
	{
		g.setColor(Color.pink);
		g.fillOval(rebote.x, rebote.y, 10, 10);
		
		if(rebote.x > 780 || rebote.x < 0) 
		{
			velocidadX *= -1;
			
		}else if(rebote.y > 770 || rebote.y < 0) 
		{
			velocidadY *= -1;
		}
		
		rebote.x += velocidadX;
		rebote.y += velocidadY;
	}
	
	public void cambiarTamano(Graphics2D g) 
	{
		g.setColor(Color.red);
		g.fillOval(100 - cambio, 650 - cambio, 10 + cambio * 2, 10 + cambio * 2);
		
		if(cambio > 50 || cambio < 20) 
		{
			razon *= -1;
		}
		
		cambio += razon;
	}

	public Point trayectoria(double t) 
	{
		
		return new Point((int)(rx * Math.cos(t) + centro.x), (int) (ry * Math.sin(t) + centro.y));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		repaint();
	}
}
