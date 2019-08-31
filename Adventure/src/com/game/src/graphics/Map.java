package com.game.src.graphics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public final class Map
{

    private Tiles tileSet;
    private Scanner in;
    private int fillTileID = -1;
    private ArrayList<MappedTile> mappedTiles;
    private File mapFile;

    private Chunk[][] chunks;
    private int chunkStartX = 0, chunkStartY = 0, chunkLength = 6, chunkPixelW = chunkLength * 16, chunkPixelH = chunkLength * 16;

    public Map(File mapFile, Tiles tileSet)
    {
        this.tileSet = tileSet;
        this.mapFile = mapFile;

        mappedTiles = new ArrayList<>();

        readFile(mapFile);
    }

    public void readFile(File mapFile)
    {

        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;

        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;

        try
        {
            in = new Scanner(mapFile);

            while (in.hasNext())
            {
                String line = in.nextLine();

                if (!line.startsWith("//"))
                {
                    if (line.contains(":"))
                    {
                        String[] splitString = line.split(":");

                        if (splitString[0].equalsIgnoreCase("Fill"))
                        {
                            fillTileID = Integer.parseInt(splitString[1]);

                            continue;
                        }

                    }

                    String[] splitString = line.split(",");

                    if (splitString.length >= 3)
                    {
                        MappedTile mappedTile = new MappedTile(Integer.parseInt(splitString[0]), Integer.parseInt(splitString[1]), Integer.parseInt(splitString[2]));

                        //se est� buscando el bloque que este m�s cerca y el que est� m�s alejado
                        if (mappedTile.x < minX)
                            minX = mappedTile.x;

                        if (mappedTile.y < minY)
                            minY = mappedTile.y;

                        if (mappedTile.x > maxX)
                            maxX = mappedTile.x;

                        if (mappedTile.y > maxY)
                            maxY = mappedTile.y;

                        mappedTiles.add(mappedTile);

                    }

                }

            }

            chunkStartX = minX;
            chunkStartY = minY;

            int chunkSizeX = maxX - minX + chunkLength;
            int chunkSizeY = maxY - minY + chunkLength;

            chunks = new Chunk[chunkSizeX][chunkSizeY];

            //Se a�aden todos los bloques a sus correspondientes chunks
            for (int i = 0; i < mappedTiles.size(); i++)
            {
                MappedTile mappedTile = mappedTiles.get(i);

                int chunkX = (mappedTile.x - minX) / chunkLength;
                int chunkY = (mappedTile.y - minY) / chunkLength;

                assert (chunkX >= 0 && chunkX < chunks.length && chunkY >= 0 && chunkY < chunks[0].length);

                if (chunks[chunkX][chunkY] == null)
                    chunks[chunkX][chunkY] = new Chunk();

                chunks[chunkX][chunkY].mappedTiles.add(mappedTile);

            }

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();

        } finally
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

            if (mappedTile.x == tileX && mappedTile.y == tileY)
            {
                mappedTile.id = tileID;
                foundTile = true;
                break;
            }

        }

        if (!foundTile)
            mappedTiles.add(new MappedTile(tileID, tileX, tileY));

    }

    public synchronized void removeTile(int tileX, int tileY)
    {
        for (int i = 0; i < mappedTiles.size(); i++)
        {
            MappedTile mappedTile = mappedTiles.get(i);

            if (mappedTile.x == tileX && mappedTile.y == tileY)
                mappedTiles.remove(i);

        }
    }

    public synchronized void renderMap(RenderHandler renderer, int xZoom, int yZoom)
    {
        int xIncrement = 16 * xZoom;
        int yIncrement = 16 * yZoom;

        //Dibuja el fondo
        if (fillTileID >= 0)
            for (int y = renderer.getCamera().y - yIncrement - (renderer.getCamera().y % yIncrement); y < renderer.getCamera().y + renderer.getCamera().h; y += yIncrement)
                for (int x = renderer.getCamera().x - xIncrement - (renderer.getCamera().x % xIncrement); x < renderer.getCamera().x + renderer.getCamera().w; x += xIncrement)
                    tileSet.renderTile(fillTileID, renderer, x, y, xZoom, yZoom);

        int topLeftX = renderer.getCamera().x;
//		int topLeftY = renderer.getCamera().y;

        int pixelX = renderer.getCamera().x;
        int pixelY = renderer.getCamera().y;

        int bottomRightX = renderer.getCamera().x + renderer.getCamera().w;
        int bottomRightY = renderer.getCamera().y + renderer.getCamera().h;

        int leftChunkX = (pixelX / xIncrement - chunkStartX - 16) / chunkLength;
        int chunkX = (pixelX - chunkStartX) / chunkLength;
        int chunkY = (pixelY / yIncrement - chunkStartY - 16) / chunkLength;

        while (pixelX < bottomRightX && pixelY < bottomRightY)
        {
            if (chunkX >= 0 && chunkY >= 0 && chunkX < chunks.length && chunkY < chunks[0].length)
                if (chunks[chunkX][chunkY] != null)
                    chunks[chunkX][chunkY].render(renderer, xZoom, yZoom, xIncrement, yIncrement);

            chunkX++;
            pixelX += chunkPixelW;

            if (pixelX > bottomRightX)
            {
                pixelX = topLeftX;
                chunkX = leftChunkX;
                chunkY++;
                pixelY += chunkPixelH;

                if (pixelY > bottomRightY)
                    break;
            }

        }

    }

