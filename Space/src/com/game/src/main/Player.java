package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.src.main.interfaces.EntityA;

public class Player extends GameObject implements EntityA
{

    private int velX = 0, velY = 0;

    private BufferedImage player;

    private Controller controller;

    private boolean muerte = false;

    private Game game;

    public Player(double x, double y, Controller controller, Game game)
    {
        super(x, y);

        this.game = game;

        this.controller = controller;

        player = new BufferedImageLoader().loadImage("/res/player/player.png");

    }

    public void tick()
    {
        x += velX;
        y += velY;

        if (x <= 0)
        {
            x = 0;
        }

        if (x >= 600)
        {
            x = 600;
        }

        if (y <= 0)
        {
            y = 0;
        }

        if (y >= 450)
        {
            y = 450;
        }

        for (int i = 0; i < controller.obtenerEntidadesB().size(); i++)
        {
            if (Physics.Collision(this, controller.obtenerEntidadesB().get(i)))
            {
                Game.VIDA -= 1;

                break;

            }
        }

        if (Game.VIDA <= 0 && !muerte)
        {
            game.setCoordenadasExp(true, (int) getX(), (int) getY());

            muerte = true;
        }

    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public void setVelX(int velX)
    {
        this.velX = velX;
    }

    public void setVelY(int velY)
    {
        this.velY = velY;
    }

    public int anchoPlayer()
    {
        return player.getWidth();
    }

    public int largoPlayer()
    {
        return player.getHeight();
    }

    public boolean getMuerte()
    {
        return muerte;
    }

    public void setMuerte(boolean muerte)
    {
        this.muerte = muerte;
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(player, (int) x, (int) y, null);
    }

    @Override
    public Rectangle getBounds(int ancho, int altura)
    {
        return new Rectangle((int) x, (int) y, ancho + 35, altura);
    }

}
