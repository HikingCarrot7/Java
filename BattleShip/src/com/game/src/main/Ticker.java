package com.game.src.main;

/**
 *Este es el corazón del juego, desde aquí se renderozan todas las imágenes y se actualizan las posiciones de todos los objectos.
 *
 *
 * @author HikingCarrot7
 * @since 1.0
 */
public final class Ticker implements Runnable
{

    private final Main main;
    private final Thread thread;

    public Ticker(Main main)
    {
        this.main = main;

        thread = new Thread(this);

        thread.start();
    }

    @Override
    public void run()
    {

        main.init();

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
                main.tick();
                update++;
                delta--;
            }

            main.render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000)
            {
                System.out.println("TICKS: " + update + " FPS: " + frames);
                timer += 1000;
                update = 0;
                frames = 0;
            }
        }

    }

}
