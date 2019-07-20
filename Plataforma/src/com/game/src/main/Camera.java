package com.game.src.main;

import com.game.src.framework.GameObject;

public class Camera 
{
	
	private float x, y;
	
	private boolean noMover = false;
	
	public Camera(float x, float y) 
	{
		this.x = x;
		this.y = y;
	}
	
	public void tick(GameObject player) 
	{
		//x = (- player.getX() + Game.ANCHO/2)/2;
		
		if(!noMover) 
		{
			float xTarg = (- player.getX() + Game.ANCHO/2)/2;
			
			x += (xTarg - x) * (0.05);
			
			y = ((- player.getY() + Game.ALTO/2)/2);
			
		}
		
	}

	public float getX() 
	{
		return x;
	}

	public void setX(float x) 
	{
		this.x = x;
	}

	public float getY() 
	{
		return y;
	}

	public void setY(float y) 
	{
		this.y = y;
		
		noMover = true;

		
	}
	
}
