package com.game.src.objects;

import com.game.src.framework.GameObject;
import com.game.src.framework.ObjectId;
import com.game.src.main.Game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject
{

    private Handler handler;
    private Random rand;

    public EnemyBossBullet(float x, float y, ObjectId id, Handler handler)
    {
        super(x, y, id);

        rand = new Random();

        velX = rand.nextInt(10) - 5;
        velY = Game.difficulty ? 8 : 5;

        this.handler = handler;

    }

    @Override
    public void tick()
    {
        x += velX;

        y += velY;

        if (y > Game.ALTO - 25)
            handler.removeObject(this);

        //if (x < 0 || x > Game.ANCHO - 32) velX *= -1;
        //handler.addTrail(new Trail(x, y, ObjectId.Trail, handler, Color.red, 16, 16, 0.009f));
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
