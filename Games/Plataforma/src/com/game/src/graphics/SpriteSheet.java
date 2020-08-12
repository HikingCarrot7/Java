package com.game.src.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet
{

    private final BufferedImage image;

    public SpriteSheet(BufferedImage image)
    {
        this.image = image;
    }

    public BufferedImage grabImage(int fila, int columna, int w, int h)
    {
        return image.getSubimage((columna * w) - w, (fila * h) - h, w, h);
    }

}
