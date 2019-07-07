package com.game.src.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation 
{
	private int speed, frames, index = 0, count = 0;
	
	private BufferedImage[] images;
	
	private BufferedImage currentImg;
	
	public Animation(int speed, BufferedImage[] images) 
	{
		this.speed = speed;
		this.images = images;
		
		frames = images.length;
		
	}
	
	public void runAnimation() 
	{
		index++;
		
		if(index > speed) 
		{
			index = 0;
			nextFrame();
		}
	}
	
	public void nextFrame() 
	{
		currentImg = images[count % frames];
		count++;
	}
	
	public void drawAnimation(Graphics g, int x, int y) 
	{
		g.drawImage(currentImg, x, y, null);
	}
	
	public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY) 
	{
		g.drawImage(currentImg, x, y, scaleX, scaleY, null);
	}
	
}
