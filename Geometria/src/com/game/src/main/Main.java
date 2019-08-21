package com.game.src.main;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

/**
 *
 * @author HikingCarrot7
 */
public class Main extends Canvas
{

    public static int ANCHO = 600, ALTO = 500;

    public static void main(String[] args)
    {
        new Window("Geometría", ANCHO, ALTO, new Main());
    }

    public void init()
    {
        createBufferStrategy(3);
    }

    public void tick()
    {
    }

    public void render()
    {
        BufferStrategy bs = getBufferStrategy();

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        g.dispose();
        bs.show();
    }

}
