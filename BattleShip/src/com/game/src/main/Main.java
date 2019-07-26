package com.game.src.main;

import com.game.src.UI.Menu;
import com.game.src.UI.PlacingShips;
import com.game.src.input.KeyInput;
import com.game.src.input.MouseInput;
import com.game.src.net.Cliente;
import com.game.src.input.MouseMotionInput;
import com.game.src.map.RandomLayout;
import com.game.src.net.Server;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

/**
 *
 * @author HikingCarrot7
 */
public class Main extends Canvas implements Runnable
{

    private static final long serialVersionUID = 1L;

    public static int ANCHO = 800, ALTO = 700;

    private int CAMBIOX = 600, CAMBIOY = 500;
    private boolean moving = false;

    public static enum STATE
    {

        Menu,
        SelectingMode,
        ConnectingToServer,
        ColocandoBarcos,
        Jugando,
    };

    public static STATE GAMESTATE = STATE.Menu;

    private static Window window;
    private Cliente cliente;
    private Menu menu;
    private PlacingShips placingShips;
    private RandomLayout randomLayout;
    private MouseInput mouseInput;
    private Thread thread;

    public static void main(String[] args)
    {
        window = new Window(ANCHO, ALTO, "BATTLESHIP", new Main());
    }

    public void init()
    {
        createBufferStrategy(3);

        thread = new Thread(this);
        randomLayout = new RandomLayout();
        placingShips = new PlacingShips(randomLayout);
        menu = new Menu(placingShips, randomLayout, this);
        mouseInput = new MouseInput(menu);

        addMouseListener(mouseInput);
        addMouseMotionListener(new MouseMotionInput(menu));
        addKeyListener(new KeyInput(menu));

    }

    public void tick()
    {

        switch (GAMESTATE)
        {
            case ColocandoBarcos:

                if (CAMBIOX <= 750)
                {
                    window.setSize(CAMBIOX += 3, CAMBIOY -= 2);

                    moving = true;

                } else
                {
                    menu.tick();

                    moving = false;
                }

                break;

            case Jugando:

                if (CAMBIOX <= 750)
                {
                    window.setSize(CAMBIOX += 3, CAMBIOY);

                    moving = true;

                } else
                {
                    moving = false;
                }

                if (CAMBIOY <= 700)
                {
                    window.setSize(CAMBIOX, CAMBIOY += 3);

                    moving = true;

                } else
                {
                    moving = false;
                }

                break;

            default:

                break;

        }
    }

    public void render()
    {
        BufferStrategy bs = getBufferStrategy();
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        if (!moving)
        {
            g.setColor(Color.black);
            g.fillRect(0, 0, ANCHO, ALTO);

            switch (GAMESTATE)
            {
                case Jugando:

                    cliente.render(g);

                    break;

                case Menu:
                case SelectingMode:
                case ConnectingToServer:

                    window.setSize(600, 500);

                    menu.render(g);

                    break;

                case ColocandoBarcos:

                    menu.render(g);

                    break;

                default:

                    break;

            }
        }

        g.dispose();
        bs.show();
    }

    public void crearClienteYServer(String host)
    {
        thread.start();
        
        cliente = new Cliente(host, menu);

        mouseInput.setCliente(cliente);
        menu.setCliente(cliente);

    }

    public void crearCliente(String host)
    {
        cliente = new Cliente(host, menu);

        mouseInput.setCliente(cliente);
        menu.setCliente(cliente);

    }

    @Override
    public void run()
    {
        new Server();
    }

}
