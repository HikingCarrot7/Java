package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import com.game.src.audio.AudioPlayer;
import com.game.src.framework.ObjectId;
import com.game.src.graphics.ExplosionAnimation;
import com.game.src.graphics.HUD;
import com.game.src.graphics.MenuBackground;
import com.game.src.graphics.Textures;
import com.game.src.menus.MainMenu;
import com.game.src.menus.Shop;
import com.game.src.objects.Handler;
import com.game.src.objects.Player;
import com.game.src.objects.Spawn;

public class Game extends Canvas
{

    private static final long serialVersionUID = 1L;

    public static int ANCHO = 800;
    public static int ALTO = 600;

    public ArrayList<ExplosionAnimation> explosiones;

    public boolean running = false;
    public static boolean paused = false;
    public static boolean difficulty = false;

    private Handler handler;
    private HUD hud;
    private Spawn spawn;
    private MainMenu menu;
    private Random rand;
    private Textures tex;
    private Shop shop;

    public static enum STATE
    {

        Menu,
        Select,
        Help,
        Game,
        Shop,
        End
    };

    public static STATE gameState = STATE.Menu;

    public static void main(String[] args)
    {
        new Window(ANCHO, ALTO, "Cube game", new Game());
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
        AudioPlayer.load();

        AudioPlayer.getMusic("paladins").loop();

        explosiones = new ArrayList<>();

        rand = new Random();
        tex = new Textures();
        handler = new Handler();
        hud = new HUD(this, handler);
        spawn = new Spawn(handler, hud, tex);
        shop = new Shop(handler, hud);
        menu = new MainMenu(handler, hud, spawn, this, tex);

        handler.addObject(new Player(ANCHO / 2 - 32, ALTO / 2 - 32, ObjectId.Player1, handler, tex));

        createBackground();

        requestFocus();
        addKeyListener(new KeyInput(handler, (Player) handler.getObjects().get(0)));
        addMouseListener(new MouseInput(menu, shop));
    }

    public void tick()
    {
        if (!(paused || gameState.equals(STATE.Shop)))
        {
            handler.tick();

            for (ExplosionAnimation A : explosiones)
            {
                A.tick();
            }

            if (gameState.equals(STATE.Game))
            {
                hud.tick();
                spawn.tick();

            } else if (gameState.equals(STATE.Menu) || gameState.equals(STATE.End))
            {
                menu.tick();
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

        handler.render(g);

        for (ExplosionAnimation A : explosiones)
        {
            A.render(g);
        }

        if (gameState.equals(STATE.Game))
        {
            hud.render(g);

        } else if (gameState.equals(STATE.Shop))
        {
            shop.render(g);

        } else
        {
            menu.render(g);
        }

        if (paused)
        {
            g.drawString("PAUSED", ANCHO / 2 - 100, ALTO / 2);
        }

        g.dispose();
        bs.show();

    }

    public void createBackground()
    {
        for (int i = 0; i < 20; i++)
        {
            if (i < 10)
            {
                handler.addObject(new MenuBackground(rand.nextInt(ANCHO / 2 - 100), rand.nextInt(Game.ALTO - 50), ObjectId.MenuBackgroudParticle, handler, menu));

            } else
            {
                handler.addObject(new MenuBackground(ANCHO / 2 + 100 + rand.nextInt(100), rand.nextInt(Game.ALTO - 50), ObjectId.MenuBackgroudParticle, handler, menu));

            }
        }
    }

    public static float clamp(float var, int max, int min)
    {
        if (var > max)
        {
            return max;
        } else if (var < min)
        {
            return min;
        } else
        {
            return var;
        }
    }

    public void addExplosion(ExplosionAnimation explosion)
    {
        explosiones.add(explosion);
    }

    public ArrayList<ExplosionAnimation> getExplosion()
    {
        return explosiones;
    }

}
