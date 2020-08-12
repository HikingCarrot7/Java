package com.game.src.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.game.src.framework.GameObject;
import com.game.src.framework.ObjectId;

public class Flag extends GameObject
{

    public Flag(float x, float y, ObjectId id)
    {
        super(x, y, id);
    }

    @Override
    public void tick(ArrayList<GameObject> object)
    {

    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Color.yellow);
        g.fillOval((int) x, (int) y, 32, 32);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}
