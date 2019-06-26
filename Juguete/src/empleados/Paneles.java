package empleados;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Paneles extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	private LaminaSoporte lamina;
	
	public Paneles() 
	{
		lamina = new LaminaSoporte();
		
		setBounds(0, 0, 550, 550);
		setLocationRelativeTo(null);
		setResizable(false);
		add(lamina);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
	}

	public static void main(String[] args) 
	{	
		new Paneles();
	}
}

class LaminaSoporte extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private Circulos circulo;
	
	private Lineas lineas;
	
	public LaminaSoporte() 
	{
		setLayout(null);
		
		anadirLaminas();
		
		add(circulo);
		
		add(lineas);	
	}
	
	public void anadirLaminas() 
	{
		circulo = new Circulos();
		
		lineas = new Lineas();
		
		circulo.setBounds(50, 100, 200, 200);
		
		lineas.setBounds(300, 100, 200, 200);
		
		setBackground(Color.cyan);	
	}
}

class Circulos extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private Timer timer;
	
	private int xPos = 50, yPos = 40, velocidadX = -5, velocidadY = -8;
	
	public Circulos() 
	{
		timer = new Timer(8, (ActionEvent e) ->
		{	
			choques();
			
			repaint();
		});
		
		timer.start();
	}
	
	@Override
	public void paint(Graphics g) 
	{
		super.paint(g);
		
		g.setColor(Color.blue);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.green);
		g.fillOval(xPos += velocidadX, yPos += velocidadY, 30, 30);
		
		g.dispose();	
	}
	
	public void choques() 
	{
		if(xPos < 0 || xPos > getWidth() - 30)
			velocidadX *= -1;
		
		if(yPos < 0 || yPos > getHeight() - 30)
			velocidadY *= -1;
	}
}

class Lineas extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private Timer timer;
	
	private int razon = 0;
	
	public Lineas() 
	{
		timer = new Timer(8, (ActionEvent e) ->
		{
			repaint();
		});
		
		timer.start();
	}
	
	@Override
	public void paint(Graphics g) 
	{	
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.yellow);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g2.rotate(Math.toRadians(razon += 5), getWidth()/2,getHeight()/2);
		
		g.setColor(Color.black);
		g.drawLine(20, getHeight()/2, getWidth() - 20, getHeight()/2);
		
		g.dispose();	
	}
}
