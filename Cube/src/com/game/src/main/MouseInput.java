package com.game.src.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.game.src.menus.MainMenu;
import com.game.src.menus.Shop;

public class MouseInput extends MouseAdapter 
{
	private MainMenu menu;
	private Shop shop;
	
	public MouseInput(MainMenu menu, Shop shop) 
	{
		this.menu = menu;
		this.shop = shop;
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		menu.mousePressed(e);
		shop.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e){}

}
