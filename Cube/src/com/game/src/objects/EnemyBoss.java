package com.game.src.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.framework.GameObject;
import com.game.src.framework.ObjectId;
import com.game.src.graphics.Trail;
import com.game.src.main.Game;

public class EnemyBoss extends GameObject
{

    private Handler handler;

    private Random rand;

    private int timer = 65, timer2 = 45;

    public EnemyBoss(float x, float y, ObjectId id, Handler handler)
    {
        super(x, y, id);

        rand = new Random();

        velX = rand.nextInt(2) == 0 ? 2 : -2;
        velY = 2;

        this.handler = handler;

    }

    @Override
    public void tick()
    {
        y += velY;

        if (timer <= 0)
            velY = 0;
        else
            timer--;

        if (timer <= 0)
            timer2--;

        if (timer2 <= 0)
        {
            x += velX;

            if (velX > 0)
                velX += 0.01f;
            else
                velX -= 0.01f;

            velX = Game.clamp(velX, 10, -10);

            if (rand.nextInt(Game.difficulty ? 5 : 10) == 0)
                handler.addObject(new EnemyBossBullet(x + 48, y + 48, ObjectId.BoundEnemy, handler));

            if (x < 0 || x > Game.ANCHO - 96)
                velX *= -1;
        }

        //if(y < 0 || y > Game.ALTO - 96) velY *= -1;
        handler.addObject(new Trail(x, y, ObjectId.Trail, handler, Color.green, 64, 64, 0.05f));

    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Color.green);
        g.fillRect((int) x, (int) y, 96, 96);

    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) x, (int) y, 96, 96);
    }

}
