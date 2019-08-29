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
    private final ArrayList<Particle> particulas;

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

            particulas.add(new Particle(x + 12, y + 12, velX, velY, color));
        }

    }

    @Override
    public void tick()
    {
        for (Iterator<Particle> particula = particulas.iterator(); particula.hasNext();)
        {
            Particle currentParticle = particula.next();

            if (currentParticle.x < 0 || currentParticle.x > Main.ANCHO || currentParticle.y < 0 || currentParticle.y > Main.ALTO)
            {
                particula.remove();
            }

            currentParticle.tick();

        }

    }

    @Override
    public void render(Graphics2D g)
    {
        particulas.stream().forEach((p) ->
        {
            p.render(g);

        });

    }

    //Clase de las particulas 
    private class Particle implements Drawable
    {

        private final int velX, velY;
        private int x, y;
        private final Color color;

        public Particle(int x, int y, int velX, int velY, Color color)
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
