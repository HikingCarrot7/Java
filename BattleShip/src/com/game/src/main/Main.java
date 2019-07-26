package com.game.src.main;

import com.game.src.UI.Menu;
import com.game.src.UI.PlacingShips;
import com.game.src.input.MouseInput;
import com.game.src.net.Cliente;
import com.game.src.input.MouseMotionInput;
import com.game.src.map.RandomLayout;
import com.game.src.net.Server;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private Server server;
    private PlacingShips placingShips;
    private RandomLayout randomLayout;
    private MouseInput mouseInput;
    private ExecutorService thread;

    public static void main(String[] args)
    {
        window = new Window(ANCHO, ALTO, "BATTLESHIP", new Main());
    }

    public void init()
    {
        createBufferStrategy(3);

        thread = Executors.newCachedThreadPool();
        randomLayout = new RandomLayout();
        placingShips = new PlacingShips(randomLayout);
        menu = new Menu(placingShips, randomLayout, this);
        mouseInput = new MouseInput(menu);

        addMouseListener(mouseInput);
        addMouseMotionListener(new MouseMotionInput(menu));

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

    public void crearClienteYServidor()
    {
        try
        {
            thread.execute(this);
            
            cliente = new Cliente(InetAddress.getLocalHost().getHostAddress());
            
            System.out.println("Cliente");

            mouseInput.setCliente(cliente);
            menu.setCliente(cliente);

        } catch (UnknownHostException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void crearCliente(String host)
    {
        cliente = new Cliente(host);

        mouseInput.setCliente(cliente);
        menu.setCliente(cliente);

    }

    @Override
    public void run()
    {
        server = new Server();
        server.execute();
    }

}
