package com.game.src.main;

/**
 *
 * @author HikingCarrot
 */
public class Ticker implements Runnable
{

    private Game game;
    private Thread thread;

    public Ticker(Game game)
    {
        this.game = game;

        thread = new Thread(this);

        game.start();
        thread.start();
    }

    @Override
    public void run()
    {
        game.init();

        long lastTime = System.nanoTime();
        final double amountOfThicks = 60.0;
        double ns = 1000000000 / amountOfThicks;
        double delta = 0;

        int update = 0;
        int frames = 0;
        Long timer = System.currentTimeMillis();

        while (true)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1)
            {
                game.tick();
                update++;
                delta--;
            }

            game.render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000)
            {
                //System.out.println("TICKS: " + update + " FPS: " + frames);
                timer += 1000;
                update = 0;
                frames = 0;
            }

        }

    }

}
