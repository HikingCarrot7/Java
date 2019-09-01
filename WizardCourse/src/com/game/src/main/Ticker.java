package com.game.src.main;

public class Ticker implements Runnable
{

    private Game game;
    private Window window;

    public Ticker(Game game, Window window)
    {
        this.game = game;
        this.window = window;

        new Thread(this).start();
        
        game.start();
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

        while (game.running)
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
                window.setTitle(String.format("FPS: %d, TICKS: %d", frames, update));
                timer += 1000;
                update = 0;
                frames = 0;
            }
        }

        game.stop();
    }

}
