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

    private final int INICIOX, INICIOY, ANCHOMAPA = 20, ALTOMAPA = 10;
    private final int[][] mapa;
    private final int LADOCUADRO = 24;

    public Cuadricula(int INICIOX, int INICIOY)
    {
        this.INICIOX = INICIOX;
        this.INICIOY = INICIOY;

        mapa = new int[ALTOMAPA][ANCHOMAPA];

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
        for (int i = 0; i < mapa.length; i++)
        {
            for (int j = 0; j < mapa[i].length; j++)
            {

                g.setColor(mapa[i][j] == 1 ? Color.red : Color.white);
                g.setStroke(new BasicStroke(2));
                g.drawRect(j * (LADOCUADRO) + INICIOX, i * (LADOCUADRO) + INICIOY, LADOCUADRO, LADOCUADRO);

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
        if (i < ALTOMAPA && i >= 0 && j < ANCHOMAPA && j >= 0)
        {
            mapa[i][j] = cambio;
        }
    }

}
