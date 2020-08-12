package com.game.src.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.framework.GameObject;
import com.game.src.framework.ObjectId;

public class Enemy extends GameObject
{

    private Handler handler;
    private Random rand;
    private int chose;
    private int hp = 100;

    public Enemy(float x, float y, ObjectId id, Handler handler)
    {
        super(x, y, id);

        rand = new Random();

        this.handler = handler;

    }

    @Override
    public void tick()
    {
        x += velX;
        y += velY;

        chose = rand.nextInt(10);

        for (int i = 0; i < handler.getObjects().size(); i++)
        {
            GameObject tempObject = handler.getObjects().get(i);

            if (tempObject.getId().equals(ObjectId.Block))
                if (getBoundsBig().intersects(tempObject.getBounds()))
                {
                    x += (velX * 5) * -1;
                    y += (velY * 5) * -1;

                    velX *= -1;
                    velY *= -1;

                } else if (chose == 0)
                {
                    velX = rand.nextInt(8) - 4;
                    velY = rand.nextInt(8) - 4;
                }

            if (tempObject.getId().equals(ObjectId.Bullet))
                if (getBounds().intersects(tempObject.getBounds()))
                {
                    hp -= 50;

                    handler.removeObject(tempObject);

                    if (hp <= 0)
                        handler.removeObject(this);
                }

        }

    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Color.yellow);

        g.fillRect((int) x, (int) y, 32, 32);

    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) x - 16, (int) y - 16, 64, 64);
    }

    public Rectangle getBoundsBig()
    {
        return new Rectangle((int) x - 16, (int) y - 16, 64, 64);
    }

}
