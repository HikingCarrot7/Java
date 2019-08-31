package com.game.src.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet
{

    private int[] pixels;
    private BufferedImage image;
    public final int SIZEX, SIZEY;
    public int spriteSizeX;
    private Sprite[] loadedSprites = null;

    public SpriteSheet(BufferedImage sheetImage)
    {
        image = sheetImage;
        SIZEX = sheetImage.getWidth();
        SIZEY = sheetImage.getHeight();

        pixels = new int[SIZEX * SIZEY];

        sheetImage.getRGB(0, 0, SIZEX, SIZEY, pixels, 0, SIZEX);

    }

    public void loadSprite(int spriteSizeX, int spriteSizeY)
    {
        this.spriteSizeX = spriteSizeX;

        loadedSprites = new Sprite[SIZEX / spriteSizeX * SIZEY / spriteSizeY];

        int SpriteID = 0;

        for (int y = 0; y < SIZEY; y += spriteSizeY)
            for (int x = 0; x < SIZEX; x += spriteSizeX)
                loadedSprites[SpriteID++] = new Sprite(this, x, y, spriteSizeX, spriteSizeY);
    }

    public Sprite getSprite(int x, int y)
    {
        int SpriteID = x + y * (SIZEX / spriteSizeX);

        if (SpriteID < loadedSprites.length)
            return loadedSprites[SpriteID];
        else
            return null;
    }

    public Sprite[] getLoadedSprites()
    {
        return loadedSprites;
    }

    public int[] getPixels()
    {
        return pixels;
    }

    public BufferedImage getImage()
    {
        return image;
    }

}
