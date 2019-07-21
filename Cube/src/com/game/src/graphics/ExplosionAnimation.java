package com.game.src.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class ExplosionAnimation
{

    private ArrayList<Particles> particulas;

    public ExplosionAnimation(float x, float y)
    {
        particulas = new ArrayList<>();

        for (int i = 0; i < 30; i++)
        {
            particulas.add(new Particles(x, y));
        }
    }

    public void tick()
    {
        for (Particles P : particulas)
        {
            P.tick();
        }
    }

    public void render(Graphics2D g)
    {
        for (Particles P : particulas)
        {
            P.render(g);
        }
    }

    private class Particles
    {

        private Random rand;

        private float x, y, velX, velY;

        public Particles(float x, float y)
        {
            rand = new Random();

            this.x = x;
            this.y = y;

            velX = rand.nextInt(10) - 5;
            velY = rand.nextInt(10) - 5;

            if (velX == 0)
            {
                velX = 5;
            }
            if (velY == 0)
            {
                velY = 3;
            }

        }

        public void tick()
        {
            x += velX;
            y += velY;
        }

        public void render(Graphics2D g)
        {
            g.setColor(Color.green);
            g.fillRect((int) x, (int) y, 5, 5);
        }

    }

}
