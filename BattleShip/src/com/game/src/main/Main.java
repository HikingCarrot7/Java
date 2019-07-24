package com.game.src.main;

import com.game.src.UI.Menu;
import com.game.src.UI.PlacingShips;
import com.game.src.input.MouseInput;
import com.game.src.cliente.Cliente;
import com.game.src.input.MouseMotionInput;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

/**
 *
 * @author HikingCarrot7
 */
public class Main extends Canvas
{

    public static int ANCHO = 800, ALTO = 700;
    private int CAMBIOX = 600, CAMBIOY = 500;

    public static enum STATE
    {

        Menu,
        SelectingMode,
        ConnectingToServer,
        ColocandoBarcos,
        Jugado,
    };

    public static STATE GAMESTATE = STATE.Menu;

    private static Window window;
    private Cliente cliente;
    private Menu menu;
    private PlacingShips placingShips;

    public static void main(String[] args)
    {
        window = new Window(ANCHO, ALTO, "BATTLESHIP", new Main());
    }

    public void init()
    {
        createBufferStrategy(3);

        placingShips = new PlacingShips();
        menu = new Menu(placingShips);
        cliente = new Cliente();

        addMouseListener(new MouseInput(cliente, menu));
        addMouseMotionListener(new MouseMotionInput(menu));

    }

    public void tick()
    {
        if (GAMESTATE.equals(STATE.ColocandoBarcos))
        {
            if (CAMBIOX <= 750)
            {
                window.setSize(CAMBIOX += 3, CAMBIOY -= 2);

            } else
            {
                menu.tick();

            }

        }
    }

    public void render()
    {
        BufferStrategy bs = getBufferStrategy();
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, ANCHO, ALTO);

        switch (GAMESTATE)
        {
            case Jugado:

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

        g.dispose();
        bs.show();
    }

}
