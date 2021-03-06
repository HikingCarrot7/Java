package com.game.src.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BufferedImageLoader
{

    public BufferedImage loadImage(String path)
    {
        try
        {
            return ImageIO.read(BufferedImageLoader.class.getClass().getResource(path));

        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return null;

    }

}
