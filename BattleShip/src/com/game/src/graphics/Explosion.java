package com.game.src.graphics;

import com.game.src.main.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author HikingCarrot7
 */
public class Explosion implements Drawable
{

    private final Random rand;
    private final ArrayList<Particles> particulas;

    public Explosion(int x, int y, Color color)
    {
        rand = new Random();
        particulas = new ArrayList<>();

        for (int i = 0; i < 25; i++)
        {
            int velX = rand.nextInt(11) - 5;
            int velY = rand.nextInt(11) - 5;

            if (velX == 0)
            {
                velX = rand.nextInt(6) + 1;
            }

            if (velY == 0)
            {
                velY = rand.nextInt(6) + 1;
            }

            particulas.add(new Particles(x + 12, y + 12, velX, velY, color));
        }

    }

    @Override
    public void tick()
    {
        for (Iterator<Particles> particula = particulas.iterator(); particula.hasNext();)
        {
            Particles next = particula.next();

            if (next.x < 0 || next.x > Main.ANCHO || next.y > 0 || next.y < Main.ALTO)
            {
                particula.remove();
            }

        }
    }

    @Override
    public void render(Graphics2D g)
    {
        for (Particles particula : particulas)
        {
            particula.render(g);
        }
    }

    private class Particles implements Drawable
    {

        private final int velX, velY;
        private final Color color;
        private int x, y;

        public Particles(int x, int y, int velX, int velY, Color color)
        {
            this.x = x;
            this.y = y;
            this.velX = velX;
            this.velY = velY;
            this.color = color;
        }

        @Override
        public void tick()
        {
            x += velX;
            y += velY;
        }

        @Override
        public void render(Graphics2D g)
        {
            g.setColor(color);
            g.fillRect(x, y, 4, 4);
        }

    }

}
