package com.game.src.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import com.game.src.graphics.HUD;
import com.game.src.main.Game;
import com.game.src.objects.Handler;

public class Shop 
{
	private Handler handler;
	private Rectangle[] rectangles;
	private HUD hud;
	
	private int b1 = 1, b2 = 1, b3 = 1;
	
	public Shop(Handler handler, HUD hud) 
	{
		this.handler = handler;
		this.hud = hud;
		
		rectangles = new Rectangle[3];
		
		for (int i = 0; i < rectangles.length; i++) 
			rectangles[i] = new Rectangle(i * 250 + 50, 200, 200, 120);
		
	}
	
	
	public void render(Graphics2D g) 
	{
		g.setColor(Color.white);
		
		g.setFont(new Font("serif", Font.BOLD, 70));
		g.drawString("Shop", Game.ANCHO / 2 - 80, 90);
		
		g.setFont(new Font("serif", Font.BOLD, 20));
		
		g.drawString("Upgrade health", rectangles[0].x + 33, rectangles[0].y + 20);
		g.drawString("Upgrade speed", rectangles[1].x + 35, rectangles[1].y + 20);
		g.drawString("Refill health", rectangles[2].x + 45, rectangles[2].y + 20);
		
		g.setFont(new Font("serif", Font.BOLD, 15));
		g.drawString("Cost: " + b1, rectangles[0].x + 5, rectangles[0].y + 40);
		g.drawString("Cost: " + b2, rectangles[1].x + 5, rectangles[1].y + 40);
		g.drawString("Cost: " + b3, rectangles[2].x + 5, rectangles[2].y + 40);
		
		g.setFont(new Font("serif", Font.BOLD, 60));
		g.drawString("SCORE: " + hud.getScore(), Game.ANCHO/ 2 - 150, 450);
		
		g.setFont(new Font("serif", Font.BOLD, 50));
		g.drawString("Press Space to go back", Game.ANCHO/ 2 - 250 , 550);
		
		for (Rectangle R : rectangles) 
			g.draw(R);
	}
	
	
	public void mousePressed(MouseEvent e) 
	{
		Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
		
		if(r.intersects(rectangles[0]) && Game.gameState.equals(Game.STATE.Shop)) 
		{
			if(hud.getScore() >= b1) 
			{
				hud.setScore(hud.getScore() - b1);
				b1 += 1;
				hud.bounds += 70;
				HUD.HEALTH += 35;
			}
			
		}else if(r.intersects(rectangles[1]) && Game.gameState.equals(Game.STATE.Shop)) 
		{
			if(hud.getScore() >= b2) 
			{
				hud.setScore(hud.getScore() - b2);
				b2 += 1;
				
				handler.getObjects().get(0).setVelX(handler.getObjects().get(0).getVelX() + 3);
				handler.getObjects().get(0).setVelY(handler.getObjects().get(0).getVelY() + 3);
				
			}
			
		}else if(r.intersects(rectangles[2]) && Game.gameState.equals(Game.STATE.Shop)) 
		{
			if(hud.getScore() >= b3) 
			{
				hud.setScore(hud.getScore() - b3);
				b3 += 1;
				
				HUD.HEALTH += hud.bounds;
				
			}
		}
			
		
	}

	public void mouseReleased(MouseEvent e){}

}
