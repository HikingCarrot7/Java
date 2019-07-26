package com.game.src.UI;

import com.game.src.net.Cliente;
import com.game.src.graphics.Drawable;
import com.game.src.input.InputListener;
import com.game.src.main.Main;
import com.game.src.map.RandomLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

/**
 *
 * @author HikingCarrot7
 */
public class Menu implements Drawable, InputListener
{

    private final Rectangle play, connect, random, manual;
    private final Font title, buttonText, text;
    private final PlacingShips placingShips;
    private final RandomLayout randomLayout;
    private final Main main;
    private Cliente cliente;


    public Menu(PlacingShips placingShips, RandomLayout randomLayout, Main main)
    {
        play = new Rectangle(Main.ANCHO / 2 - 210, 200, 200, 60);
        connect = new Rectangle(Main.ANCHO / 2 - 210, 300, 200, 60);
        random = new Rectangle(80, 200, 200, 60);
        manual = new Rectangle(Main.ANCHO - 480, 200, 200, 60);

        title = new Font("serif", Font.BOLD, 50);
        text = new Font("serif", Font.BOLD, 20);
        buttonText = new Font("serif", Font.BOLD, 45);

        this.randomLayout = randomLayout;
        this.placingShips = placingShips;
        this.main = main;

    }

    @Override
    public void tick()
    {
        if (Main.GAMESTATE.equals(Main.STATE.ColocandoBarcos))
        {
            placingShips.tick();
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Color.white);
        renderMenu(g);
    }

    public void renderMenu(Graphics2D g)
    {
        switch (Main.GAMESTATE)
        {
            case Menu:

                dibujarMenuPrincipal(g);

                break;

            case SelectingMode:

                dibujarSelectingMode(g);

                break;

            case ColocandoBarcos:

                placingShips.render(g);

                break;
        }

    }

    public void dibujarMenuPrincipal(Graphics2D g)
    {
        g.setFont(title);
        g.drawString("BATTLESHIP!", Main.ANCHO / 2 - 270, 80);

        g.setFont(buttonText);
        g.draw(play);
        g.drawString("Play!", play.x + 55, play.y + 45);

        g.draw(connect);
        g.drawString("Connect...", connect.x + 5, connect.y + 45);

    }

    public void dibujarSelectingMode(Graphics2D g)
    {
        g.setFont(text);
        g.drawString("¿Como deseas generar tu tablero?", Main.ANCHO / 2 - 250, Main.ALTO / 2 - 180);

        g.setFont(buttonText);
        g.drawString("Random", random.x + 20, random.y + 45);
        g.draw(random);

        g.drawString("Manual", manual.x + 25, manual.y + 45);
        g.draw(manual);
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        placingShips.mouseMoved(e);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        detectarRectangle(e);

        placingShips.mousePressed(e);
    }

    public void detectarRectangle(MouseEvent e)
    {
        Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);

        if (r.intersects(play) && Main.GAMESTATE.equals(Main.STATE.Menu))
        {
            main.crearClienteYServidor();
            
            Main.GAMESTATE = Main.STATE.SelectingMode;

        } else if (r.intersects(connect) && Main.GAMESTATE.equals(Main.STATE.Menu))
        {
            main.crearCliente("192.168.0.2");
            
            Main.GAMESTATE = Main.STATE.Jugando;

        } else if (r.intersects(manual) && Main.GAMESTATE.equals(Main.STATE.SelectingMode))
        {
            Main.GAMESTATE = Main.STATE.ColocandoBarcos;

        } else if (r.intersects(random) && Main.GAMESTATE.equals(Main.STATE.SelectingMode))
        {
            cliente.setBarcos(randomLayout.generarTablero());
            
            Main.GAMESTATE = Main.STATE.Jugando;
            
        }

    }
    
    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

}
