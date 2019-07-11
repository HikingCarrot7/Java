package com.game.src.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.game.src.main.Game;

public class RenderHandler 
{
	private BufferedImage view;
	private Rectangle camera;
	private int[] pixels;
	
	public RenderHandler(int w, int h) 
	{
		view = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		
		camera = new Rectangle(0, 0, w, h);
		
		pixels = ((DataBufferInt) view.getRaster().getDataBuffer()).getData();
		
	}
	
	public void render(Graphics2D g) 
	{
		g.drawImage(view, 0, 0, view.getWidth(), view.getHeight(), null);
	}
	
	public void renderImage(BufferedImage image, int xPos, int yPos, int xZoom, int yZoom, boolean fixed) 
	{
		int[] imagePixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		renderArray(imagePixels, image.getWidth(), image.getHeight(), xPos, yPos, xZoom, yZoom, fixed);	
	}
	
	public void renderRectangle(Rectangle rectangle, int xZoom, int yZoom, boolean fixed) 
	{
		int[] rectanglePixels = rectangle.getPixels();
		
		if(rectanglePixels != null)
			renderArray(rectanglePixels, rectangle.w, rectangle.h, rectangle.x, rectangle.y, xZoom, yZoom, fixed);
	}
	
	public void renderRectangle(Rectangle rectangle, Rectangle offSet, int xZoom, int yZoom, boolean fixed) 
	{
		int[] rectanglePixels = rectangle.getPixels();
		
		if(rectanglePixels != null)
			renderArray(rectanglePixels, rectangle.w, rectangle.h, rectangle.x + offSet.x, rectangle.y + offSet.y, xZoom, yZoom, fixed);
	}
	
	public void renderSprite(Sprite sprite, int xPos, int yPos, int xZoom, int yZoom, boolean fixed) 
	{
		renderArray(sprite.getPixels(), sprite.getW(), sprite.getH(), xPos, yPos, xZoom, yZoom, fixed);
	}
	
	public void renderArray(int[] renderPixels, int renderW, int renderH, int xPos, int yPos, int xZoom, int yZoom, boolean fixed) 
	{
		for (int y = 0; y < renderH; y++)
			for (int x = 0; x < renderW; x++) 
				for (int yZoomPosition = 0; yZoomPosition < yZoom; yZoomPosition++) 
					for (int xZoomPosition = 0; xZoomPosition < xZoom; xZoomPosition++) 
						setPixel(renderPixels[y * renderW + x], (x * xZoom + xPos + xZoomPosition), (y * yZoom + yPos + yZoomPosition), fixed);
	}
	
	private void setPixel(int pixel, int x, int y, boolean fixed) 
	{
		int pixelIndex = 0;
		
		if(!fixed) 
		{
			if(x >= camera.x && y >= camera.y && x <= camera.x + camera.w && y <= camera.y + camera.h) 
				pixelIndex = (y - camera.y) * view.getWidth() + (x - camera.x);
			
		}else 
			if(x >= 0 && y >= 0 && x <= camera.w && y <= camera.h)
				pixelIndex = x + y * view.getWidth();
		
		if(pixels.length > pixelIndex && pixel != Game.alpha) 
			pixels[pixelIndex] = pixel;
		
	}
	
	public void clear() 
	{
		for (int i = 0; i < pixels.length; i++) 
			pixels[i] = 0;
	}
	
	public Rectangle getCamera() 
	{
		return camera;
	}

}
