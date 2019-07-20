package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.game.src.main.interfaces.EntityB;

public class Enemy extends GameObject implements EntityB
{
	private BufferedImage enemy;
	
	private Random rand;
	
	private int velocidad;
	
	private Controller controller;
	
	private Game game;
	
	private static int enemigosTotales = 5;

	public Enemy(double x, double y, Controller controller, Game game) 
	{
		super(x, y);
		
		this.controller = controller;
		
		this.game = game;
		
		rand = new Random();
		
		velocidad = rand.nextInt(3) + 2;
		
		enemy = new BufferedImageLoader().loadImage("/res/enemies/enemy1.png");
	}
	
	@Override
	public void tick() 
	{
		y += velocidad;
		
		if(y > Game.LARGO * Game.ESCALA) 
		{
			y = 0;
			
			velocidad = rand.nextInt(3) + 2;
			
			x = rand.nextInt(Game.ANCHO * Game.ESCALA - 60);
		}
		
		for(int i = 0; i < controller.obtenerEntidadesA().size(); i++) 
		{
			if(Physics.Collision(controller.obtenerEntidadesA().get(i), this)) 
			{
				game.setCoordenadasExp(true, (int) getX(), (int) getY());
				
				game.setEnemigosDestruidos(game.getEnemigosDestruidos() + 1);
				
				controller.removeEntity(controller.obtenerEntidadesB(), this);
				
				controller.removeEntity(controller.obtenerEntidadesA(), controller.obtenerEntidadesA().get(i));
			}
		}
		
	}
	
	@Override
	public void render(Graphics g) 
	{
		g.drawImage(enemy, (int) x, (int) y, null);
	}

	@Override
	public double getX() 
	{
		return x;
	}

	@Override
	public double getY() 
	{	
		return y;
	}

	@Override
	public Rectangle getBounds(int ancho, int altura) 
	{
		return new Rectangle((int) x, (int) y, ancho, altura);
	}
	
	public static int getEnemigosTotales() 
	{
		return enemigosTotales;
	}
	
	public static void sumarEnemigosTotales(int e) 
	{
		enemigosTotales += e;	
	}
	
	public static void setEnemigosTotales(int e) 
	{
		enemigosTotales = e;	
	}

}
