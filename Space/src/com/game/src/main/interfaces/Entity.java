package com.game.src.main.interfaces;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Entity
{

    public void tick();

    public void render(Graphics g);

    public double getX();

    public double getY();

    public Rectangle getBounds(int ancho, int altura);

}
