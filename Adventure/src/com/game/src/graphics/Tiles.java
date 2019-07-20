package com.game.src.graphics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tiles 
{
	private SpriteSheet spriteSheet;
	private Scanner in;
	private ArrayList<Tile> tiles;
	
	public Tiles(File tilesFile, SpriteSheet spriteSheet) 
	{
		this.spriteSheet = spriteSheet;
		
		tiles = new ArrayList<>();
		
		readFile(tilesFile);
		
	}
	
	public void readFile(File tilesFile) 
	{
		try 
		{
			in = new Scanner(tilesFile);
			
			while(in.hasNext()) 
			{
				String line = in.nextLine();
				
				if(!line.startsWith("//")) 
				{
					String[] splitString = line.split("-");
					
					String tileName = splitString[0];
					int spriteX = Integer.parseInt(splitString[1]);
					int spriteY = Integer.parseInt(splitString[2]);
					
					tiles.add(new Tile(tileName, spriteSheet.getSprite(spriteX, spriteY)));
					
				}
				
			}
			
		}catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			
		}finally 
		{
			in.close();
		}
	}
	
	public void renderTile(int tileID, RenderHandler renderer, int xPos, int yPos, int xZoom, int yZoom) 
	{
		if(tiles.size() > tileID && tileID >= 0)
			renderer.renderSprite(tiles.get(tileID).sprite, xPos, yPos, xZoom, yZoom, false);
	}
	
	public int tiles() 
	{
		return tiles.size();
	}
	
	public Sprite[] getSprites() 
	{
		Sprite[] sprites = new Sprite[tiles()];
		
		for (int i = 0; i < sprites.length; i++) 
			sprites[i] = tiles.get(i).sprite;
		
		return sprites;
	}
	
	private class Tile
	{
		private String tileName;
		public Sprite sprite;
		
		public Tile(String tileName, Sprite sprite) 
		{
			this.tileName = tileName;
			this.sprite = sprite;
		}
		
	}

}