//	public synchronized void renderMap(RenderHandler renderer, int xZoom, int yZoom) 
//	{
//		int xIncrement = 16 * xZoom;
//		int yIncrement = 16 * yZoom;
//		
//		if(fillTileID >= 0) 
//			for (int y = renderer.getCamera().y - yIncrement - (renderer.getCamera().y % yIncrement); y < renderer.getCamera().y + renderer.getCamera().h; y += yIncrement) 
//				for (int x = renderer.getCamera().x - xIncrement - (renderer.getCamera().x % xIncrement); x < renderer.getCamera().x + renderer.getCamera().w; x += xIncrement) 
//					tileSet.renderTile(fillTileID, renderer, x, y, xZoom, yZoom);
//		
//		for (int i = 0; i < mappedTiles.size(); i++) 
//		{
//			MappedTile mappedTile = mappedTiles.get(i);
//			
//			tileSet.renderTile(mappedTile.id, renderer, mappedTile.x * xIncrement, mappedTile.y * yIncrement, xZoom, yZoom);
//		}
//		
//	}
    public void saveMap()
    {

        try
        {
            if (mapFile.exists())
                mapFile.delete();

            mapFile.createNewFile();

            try (PrintWriter printWriter = new PrintWriter(mapFile))
            {
                if (fillTileID >= 0)
                    printWriter.printf("//Fill title for whole map%nFill:%d%n//TileID-X-Y%n", fillTileID);

                for (int i = 0; i < mappedTiles.size(); i++)
                {
                    MappedTile mappedTile = mappedTiles.get(i);

                    printWriter.println(mappedTile.id + "," + mappedTile.x + "," + mappedTile.y);
                }
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //un chunk representa un bloque de 6 x 6
    private class Chunk
    {

        public ArrayList<MappedTile> mappedTiles;

        public Chunk()
        {
            mappedTiles = new ArrayList<>();
        }

        public void render(RenderHandler renderer, int xZoom, int yZoom, int xIncrement, int yIncrement)
        {
            for (int i = 0; i < mappedTiles.size(); i++)
            {
                MappedTile mappedTile = mappedTiles.get(i);
                tileSet.renderTile(mappedTile.id, renderer, mappedTile.x * xIncrement, mappedTile.y * yIncrement, xZoom, yZoom);
            }
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
