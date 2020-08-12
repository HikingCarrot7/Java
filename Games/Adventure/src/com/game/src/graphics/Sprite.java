package com.game.src.graphics;

import java.awt.image.BufferedImage;

public class Sprite
{

    protected int w, h;
    protected int[] pixels;

    public Sprite(SpriteSheet sheet, int startX, int startY, int w, int h)
    {
        this.w = w;
        this.h = h;

        pixels = new int[w * h];

        sheet.getImage().getRGB(startX, startY, w, h, pixels, 0, w);

    }

    public Sprite(BufferedImage image)
    {
        w = image.getWidth();
        h = image.getHeight();

        pixels = new int[w * h];

        image.getRGB(0, 0, w, h, pixels, 0, w);

    }

    public Sprite()
    {
    }

    public int getW()
    {
        return w;
    }

    public int getH()
    {
        return h;
    }

    public int[] getPixels()
    {
        return pixels;
    }

}
