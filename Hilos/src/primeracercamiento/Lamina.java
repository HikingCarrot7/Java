package primeracercamiento;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Lamina extends JPanel 
{
	private static final long serialVersionUID = 1L;

	private class Pelota
	{
		private int xPos, yPos, velX, velY;
		
		public Pelota(int xPos, int yPos, int velX, int velY) 
		{
			this.xPos = xPos;
			this.yPos = yPos;
			this.velX = velX;
			this.velY = velY;
		}
		
		public void setVelX() 
		{
			velX *= -1;
		}
		
		public void setVelY() 
		{
			velY *= -1;
		}
		
		public void dibujarPelota(Graphics g) 
		{
			setBackground(Color.black);
			
			g.setColor(new Color(new SecureRandom().nextInt(255), new SecureRandom().nextInt(255), new SecureRandom().nextInt(255)));
			g.fillOval(xPos += velX, yPos += velY, 40, 40);
			
			choques();
			
			//g.dispose();
		}
		
	}
	
	private JButton dale;
	
	private JPanel pelota, boton;
	
	//private Timer timer;
	
	private ArrayList<Pelota> pelotas;
	
	private ArrayList<Thread> hilos;
	
	public Lamina() 
	{
		setLayout(new BorderLayout());
		
		anadirElementos();
		
		pelota.setBackground(Color.black);
		
		/*timer = new Timer(8, (ActionEvent e) ->
		{
			repaint();
		});
		
		timer.start();*/
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		/*for(Pelota P: pelotas)
			P.dibujarPelota(g);*/
		
		//choques();
		
		//g.dispose();
		
	}
	
	public void choques() 
	{
		for(int i = 0; i < pelotas.size(); i++) 
		{
			if(pelotas.get(i).xPos > 500 || pelotas.get(i).xPos < 0)
				pelotas.get(i).setVelX();
			
			if(pelotas.get(i).yPos > 500 || pelotas.get(i).yPos < 0)
				pelotas.get(i).setVelY();	
		}
	}
	
	public void anadirElementos() 
	{
		pelota = new JPanel();
		boton = new JPanel();
		
		pelotas = new ArrayList<>();
		
		hilos = new ArrayList<>();
		
		dale = new JButton("Dale!");
		
		dale.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				pelotas.add(new Pelota(20, 20 , 4, 6));	
				
				Thread thread = new Thread(new muevePelota(pelotas.get(pelotas.size() - 1)));
				
				thread.start();
				
				hilos.add(thread);
				
			}
			
		});
		
		boton.add(dale);
		
		add(pelota, BorderLayout.CENTER);
		add(boton, BorderLayout.SOUTH);
		
	}
	
	private class muevePelota implements Runnable
	{	
		private Pelota pelota;

		public muevePelota(Pelota pelota) 
		{
			this.pelota = pelota;
		}

		@Override
		public void run() 
		{
			while(true) 
				try 
				{
					Thread.sleep(8);
					
					pelota.dibujarPelota(getGraphics());
					
					//repaint();
					
				} catch (InterruptedException e) 
				{
					
				}
			
		}
		
	}
	
}
