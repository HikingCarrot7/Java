package com.game.src.graphics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Map 
{
	private Tiles tileSet;
	private Scanner in;
	private int fillTileID = -1;
	private ArrayList<MappedTile> mappedTiles;
	private File mapFile;
	
	public Map(File mapFile, Tiles tileSet) 
	{
		this.tileSet = tileSet;
		this.mapFile = mapFile;
		
		mappedTiles = new ArrayList<>();

		readFile(mapFile);
	}

	public void readFile(File mapFile) 
	{
		try 
		{
			in = new Scanner(mapFile);
			
			while(in.hasNext()) 
			{
				String line = in.nextLine();

				if(!line.startsWith("//")) 
				{
					if(line.contains(":")) 
					{
						String[] splitString = line.split(":");

						if(splitString[0].equalsIgnoreCase("Fill")) 
						{
							fillTileID = Integer.parseInt(splitString[1]);

							continue;
						}

					}

					String[] splitString = line.split(",");
					
					if(splitString.length >= 3) 
						mappedTiles.add(new MappedTile(Integer.parseInt(splitString[0]), Integer.parseInt(splitString[1]), Integer.parseInt(splitString[2])));

				}

			}

		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();

		}finally 
		{
			in.close();
		}
		 
	}
	
	public void setTile(int tileX, int tileY, int tileID) 
	{
		boolean foundTile = false;
		
		for (int i = 0; i < mappedTiles.size(); i++) 
		{
			MappedTile mappedTile = mappedTiles.get(i);
			
			if(mappedTile.x == tileX && mappedTile.y == tileY) 
			{
				mappedTile.id = tileID;
				foundTile = true;
				break;
			}
			
		}
		
		if(!foundTile) 
			mappedTiles.add(new MappedTile(tileID, tileX, tileY));
		
	}
	
	public synchronized void removeTile(int tileX, int tileY) 
	{
		for (int i = 0; i < mappedTiles.size(); i++) 
		{
			MappedTile mappedTile = mappedTiles.get(i);
			
			if(mappedTile.x == tileX && mappedTile.y == tileY) 
				mappedTiles.remove(i);
			
		}
	}
	
	public synchronized void renderMap(RenderHandler renderer, int xZoom, int yZoom) 
	{
		int xIncrement = 16 * xZoom;
		int yIncrement = 16 * yZoom;
		
		if(fillTileID >= 0) 
			for (int y = renderer.getCamera().y - yIncrement - (renderer.getCamera().y % yIncrement); y < renderer.getCamera().y + renderer.getCamera().h; y += yIncrement) 
				for (int x = renderer.getCamera().x - xIncrement - (renderer.getCamera().x % xIncrement); x < renderer.getCamera().x + renderer.getCamera().w; x += xIncrement) 
					tileSet.renderTile(fillTileID, renderer, x, y, xZoom, yZoom);
		
		for (int i = 0; i < mappedTiles.size(); i++) 
		{
			MappedTile mappedTile = mappedTiles.get(i);
			
			tileSet.renderTile(mappedTile.id, renderer, mappedTile.x * xIncrement, mappedTile.y * yIncrement, xZoom, yZoom);
		}
		
	}
	
	public void saveMap() 
	{
		
		try
		{
			if(mapFile.exists())
				mapFile.delete();
			
			mapFile.createNewFile();
		
			PrintWriter printWriter = new PrintWriter(mapFile);
			
			if(fillTileID >= 0) 
				printWriter.printf("//Fill title for whole map%nFill:%d%n//TileID-X-Y%n", fillTileID);
	
			for (int i = 0; i < mappedTiles.size(); i++) 
			{	
				MappedTile mappedTile = mappedTiles.get(i);
				
				printWriter.println(mappedTile.id + "," + mappedTile.x + "," + mappedTile.y);
			}
			
			printWriter.close();
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private class MappedTile
	{
		public int id, x, y;

		public MappedTile(int id, int x, int y) 
		{
			this.id = id;
			this.x = x;
			this.y = y;
		}
		
	}

}
