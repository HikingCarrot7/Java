package com.game.src.interfaces;

import com.game.src.graphics.Rectangle;
import com.game.src.graphics.RenderHandler;
import com.game.src.graphics.Sprite;
import com.game.src.main.Game;
import com.game.src.objects.GameObject;

public class GUI implements GameObject 
{
	private Sprite backgroundSprite; 
	private GUIButton[] buttons;
	private Rectangle rect;
	private boolean fixed;
	
	public GUI(Sprite backgroundSprite, GUIButton[] buttons, int x, int y, boolean fixed) 
	{
		this.backgroundSprite = backgroundSprite;
		this.buttons = buttons;
		this.fixed = fixed;
		
		rect = new Rectangle();
		
		rect.x = x;
		rect.y = y;
		
		if(backgroundSprite != null) 
		{
			rect.w = backgroundSprite.getW();
			rect.h = backgroundSprite.getH();
		}
			
	}
	
	public GUI(GUIButton[] buttons, int x, int y, boolean fixed) 
	{	
		this(null, buttons, x, y, fixed);
	}

	@Override
	public void tick(Game game) 
	{
		if(buttons != null)
			for (GUIButton guiButton : buttons)
				guiButton.tick(game);
		
	}

	@Override
	public void render(RenderHandler renderer, int xZoom, int yZoom)
	{
		if(backgroundSprite != null)
			renderer.renderSprite(backgroundSprite, rect.x, rect.y, xZoom, yZoom, true);
		
		if(buttons != null)
			for (GUIButton guiButton : buttons)
				guiButton.render(renderer, xZoom, yZoom, rect);
		
	}

	@Override
	public boolean handleMouseClick(Rectangle mouseRectangle, Rectangle camera, int xZoom, int yZoom) 
	{
		if(!fixed) 
			mouseRectangle = new Rectangle(mouseRectangle.x + camera.x, mouseRectangle.y + camera.y, 1, 1);
		
		else
			mouseRectangle = new Rectangle(mouseRectangle.x, mouseRectangle.y, 1, 1);

		boolean stoppedChecking = false;
		
		if(rect.h == 0|| rect.w == 0 || mouseRectangle.intersects(rect)) 
		{
			mouseRectangle.x -= rect.x;
			mouseRectangle.y -= rect.y;
			
			for (GUIButton guiButton : buttons) 
			{
				boolean result = guiButton.handleMouseClick(mouseRectangle, camera, xZoom, yZoom);
				
				if(stoppedChecking == false)
					stoppedChecking = result;
				
			}
			
		}
		
		return stoppedChecking;

	}

}
