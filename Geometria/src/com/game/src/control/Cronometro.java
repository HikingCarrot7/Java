package com.game.src.control;

/**
 *
 * @author HikingCarrot7
 */
public class Cronometro
{

    private long delta, time, lastTime;
    private boolean running;

    public Cronometro()
    {
        delta = 0;
        time = 0;
        running = false;

    }

    public void run(long time)
    {
        this.time = time;

        running = true;

    }

    public void tick()
    {
        if (running)
        {
            delta += System.currentTimeMillis() - lastTime;

            if (delta >= time)
            {
                running = false;

                delta = 0;
            }

            lastTime = System.currentTimeMillis();

        }
    }

    public boolean isRunning()
    {
        return running;
    }

}
