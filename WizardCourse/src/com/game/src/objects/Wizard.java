package com.game.src.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.game.src.framework.GameObject;
import com.game.src.framework.ObjectId;

public class Wizard extends GameObject
{

    private ArrayList<Integer> keys;
    private Handler handler;
    private boolean controlHorizontal = false;
    private int ancho = 32, alto = 64;
    private int ammo = 5;

    public Wizard(float x, float y, ObjectId id, Handler handler)
    {
        super(x, y, id);

        this.handler = handler;

        keys = new ArrayList<>();

    }

    @Override
    public void tick()
    {
        keyTracker();

        Collision(handler.getObjects());

    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Color.blue);
        g.drawRect((int) x, (int) y, 32, 64);

        g.setColor(Color.blue);
        g.drawRect((int) x, (int) y, ancho, alto);

        g.setColor(Color.green);
        g.draw(getBounds());

        g.setColor(Color.red);
        g.draw(getBoundsLeft());

        g.setColor(Color.white);
        g.draw(getBoundsRight());

        g.setColor(Color.gray);
        g.draw(getBoundsTop());

    }

    public void Collision(ArrayList<GameObject> objects)
    {
        for (int i = 0; i < objects.size(); i++)
            if (objects.get(i).getId().equals(ObjectId.Block))
            {
                //top
                if (getBoundsTop().intersects(objects.get(i).getBounds()))
                    y = objects.get(i).getY() + 32;

                //beneath
                if (getBoundsBeneath().intersects(objects.get(i).getBounds()))
                    y = objects.get(i).getY() - alto;

                //right
                if (getBoundsRight().intersects(objects.get(i).getBounds()))
                    x = objects.get(i).getX() - ancho;

                //left
                if (getBoundsLeft().intersects(objects.get(i).getBounds()))
                    x = objects.get(i).getX() + 32;

            } else if (objects.get(i).getId().equals(ObjectId.Crate) && getBounds().intersects(objects.get(i).getBounds()))
            {
                setAmmo(getAmmo() + 25);

                handler.removeObject(objects.get(i));
            }
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) x, (int) y, ancho, alto);
    }

    public Rectangle getBoundsLeft()
    {
        return new Rectangle((int) x, (int) y + 5, 1, alto - 10);
    }

    public Rectangle getBoundsRight()
    {
        return new Rectangle((int) x + ancho - 1, (int) y + 5, 1, alto - 10);
    }

    public Rectangle getBoundsTop()
    {
        return new Rectangle((int) x + 8, (int) y, ancho - 16, 1);
    }

    public Rectangle getBoundsBeneath()
    {
        return new Rectangle((int) x + 8, (int) y + alto - 1, ancho - 16, 1);
    }

    public void keyTracker()
    {
        x += getVelX();
        y += getVelY();

        if (keys.contains(38))
            setVelY(-5);

        if (keys.contains(40))
            setVelY(5);

        if (!controlHorizontal && keys.contains(39))
        {
            setVelX(5);

            if (keys.contains(37))
            {
                setVelX(-5);
                controlHorizontal = false;
            }

        } else if (keys.contains(37))
        {
            setVelX(-5);

            controlHorizontal = true;

            if (keys.contains(39))
                setVelX(5);
        }

        if (keys.isEmpty() || (keys.size() == 1 && keys.contains(39)))
            controlHorizontal = false;
    }

    public void addKey(Integer key)
    {
        keys.add(key);
    }

    public void removeKey(Integer key)
    {
        keys.remove(key);
    }

    public ArrayList<Integer> getKeys()
    {
        return keys;
    }

    public int getAmmo()
    {
        return ammo;
    }

    public void setAmmo(int ammo)
    {
        this.ammo = ammo;
    }

}
