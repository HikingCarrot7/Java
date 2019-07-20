package com.game.src.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.framework.GameObject;
import com.game.src.framework.ObjectId;
import com.game.src.main.Game;
import com.game.src.menus.MainMenu;
import com.game.src.objects.Handler;

public class MenuBackground extends GameObject
{
	private Color color;
	private Random rand;
	private Handler handler;
	private MainMenu menu;
	
	public MenuBackground(float x, float y, ObjectId id, Handler handler, MainMenu menu) 
	{
		super(x, y, id);
		
		rand = new Random();
		int dir = rand.nextInt(2);
		
		velX = dir == 0 ? 8 : 10;
		velY = dir == 0 ? 10 : 8;
		
		this.handler = handler;
		this.menu = menu;
		color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
	}

	@Override
	public void tick() 
	{
		x += velX;
		y += velY; 
		
		if(y < 0 || y > Game.ALTO - 25) velY *= -1;
		if (x < 0 || x > Game.ANCHO - 32) velX *= -1;
		
		/*for (int i = 0; i < menu.rectangles.length; i++) 
		{
			if(menu.rectangles[i].intersects(getBounds()) ) velY *= -1;
			if (menu.rectangles[i].intersects(getBounds())) velX *= -1;
		}*/
		
		
		
		handler.addObject(new Trail(x, y, ObjectId.Trail, handler, color, 16, 16, 0.03f));
		
	}

	@Override
	public void render(Graphics2D g) 
	{
		g.setColor(color);
		g.fillRect((int) x, (int) y, 16, 16);
	}

	@Override
	public Rectangle getBounds() 
	{
		return new Rectangle((int) x, (int) y, 16, 16);
	}

}