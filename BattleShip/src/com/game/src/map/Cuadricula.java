package com.game.src.map;

import com.game.src.graphics.Drawable;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author HikingCarrot7
 */
public final class Cuadricula implements Drawable
{

    private final int INICIOX, INICIOY, ANCHOTABLERO = 20, ALTOTABLERO = 10;
    private int[][] tablero;
    private final int LADOCUADRO = 24;

    public Cuadricula(int INICIOX, int INICIOY)
    {
        this.INICIOX = INICIOX;
        this.INICIOY = INICIOY;

        tablero = new int[ALTOTABLERO][ANCHOTABLERO];

        iniciarMapa();
    }

    @Override
    public void tick()
    {

    }

    @Override
    public void render(Graphics2D g)
    {
        dibujarMapa(g);
    }

    public void dibujarMapa(Graphics2D g)
    {
        for (int i = 0; i < tablero.length; i++)
        {
            for (int j = 0; j < tablero[i].length; j++)
            {
                g.setColor(tablero[i][j] == 1 ? Color.green : Color.white);
                g.fillRect(j * (LADOCUADRO) + INICIOX, i * (LADOCUADRO) + INICIOY, LADOCUADRO, LADOCUADRO);
                g.setColor(Color.black);
                g.setStroke(new BasicStroke(2));
                g.drawRect(j * (LADOCUADRO) + INICIOX, i * (LADOCUADRO) + INICIOY, LADOCUADRO, LADOCUADRO);
                g.setColor(Color.white);

            }
        }

        dibujarEtiquetas(g, INICIOX, INICIOY, 15);

    }

    public void dibujarEtiquetas(Graphics2D g, int INICIOX, int INICIOY, int OFFSET)
    {
        char letra = 'A';

        for (int i = 0; i < 10; i++)
        {
            g.drawString("" + letra++, INICIOX - OFFSET, INICIOY + i * LADOCUADRO + OFFSET);
        }

        for (int i = 0; i < 20; i++)
        {
            g.drawString(i < 9 ? "0" + (i + 1) : "" + (i + 1), INICIOX + 5 + i * LADOCUADRO, INICIOY - 10);
        }
    }

    public void modificarTablero(int i, int j, int cambio)
    {
        if (i < ALTOTABLERO && i >= 0 && j < ANCHOTABLERO && j >= 0)
        {
            tablero[i][j] = cambio;
        }
    }

    public void confirmarShip(int x, int y, int length, boolean horizontal)
    {
        int LIMITE = horizontal ? ANCHOTABLERO : ALTOTABLERO;

        if (x < ALTOTABLERO && x >= 0 && y < ANCHOTABLERO && y >= 0)
        {
            for (int i = 0; i < (horizontal ? ALTOTABLERO : ANCHOTABLERO); i++)
            {
                for (int j = 0; j < (horizontal ? ANCHOTABLERO : ALTOTABLERO); j++)
                {
                    if ((horizontal ? y : x) + length - 1 < LIMITE)
                    {
                        if (horizontal)
                        {
                            tablero[i][j] = j >= y && j < y + length && i == x ? 1 : 0;
                            
                        } else
                        {
                            tablero[j][i] = j >= x && j < x + length && i == y ? 1 : 0;
                        }

                    } else
                    {
                        iniciarMapa();
                    }
                }
            }

        } else
        {
            iniciarMapa();
        }
    }
    
    public void recibirTablero(int[][] tablero)
    {
        this.tablero = tablero;
    }

    public void iniciarMapa()
    {
        for (int[] I : tablero)
        {
            for (int i = 0; i < I.length; i++)
            {
                I[i] = 0;
            }
        }
    }
}
