package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.src.main.interfaces.EntityA;

public class Bullet extends GameObject implements EntityA
{

    private BufferedImage bala;

    public Bullet(double x, double y)
    {
        super(x, y);

        bala = new BufferedImageLoader().loadImage("/res/lasers/laserBlue01.png");

    }

    @Override
    public void tick()
    {
        y -= 10;
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(bala, (int) x, (int) y, null);
    }

    @Override
    public double getY()
    {
        return y;
    }

    @Override
    public double getX()
    {
        return x;
    }

    @Override
    public Rectangle getBounds(int ancho, int altura)
    {
        return new Rectangle((int) x - ancho, (int) y, ancho + 10, altura);
    }

}
