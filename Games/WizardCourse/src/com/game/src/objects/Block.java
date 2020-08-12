package com.game.src.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.src.framework.GameObject;
import com.game.src.framework.ObjectId;

public class Block extends GameObject
{

    public Block(float x, float y, ObjectId id)
    {
        super(x, y, id);

    }

    @Override
    public void tick()
    {

    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Color.white);
        g.drawRect((int) x, (int) y, 32, 32);

    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

}
