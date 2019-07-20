package com.game.src.graphics;

import java.awt.image.BufferedImage;

import com.game.src.main.Game;
import com.game.src.objects.GameObject;

public class AnimatedSprite extends Sprite implements GameObject
{
	private Sprite[] sprites;
	private int currentSprite, speed, counter, startSprite = 0, endSprite;
	
	private int xPos, yPos;
	
	public AnimatedSprite(SpriteSheet sheet, Rectangle[] positions, int speed) 
	{
		
		this.speed = speed;
		endSprite = 7;
		
		sprites = new Sprite[positions.length];
		
		for (int i = 0; i < positions.length; i++)
			sprites[i] = new Sprite(sheet, positions[i].x, positions[i].y, positions[i].w, positions[i].h);
		
	}
	
	public AnimatedSprite(SpriteSheet sheet, int speed) 
	{
		this.speed = speed;
		
		sprites = sheet.getLoadedSprites();
		endSprite = 7;
	}
	
	public AnimatedSprite(SpriteSheet sheet, int speed, int xPos, int yPos) 
	{
		this.speed = speed;
		this.xPos = xPos;
		this.yPos = yPos;
		
		
		sprites = sheet.getLoadedSprites();
		endSprite = 32;
	}
	
	public AnimatedSprite(BufferedImage[] images, int speed)
	{
		sprites = new Sprite[images.length];
		
		this.speed = speed;
		endSprite = images.length;
		
		for (int i = 0; i < images.length; i++)
			sprites[i] = new Sprite(images[i]);
	}

	@Override
	public void tick(Game game) 
	{
		if(counter++ >= speed) 
		{
			counter = 0;
			
			incrementSprite();
			
		}
		
	}
	
	public void reset() 
	{
		counter = 0;
		currentSprite = startSprite;
	}
	
	public void setAnimationRange(int startSprite, int endSprite) 
	{
		this.startSprite = startSprite;
		this.endSprite = endSprite;
		
		reset();
		
	}

	@Override
	public void render(RenderHandler renderer, int xZoom, int yZoom) 
	{
		renderer.renderSprite(this, xPos, yPos, 1, 1, false);
	}
	
	@Override
	public int getW()
	{
		return sprites[currentSprite].getW();
	}
	
	@Override
	public int getH() 
	{
		return sprites[currentSprite].getH();
	}
	
	@Override
	public int[] getPixels() 
	{
		return sprites[currentSprite].getPixels();
	}
	
	public void incrementSprite() 
	{
		if(currentSprite++ >= endSprite)
			currentSprite = startSprite;
	}

	@Override
	public boolean handleMouseClick(Rectangle mouseRectangle, Rectangle camera, int xZoom, int yZoom) 
	{
		return false;
	}

}
