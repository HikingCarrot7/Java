package com.game.src.UI;

import com.game.src.net.Cliente;
import com.game.src.graphics.Drawable;
import com.game.src.input.InputListener;
import com.game.src.main.Main;
import com.game.src.map.Cuadricula;
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
    private final Cliente cliente;
    private final RandomLayout randomLayout;
    private Cuadricula cuadricula;

    public Menu(PlacingShips placingShips, Cliente cliente, RandomLayout randomLayout)
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
        this.cliente = cliente;

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
        g.drawString("¿Cómo deseas generar tu tablero?", Main.ANCHO / 2 - 250, Main.ALTO / 2 - 180);

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
            Main.GAMESTATE = Main.STATE.SelectingMode;

        } else if (r.intersects(connect) && Main.GAMESTATE.equals(Main.STATE.Menu))
        {
            Main.GAMESTATE = Main.STATE.ConnectingToServer;

        } else if (r.intersects(manual) && Main.GAMESTATE.equals(Main.STATE.SelectingMode))
        {
            Main.GAMESTATE = Main.STATE.ColocandoBarcos;

        } else if (r.intersects(random) && Main.GAMESTATE.equals(Main.STATE.SelectingMode))
        {
            cliente.setBarcos(randomLayout.generarTablero());
            
            Main.GAMESTATE = Main.STATE.Jugando;
            
        }

    }

}