package com.game.src.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author HikingCarrot
 */
public class Player extends GameObject
{

    private boolean moving = false;
    private int type = 0;

    public Player(float x, float y, GameId gameId, int type)
    {
        super(x, y, gameId);

        this.type = type;

    }

    @Override
    public void render(Graphics2D g)
    {
        if (type == 1)
        {
            g.setColor(Color.blue);
        } else
        {
            g.setColor(Color.red);
        }

        g.drawRect((int) x, (int) y, 32, 32);
    }

    @Override
    public void tick()
    {
        x += velX;
        y += velY;
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public boolean isMoving()
    {
        return moving;
    }

    public void setMoving(boolean moving)
    {
        this.moving = moving;
    }

}
