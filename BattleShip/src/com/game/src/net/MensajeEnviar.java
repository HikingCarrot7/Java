package com.game.src.net;

import java.io.Serializable;

/**
 *
 * @author HikingCarrot7
 */
public final class MensajeEnviar implements Serializable
{

    private final int fila, columna, miMarca;
    private final boolean nuevo;
    private final String ip;

    public MensajeEnviar(int fila, int columna, int miMarca, boolean nuevo, String ip)
    {
        this.fila = fila;
        this.columna = columna;
        this.miMarca = miMarca;
        this.nuevo = nuevo;
        this.ip = ip;
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

    public boolean isNuevo()
    {
        return nuevo;
    }

    public String getIp()
    {
        return ip;
    }

}
