package com.game.src.UI;

import com.game.src.graphics.Drawable;
import com.game.src.input.InputListener;
import com.game.src.main.Main;
import com.game.src.map.Cuadricula;
import com.game.src.map.RandomLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

/**
 *
 * @author HikingCarrot7
 */
public class PlacingShips implements Drawable, InputListener
{

    private final Rectangle[] barcosHorizontales, barcosVerticales;
    private final Cuadricula cuadricula;
    private final int LADOCUADRO = 24;
    private final int ANCHOTABLERO = 20, ALTOTABLERO = 10;
    private final RandomLayout randomLayout;
    private int timer = 10, COORDENADAX, COORDENADAY;
    private boolean orientacionBarcoActual = false;
    private Rectangle barcoSeleccionado;

    public PlacingShips(RandomLayout randomLayout)
    {
        barcosHorizontales = new Rectangle[5];
        barcosVerticales = new Rectangle[3];
        cuadricula = new Cuadricula(25, 105);

        this.randomLayout = randomLayout;
    }

    @Override
    public void tick()
    {
        if (timer > 0)
        {
            timer--;
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        if (timer <= 0)
        {

            colocandoBarcos(g);
            barcoSeleccionado(g);
            barcoSeleccionadoMouse(g);
            confirmarBarcoMientrasArrastre();
        }
    }

    public void colocandoBarcos(Graphics2D g)
    {
        cuadricula.render(g);

        if (barcosHorizontales[0] == null)
        {
            colocarBarcosSeleccion(g, barcosHorizontales, true, 580, 70, LADOCUADRO);
            colocarBarcosSeleccion(g, barcosVerticales, false, 580, 260, LADOCUADRO);

        } else
        {
            for (Rectangle B : barcosHorizontales)
            {
                g.draw(B);
            }

            for (Rectangle B : barcosVerticales)
            {
                g.draw(B);
            }
        }
    }

    //False para una orientacion vertical, true para una horizontal
    //Coloca los barcos para su seleccion en las parte de la derecha
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

    //Pinta de color azul el barco seleccionado
    public void barcoSeleccionado(Graphics2D g)
    {
        if (barcoSeleccionado != null)
        {
            g.setColor(Color.blue);
            g.drawRect(barcoSeleccionado.x, barcoSeleccionado.y, barcoSeleccionado.width, barcoSeleccionado.height);

        }
    }

    //Pinta el un rectangulo que sigue al mouse mientras se coloca en el tablero
    public void barcoSeleccionadoMouse(Graphics2D g)
    {
        if (barcoSeleccionado != null)
        {
            g.drawRect(COORDENADAX, COORDENADAY, barcoSeleccionado.width, barcoSeleccionado.height);
        }

    }

    public void confirmarBarcoMientrasArrastre()
    {
        if (barcoSeleccionado != null)
        {
            checarTableroAlArrastrar(
                    (COORDENADAY - 105) / LADOCUADRO,
                    (COORDENADAX - 25) / LADOCUADRO,
                    (orientacionBarcoActual ? barcoSeleccionado.width : barcoSeleccionado.height) / LADOCUADRO,
                    orientacionBarcoActual);
        }
    }

    //Checamos el tablero al arrastrar (antes de hacer clic)
    public void checarTableroAlArrastrar(int fila, int columna, int length, boolean horizontal)
    {
        if (fila < ALTOTABLERO && fila >= 0 && columna < ANCHOTABLERO && columna >= 0)
        {

            if (validarUbicacionBarco(orientacionBarcoActual, fila, columna, cuadricula.obtenerTablero(), barcoSeleccionado))
            {
                cuadricula.iniciarTablero();

                pintarBarco(fila, columna, length, 1);

            } else
            {
                cuadricula.iniciarTablero();

                pintarBarco(fila, columna, length, 3);
            }

        } else
        {
            cuadricula.iniciarTablero();
        }
    }

    //Checamos el tablero al colocar el barco (cuando se hace click)
    public void checarTableroAlColocar(int fila, int columna)
    {
        if (fila < ALTOTABLERO && fila >= 0 && columna < ANCHOTABLERO && columna >= 0 && barcoSeleccionado != null)
        {

            if (validarUbicacionBarco(orientacionBarcoActual, fila, columna, cuadricula.obtenerTablero(), barcoSeleccionado))
            {
                int length = orientacionBarcoActual ? barcoSeleccionado.width / LADOCUADRO : barcoSeleccionado.height / LADOCUADRO;

                pintarBarco(fila, columna, length, 2);

                barcoSeleccionado = null;

            }

        }

    }

    //Pintamos el barco de acuerdo al color que le pasemos
    public void pintarBarco(int fila, int columna, int length, int color)
    {
        for (int i = fila, j = columna; (orientacionBarcoActual ? j : i) < (orientacionBarcoActual ? columna : fila) + length;)
        {
            cuadricula.modificarTablero(i, j, color);

            if (orientacionBarcoActual)
            {
                j++;

            } else
            {
                i++;
            }

        }
    }

    //true corresponde para horizontal, false para vertical
    public boolean validarUbicacionBarco(boolean orientacion, int fila, int columna, int[][] tablero, Rectangle barco)
    {
        int length = (orientacion ? barco.width : barco.height) / LADOCUADRO;
        int posicion = orientacion ? columna : fila;
        int limite = orientacion ? ANCHOTABLERO : ALTOTABLERO;
        int[][] tempTablero = new int[12][22];

        tempTablero = randomLayout.copiarTableros(tempTablero, tablero, 0, 1, 0);

        if (posicion + length > limite)
        {
            return false;
        }

        for (int i = fila + 1, j = columna + 1; (orientacion ? j : i) < (orientacion ? columna : fila) + length + 1;)
        {

            if (tempTablero[i][j] == 2 || tempTablero[i][j - 1] == 2 || tempTablero[i][j + 1] == 2 || tempTablero[i + 1][j] == 2 || tempTablero[i - 1][j] == 2)
            {
                return false;
            }

            if (orientacion)
            {
                j++;

            } else
            {
                i++;
            }
        }

        return true;
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

                    orientacionBarcoActual = true;

                    return;
                }
            }

            for (Rectangle barcoVertical : barcosVerticales)
            {
                if (r.intersects(barcoVertical))
                {
                    barcoSeleccionado = barcoVertical;

                    orientacionBarcoActual = false;

                    return;
                }
            }
        }

        checarTableroAlColocar((e.getY() - 105) / LADOCUADRO, (e.getX() - 25) / LADOCUADRO);
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        COORDENADAX = e.getX();
        COORDENADAY = e.getY();
    }

}
