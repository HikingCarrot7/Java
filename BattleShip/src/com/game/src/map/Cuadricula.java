package com.game.src.map;

import com.game.src.graphics.Drawable;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author HikingCarrot7
 */
public final class Cuadricula implements Drawable
{

    public static int ANCHOTABLERO = 20, ALTOTABLERO = 10;
    private final int INICIOX, INICIOY;
    private int[][] tablero;
    private final int LADOCUADRO = 24;

    public Cuadricula(int INICIOX, int INICIOY)
    {
        this.INICIOX = INICIOX;
        this.INICIOY = INICIOY;

        tablero = new int[ALTOTABLERO][ANCHOTABLERO];

        iniciarTablero();
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

                switch (tablero[i][j])
                {
                    case 0:

                        g.setColor(Color.white);

                        break;

                    case 1:

                        g.setColor(Color.green);

                        break;

                    case 2:

                        g.setColor(Color.cyan);

                        break;
                        
                    case 3:
                        
                        g.setColor(Color.red);

                    default:

                        break;

                }

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
        
        g.setFont(new Font("serif", Font.BOLD, 15));

        for (int i = 0; i < ALTOTABLERO; i++)
        {
            g.drawString("" + letra++, INICIOX - OFFSET, INICIOY + i * LADOCUADRO + OFFSET);
        }

        for (int i = 0; i < ANCHOTABLERO; i++)
        {
            g.drawString(i < 9 ? "0" + (i + 1) : "" + (i + 1), INICIOX + 5 + i * LADOCUADRO, INICIOY - 10);
        }
    }

    public void modificarTablero(int i, int j, int cambio)
    {
        if (i < ALTOTABLERO && i >= 0 && j < ANCHOTABLERO && j >= 0)
        {
            if (tablero[i][j] != 2)
            {
                tablero[i][j] = cambio;
            }
        }
    }

    public void recibirTablero(int[][] tablero)
    {
        this.tablero = tablero;
    }

    public int[][] obtenerTablero()
    {
        return tablero;
    }

    public void iniciarTablero()
    {
        for (int[] I : tablero)
        {
            for (int i = 0; i < I.length; i++)
            {
                if (I[i] != 2)
                {
                    I[i] = 0;
                }
            }
        }
    }
}
