package com.game.src.net;

import java.io.Serializable;

/**
 *
 * @author HikingCarrot7
 */
public final class MensajeEnviar implements Serializable
{

    private final int fila, columna, miMarca;

    public MensajeEnviar(int fila, int columna, int miMarca)
    {
        this.fila = fila;
        this.columna = columna;
        this.miMarca = miMarca;
    }

    public int getFila()
    {
        return fila;
    }

    public int getColumna()
    {
        return columna;
    }

    public int getMiMarca()
    {
        return miMarca;
    }

}
