package com.game.src.input;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

import com.game.src.main.Game;

public final class ReadMap
{

    private final BufferedImage mapa;
    private Formatter salida;
    private Scanner in;
    private boolean mapLoaded = false;

    public ReadMap(Game game)
    {
        mapa = game.loadImage("/Map.png");

        checkFile();

        levelLoader();

    }

    public void levelLoader()
    {
        if (!mapLoaded)
        {
            generateControlText();

            for (int y = 0; y < mapa.getHeight(); y++)
                for (int x = 0; x < mapa.getWidth(); x++)
                {
                    int pixel = mapa.getRGB(x, y);

                    int red = (pixel >> 16) & 0xff;
                    int green = (pixel >> 8) & 0xff;
                    int blue = (pixel) & 0xff;

                    if (red == 255 && green == 255 && blue == 0)
                        salida.format("%s%n", "3," + x + "," + y);

                }

            salida.close();

        }

    }

    public void checkFile()
    {
        try
        {
            in = new Scanner(new File("Map.txt"));

            mapLoaded = in.hasNext();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    public void generateControlText()
    {
        try
        {
            salida = new Formatter(new FileWriter("Map.txt"));

            salida.format("%s%n%s%n%s%n", "//Fill title for whole map", "Fill:0", "//TileID-X-Y");

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
