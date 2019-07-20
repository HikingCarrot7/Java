package com.game.src.framework;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class GameObject 
{
	
	protected float x, y;
	
	protected ObjectId id;
	
	protected float velX = 0, velY = 0;
	
	protected boolean falling = true, jumping = true; 
	
	protected int facing = 1;
	
	public GameObject(float x, float y, ObjectId id) 
	{
		this.x = x;
		this.y = y;
		
		this.id = id;
		
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics2D g);
	
	public abstract Rectangle getBounds();
	
	public float getX() 
	{
		return x;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public float getY() 
	{
		return y;
	}

	public void setY(int y) 
	{
		this.y = y;
	}

	public float getVelX() 
	{
		return velX;
	}

	public void setVelX(float velX)
	{
		this.velX = velX;
	}

	public float getVelY() 
	{
		return velY;
	}

	public void setVelY(float velY) 
	{
		this.velY = velY;
	}

	public boolean isFalling() 
	{
		return falling;
	}

	public void setFalling(boolean falling) 
	{
		this.falling = falling;
	}

	public int getFacing() 
	{
		return facing;
	}

	public void setFacing(int facing) 
	{
		this.facing = facing;
	}

	public boolean isJumping() 
	{
		return jumping;
	}

	public void setJumping(boolean jumping) 
	{
		this.jumping = jumping;
	}

	public ObjectId getId() 
	{
		return id;
	}

}
