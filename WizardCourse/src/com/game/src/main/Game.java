package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.game.src.framework.GameObject;
import com.game.src.framework.ObjectId;
import com.game.src.graphics.BufferedImageLoader;
import com.game.src.graphics.Camera;
import com.game.src.objects.Block;
import com.game.src.objects.Crate;
import com.game.src.objects.Enemy;
import com.game.src.objects.Handler;
import com.game.src.objects.Wizard;

public class Game extends Canvas
{

    private static final long serialVersionUID = 1L;

    public static int ANCHO = 1000, ALTO = 563;
    public boolean running = false;

    private Handler handler;
    private Camera camera;
    private BufferedImageLoader loader;

    public static void main(String[] args)
    {
        new Window(ANCHO, ALTO, "Wizard course", new Game());
    }

    public synchronized void start()
    {
        if (!running)
        {
            running = true;
        }
    }

    public synchronized void stop()
    {
        System.exit(1);
    }

    public void init()
    {
        loader = new BufferedImageLoader();
        handler = new Handler();
        camera = new Camera(0, -700);

        LoadImageLevel(loader.loadImage("/map.png"));

        addMouseListener(new MouseInput(handler, camera));
        addKeyListener(new KeyInput(handler));
        requestFocus();

    }

    public void tick()
    {

        handler.tick();
        for (GameObject O : handler.getObjects())
        {
            if (O.getId().equals(ObjectId.Player))
            {
                camera.tick((Wizard) O);

                break;
            }
        }

    }

    public void render()
    {
        BufferStrategy bs = getBufferStrategy();

        if (bs == null)
        {
            createBufferStrategy(3);

            return;
        }

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, ANCHO, ALTO);

        g.translate((int) camera.getX(), (int) camera.getY());

        handler.render(g);

        g.translate((int) -camera.getX(), (int) -camera.getY());

        g.dispose();
        bs.show();
    }

    public void LoadImageLevel(BufferedImage image)
    {
        for (int j = 0; j < image.getWidth(); j++)
        {
            for (int i = 0; i < image.getHeight(); i++)
            {
                int pixel = image.getRGB(i, j);

                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255 && green == 0 && blue == 0)
                {
                    handler.addObject(new Block(i * 32, j * 32, ObjectId.Block));
                } else if (red == 255 && green == 255)
                {
                    handler.addObject(new Enemy(i * 32, j * 32, ObjectId.Enemy, handler));
                } else if (blue == 255)
                {
                    handler.addObject(new Wizard(i * 32, j * 32, ObjectId.Player, handler));
                } else if (green == 255)
                {
                    handler.addObject(new Crate(i * 32, j * 32, ObjectId.Crate, handler));
                }

            }
        }
    }

}
