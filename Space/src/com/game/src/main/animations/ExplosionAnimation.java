package com.game.src.main.animations;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class ExplosionAnimation implements Runnable
{

    private Thread thread;
    private final Image ima;
    private int ancho = 128, altura = 128, incremento = 0, mx = 0, my = 0;
    private int x, y;
    private volatile boolean iniciar;

    public ExplosionAnimation(boolean iniciar)
    {
        this.iniciar = iniciar;

        ima = Toolkit.getDefaultToolkit().getImage("res/explosion/explosion.png");

        thread = new Thread(this);
        thread.start();

    }

    public void render(Graphics g)
    {
        if (iniciar)
        {
            mx = (incremento % 4) * ancho;
            my = (incremento / 4) * altura;

            g.drawImage(ima, x - 30, y - 40, x + ancho - 30, y + altura - 40, mx, my, mx + ancho, my + altura, null);
        }
    }

    @Override
    public void run()
    {
        while (iniciar)
        {

            try
            {
                Thread.sleep(20);

            } catch (InterruptedException e)
            {
            }

            if (incremento >= 16)
            {
                incremento = 0;

                iniciar = false;
            }

            incremento++;

        }

    }

    public void setIniciar(boolean iniciar)
    {
        this.iniciar = iniciar;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

}
