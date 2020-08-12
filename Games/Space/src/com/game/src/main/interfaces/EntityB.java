package com.game.src.main.interfaces;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityB extends Entity
{

    @Override
    public void tick();

    @Override
    public void render(Graphics g);

    @Override
    public double getX();

    @Override
    public double getY();

    @Override
    public Rectangle getBounds(int ancho, int altura);

}
