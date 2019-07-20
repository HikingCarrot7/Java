package com.game.src.objects;

import com.game.src.graphics.AnimatedSprite;
import com.game.src.graphics.Rectangle;
import com.game.src.graphics.RenderHandler;
import com.game.src.input.KeyInput;
import com.game.src.main.Game;

public class Player implements GameObject
{
	
	private Rectangle playerRectangle;
	private int speed = 5, direction = 0, newDirection = 0;
	
	private AnimatedSprite playerAnimation;
	
	public Player(AnimatedSprite playerAnimation) 
	{
		this.playerAnimation = playerAnimation;
		
		playerRectangle = new Rectangle(100, 100, 20, 26);
		playerRectangle.generateGraphics(3, 0xFF00FF21);
	}
	
	@Override
	public void tick(Game game)
	{
		KeyInput input = game.getKeyListener();
		
		boolean didMove = false;
		
		if(input.up()) 
		{
			newDirection = 2;
			didMove = true;
			playerRectangle.y -= speed;
		}
		
		if(input.down()) 
		{
			newDirection = 3;
			didMove = true;
			playerRectangle.y += speed;
		}
		
		if(input.right()) 
		{
			newDirection = 0;
			didMove = true;
			playerRectangle.x += speed;
		}
		
		if(input.left()) 
		{
			newDirection = 1;
			didMove = true;
			playerRectangle.x -= speed;
		}
		
		if(newDirection != direction) 
		{
			direction = newDirection;
			updateDireccion();
		}
		
		updateCamera(game.getRenderer().getCamera());
		
		if(didMove)
			playerAnimation.tick(game);
		
		else
			playerAnimation.reset();
			
	}
	
	private void updateDireccion() 
	{
		playerAnimation.setAnimationRange(direction * 8, direction * 8 + 7);
	}

	@Override
	public void render(RenderHandler renderer, int xZoom, int yZoom) 
	{
		//renderer.renderRectangle(playerRectangle, xZoom, yZoom);
		
		renderer.renderSprite(playerAnimation, playerRectangle.x, playerRectangle.y, xZoom, yZoom, false);
		
	}
	
	public void updateCamera(Rectangle camera) 
	{
		camera.x = playerRectangle.x - (camera.w/2) - 8;
		camera.y = playerRectangle.y - (camera.h/2) - 16;
		
//		if(camera.x <= -100)
//			camera.x = -100;
//
//		if(camera.y <= -100)
//			camera.y = -100;
		
	}

	@Override
	public boolean handleMouseClick(Rectangle mouseRectangle, Rectangle camera, int xZoom, int yZoom)
	{
		return false;
	}


}
