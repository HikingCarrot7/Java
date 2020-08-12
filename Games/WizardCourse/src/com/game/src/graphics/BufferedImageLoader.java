package com.game.src.graphics;

import java.awt.Canvas;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader extends Canvas
{

    private static final long serialVersionUID = 1L;

    public BufferedImage loadImage(String path)
    {
        try
        {
            BufferedImage loadedImage = ImageIO.read(getClass().getResource(path));

            BufferedImage formattedImage = new BufferedImage(loadedImage.getWidth(), loadedImage.getHeight(), BufferedImage.TYPE_INT_RGB);

            formattedImage.getGraphics().drawImage(loadedImage, 0, 0, null);

            return formattedImage;

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;

    }

}
