package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.game.src.framework.KeyInput;
import com.game.src.framework.MouseInput;
import com.game.src.framework.ObjectId;
import com.game.src.graphics.Textures;
import com.game.src.objects.Block;
import com.game.src.objects.Flag;
import com.game.src.objects.Handler;
import com.game.src.objects.Player;

public class Game extends Canvas
{

    private static final long serialVersionUID = 1L;

    protected boolean running = false;
    private Handler handler;
    private Camera camera;
    private static Textures tex;

    private BufferedImage level = null, clouds = null;
    private BufferedImageLoader loader;

    public static int ANCHO = 800;

    public static int ALTO = 600;

    public static int LEVEL = 0;

    public static void main(String[] args)
    {
        new Window(800, 600, "Plataforma", new Game());
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
        tex = new Textures();

        loader = new BufferedImageLoader();

        level = loader.loadImage("/level.png");

        clouds = loader.loadImage("/clouds.png");

        handler = new Handler(this, level);

        camera = new Camera(0, 0);

        LoadImageLevel(level);

        addMouseListener(new MouseInput(handler, camera));
        addKeyListener(new KeyInput(handler, camera));
        requestFocus();

    }

    public void tick()
    {
        handler.tick();

        for (int i = 0; i < handler.getObjects().size(); i++)
        {
            if (handler.getObjects().get(i).getId().equals(ObjectId.Player))
            {
                camera.tick(handler.getObjects().get(i));
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

        g.translate(camera.getX(), camera.getY());

        g.setColor(new Color(25, 190, 225));
        g.fillRect(- 1000, -1000, ANCHO * 10, ALTO * 10);

        g.translate((int) camera.getX(), (int) camera.getY());

        for (int i = 0; i < clouds.getWidth() * 7; i += clouds.getWidth() + 100)
        {
            g.drawImage(clouds, i, LEVEL * 900 + 20, this);
        }

        handler.render(g);

        g.translate((int) -camera.getX(), (int) -camera.getY());

        g.dispose();
        bs.show();

    }

    public void LoadImageLevel(BufferedImage image)
    {
        for (int j = LEVEL == 0 ? LEVEL * 30 : LEVEL * 30 - 20; j < LEVEL * 30 + 30; j++)
        {
            for (int i = 0; i < 100; i++)
            {
                int pixel = image.getRGB(i, j);

                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255 && green == 255 && blue == 255)
                {
                    handler.addObject(new Block(i * 32, j * 32, 0, ObjectId.Block));
                } else if (red == 128 && green == 128 && blue == 128)
                {
                    handler.addObject(new Block(i * 32, j * 32, 1, ObjectId.Block));
                } else if (red == 0 && green == 0 && blue == 255)
                {
                    handler.addObject(new Player(i * 32, j * 32, ObjectId.Player, handler, camera));
                } else if (red == 255 && green == 255 && blue == 0)
                {
                    handler.addObject(new Flag(i * 32, j * 32, ObjectId.Flag));
                }
            }
        }
    }

    public static Textures getTexture()
    {
        return tex;
    }

}
