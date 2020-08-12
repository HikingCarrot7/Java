package com.game.src.graphics;

import com.game.src.framework.GameObject;
import com.game.src.main.Game;

public class Camera
{

    private float x, y;

    public Camera(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject player)
    {
        //x = (- player.getX() + Game.ANCHO/2)/2;
        //y = ((- player.getY() + Game.ALTO/2)/2);

        float xTarg = (-player.getX() + Game.ANCHO / 2);
        float yTarg = (-player.getY() + Game.ALTO / 2);

        x += (xTarg - x) * (0.05);
        y += (yTarg - y) * (0.05);

        if (x >= 0)
            x = 0;

        if (y >= 0)
            y = 0;

        if (x - Game.ANCHO <= -32 * 64)
            x = -32 * 64 + Game.ANCHO;

        if (y - Game.ALTO <= -32 * 64)
            y = -32 * 64 + Game.ALTO;

    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

}
