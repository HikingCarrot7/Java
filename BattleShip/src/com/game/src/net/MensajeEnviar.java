package com.game.src.net;

import java.io.Serializable;

/**
 *
 * @author HikingCarrot7
 */
public final class MensajeEnviar implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private final int fila, columna, miMarca, jugadoresConectados;
    private final boolean nuevo, acertado;
    private final String ip;

    public MensajeEnviar(int fila, int columna, int miMarca, int jugadoresConectados, boolean nuevo, boolean acertado, String ip)
    {
        this.fila = fila;
        this.columna = columna;
        this.miMarca = miMarca;
        this.jugadoresConectados = jugadoresConectados;
        this.nuevo = nuevo;
        this.acertado = acertado;
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

    public int getJugadoresConectados()
    {
        return jugadoresConectados;
    }

    public boolean isNuevo()
    {
        return nuevo;
    }

    public boolean getAcertado()
    {
        return acertado;
    }

    public String getIp()
    {
        return ip;
    }

}
