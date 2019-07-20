package com.game.src.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.src.framework.GameObject;
import com.game.src.framework.ObjectId;

public class Crate extends GameObject
{

	public Crate(float x, float y, ObjectId id, Handler handler) 
	{
		super(x, y, id);
	}

	@Override
	public void tick() 
	{
		
	}

	@Override
	public void render(Graphics2D g) 
	{
		g.setColor(Color.cyan);
		g.fillRect((int) x, (int) y, 32, 32);
		
	}

	@Override
	public Rectangle getBounds() 
	{
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
