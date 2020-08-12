package com.game.src.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.game.src.framework.GameObject;
import com.game.src.framework.ObjectId;

public class Bullet extends GameObject
{

    public Bullet(float x, float y, ObjectId id, float velX, float velY)
    {
        super(x, y, id);

        this.velX = velX;
        this.velY = velY;
    }

    @Override
    public void tick(ArrayList<GameObject> object)
    {
        x += velX;
        y += velY;
    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 16, 16);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

}
