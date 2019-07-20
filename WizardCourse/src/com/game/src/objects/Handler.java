package com.game.src.objects;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.game.src.framework.GameObject;

public class Handler 
{
	
	private ArrayList<GameObject> objects;
	
	public Handler() 
	{
		objects = new ArrayList<>();
	}
	
	
	public void tick() 
	{
		for (int i = 0; i < objects.size(); i++) 
		{
			GameObject tempObject = objects.get(i);
			tempObject.tick();
		}
	}
	
	public void render(Graphics2D g) 
	{
		for (int i = 0; i < objects.size(); i++) 
		{
			GameObject tempObject = objects.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) 
	{
		objects.add(object);
	}
	
	public void removeObject(GameObject object) 
	{
		objects.remove(object);
	}
	
	public ArrayList<GameObject> getObjects()
	{
		return objects;
	}
	
}
