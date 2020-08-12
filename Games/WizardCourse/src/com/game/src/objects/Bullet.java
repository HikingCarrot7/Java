package com.game.src.objects;

import com.game.src.framework.GameObject;
import com.game.src.framework.ObjectId;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public final class Bullet extends GameObject
{

    private final Handler handler;

    public Bullet(float x, float y, ObjectId id, float mx, float my, Handler handler)
    {
        super(x, y, id);

        this.handler = handler;

        calculateVelocity(x, y, mx, my);

    }

    @Override
    public void tick()
    {
        x += velX;
        y += velY;

        if (x >= 32 * 64 || x <= 0)
            handler.removeObject(this);

        if (y >= 32 * 64 || y <= 0)
            handler.removeObject(this);

        for (int i = 0; i < handler.getObjects().size(); i++)
        {
            GameObject tempObject = handler.getObjects().get(i);

            if (tempObject.getId().equals(ObjectId.Block) && getBounds().intersects(tempObject.getBounds()))
                handler.removeObject(this);

        }

    }

    public void calculateVelocity(float fromX, float fromY, float toX, float toY)
    {
        double distance = Math.sqrt(Math.pow((toX - fromX), 2) + Math.pow((toY - fromY), 2));
        double speed = 10; //set the speed in [2,n)  n should be < 20 for normal speed
        //find Y
        velY = (float) ((toY - fromY) * speed / distance);
        //find X
        velX = (float) ((toX - fromX) * speed / distance);

    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Color.green);
        g.fillOval((int) x, (int) y, 8, 8);

    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) x, (int) y, 8, 8);
    }

}
