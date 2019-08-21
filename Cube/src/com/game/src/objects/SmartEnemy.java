package com.game.src.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.src.framework.GameObject;
import com.game.src.framework.ObjectId;
import com.game.src.graphics.Textures;
import com.game.src.graphics.Trail;
import com.game.src.main.Game;

public class SmartEnemy extends GameObject
{

    private Handler handler;
    private Textures tex;
    private GameObject player;

    private float vel = Game.difficulty ? 5.0f : 1.0f;

    public SmartEnemy(float x, float y, ObjectId id, Handler handler, Textures tex)
    {
        super(x, y, id);

        velX = 5;
        velY = 5;

        for (int i = 0; i < handler.getObjects().size(); i++)
        {
            if (handler.getObjects().get(i).getId().equals(ObjectId.Player1))
            {
                player = handler.getObjects().get(i);

                break;
            }
        }

        this.handler = handler;
        this.tex = tex;

    }

    @Override
    public void tick()
    {
        x += velX;

        y += velY;

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;

        float distance = distanciaPuntos((int) x, (int) y, (int) player.getX(), (int) player.getY());

        velX = (float) ((-vel / distance) * diffX * vel);
        velY = (float) ((-vel / distance) * diffY * vel);

        if (y < 0 || y > Game.ALTO - 25)
        {
            velY *= -1;
        }

        if (x < 0 || x > Game.ANCHO - 32)
        {
            velX *= -1;
        }

        handler.addObject(new Trail(x, y, ObjectId.Trail, handler, Color.yellow, 16, 16, 0.05f));

    }

    public float distanciaPuntos(int x1, int y1, int x2, int y2)
    {
        float distancia = (float) (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));

        return distancia;
    }

    public void setRazon(float vel)
    {
        this.vel = vel;
    }

    @Override
    public void render(Graphics2D g)
    {
        /*g.setColor(Color.yellow);
         g.fillRect((int) x, (int) y, 16, 16);*/

        g.drawImage(tex.smartEnemy, (int) x, (int) y, null);

    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

}
