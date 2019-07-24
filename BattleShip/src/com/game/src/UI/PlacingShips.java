package com.game.src.UI;

import com.game.src.graphics.Drawable;
import com.game.src.input.Listener;
import com.game.src.main.Main;
import com.game.src.map.Cuadricula;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

/**
 *
 * @author HikingCarrot7
 */
public class PlacingShips implements Drawable, Listener
{

    private Cuadricula cuadricula;
    private final Rectangle[] barcosHorizontales, barcosVerticales;
    private Rectangle barcoSeleccionado;
    private int timer = 10, COORDENADAX, COORDENADAY;

    public PlacingShips()
    {
        barcosHorizontales = new Rectangle[5];
        barcosVerticales = new Rectangle[3];
    }

    @Override
    public void render(Graphics2D g)
    {
        if (timer <= 0)
        {
            colocandoBarcos(g);
            barcoSeleccionado(g);
            dibujarBarcoSeleccionado(g);
        }
    }

    @Override
    public void tick()
    {
        if (timer > 0)
        {
            timer--;
        }
    }

    public void colocandoBarcos(Graphics2D g)
    {
        cuadricula = new Cuadricula(25, 105);
        cuadricula.render(g);

        colocarBarcosSeleccion(g, barcosHorizontales, true, 580, 70, 24);
        colocarBarcosSeleccion(g, barcosVerticales, false, 580, 260, 24);
    }

    //False para una orientacion vertical, true para una horizontal
    public void colocarBarcosSeleccion(Graphics2D g, Rectangle[] barcos, boolean orientacion, int INICIOX, int INICIOY, int LADOCUADRO)
    {

        if (orientacion)
        {
            for (int i = 0; i < barcos.length; i++)
            {
                barcos[i] = new Rectangle(INICIOX, INICIOY + i * (LADOCUADRO + 5), (i + 2) * LADOCUADRO, LADOCUADRO);

                g.draw(barcos[i]);
            }

        } else
        {
            for (int i = barcos.length - 1; i >= 0; i--)
            {
                barcos[i] = new Rectangle(INICIOX + (barcos.length - 1 - i) * (LADOCUADRO + 5), INICIOY + (barcos.length - 1 - i) * LADOCUADRO, LADOCUADRO, (i + barcos.length - 1) * LADOCUADRO);

                g.draw(barcos[i]);
            }
        }
    }

    public void barcoSeleccionado(Graphics2D g)
    {
        if (barcoSeleccionado != null)
        {
            g.setColor(Color.blue);
            g.drawRect(barcoSeleccionado.x, barcoSeleccionado.y, barcoSeleccionado.width, barcoSeleccionado.height);

        }
    }

    public void dibujarBarcoSeleccionado(Graphics2D g)
    {
        if (barcoSeleccionado != null)
        {
            g.drawRect(COORDENADAX, COORDENADAY, barcoSeleccionado.width, barcoSeleccionado.height);
        }

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (Main.GAMESTATE.equals(Main.STATE.ColocandoBarcos) && timer <= 0)
        {
            Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);

            for (Rectangle barcoHorizontal : barcosHorizontales)
            {
                if (r.intersects(barcoHorizontal))
                {
                    barcoSeleccionado = barcoHorizontal;

                    return;
                }
            }

            for (Rectangle barcoVertical : barcosVerticales)
            {
                if (r.intersects(barcoVertical))
                {
                    barcoSeleccionado = barcoVertical;

                    return;
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        COORDENADAX = e.getX();
        COORDENADAY = e.getY();
    }

}
