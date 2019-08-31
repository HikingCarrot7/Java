package com.game.src.graphics;

import com.game.src.main.Game;

public class Rectangle
{

    public int x, y, w, h;
    private int[] pixels;

    public Rectangle(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        pixels = new int[w * h];

        for (int i = 0; i < pixels.length; i++)
            pixels[i] = Game.alpha;
    }

    public Rectangle()
    {
        this(0, 0, 0, 0);
    }

    public boolean intersects(Rectangle otherRectangle)
    {
        if (x > otherRectangle.x + otherRectangle.w || otherRectangle.x > x + w)
            return false;

        if (y > otherRectangle.y + otherRectangle.h || otherRectangle.y > y + h)
            return false;

        return true;
    }

    public void generateGraphics(int color)
    {
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
                pixels[x + y * w] = color;
    }

    public void generateGraphics(int borderW, int color)
    {
        for (int y = 0; y < borderW; y++)
            for (int x = 0; x < w; x++)
                pixels[x + y * w] = color;

        for (int y = 0; y < h; y++)
            for (int x = 0; x < borderW; x++)
                pixels[x + y * w] = color;

        for (int y = 0; y < h; y++)
            for (int x = w - borderW; x < w; x++)
                pixels[x + y * w] = color;

        for (int y = h - borderW; y < h; y++)
            for (int x = 0; x < w; x++)
                pixels[x + y * w] = color;
    }

    public int[] getPixels()
    {
        if (pixels != null)
            return pixels;
        else
            System.out.println("No se han generado los pixeles");

        return null;
    }

}
