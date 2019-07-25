package com.game.src.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author HikingCarrot7
 */
public final class Server
{

    private Player[] players;
    private final int PLAYER1 = 0, PLAYER2 = 1;
    private int jugadorActual = 0;
    private ServerSocket server;
    private ExecutorService ejecutarJuego;
    private Lock bloqueoJuego;
    private Condition turnoOtroJugador, otroJugadorConectado;

    public Server()
    {
        iniciarElementos();

        iniciarServer();
    }

    private void iniciarElementos()
    {
        ejecutarJuego = Executors.newFixedThreadPool(2);
        bloqueoJuego = new ReentrantLock();
        turnoOtroJugador = bloqueoJuego.newCondition();
        otroJugadorConectado = bloqueoJuego.newCondition();

        players = new Player[2];

    }

    private void iniciarServer()
    {
        try
        {
            server = new ServerSocket(9999, 2);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void execute()
    {
        for (int i = 0; i < players.length; i++)
        {
            try
            {
                players[i] = new Player(server.accept(), i);

                ejecutarJuego.execute(players[i]);

            } catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }

        bloqueoJuego.lock();

        try
        {
            players[PLAYER1].establecerSuspendido(false);

            otroJugadorConectado.signal();

        } finally
        {
            bloqueoJuego.unlock();
        }
    }

    public boolean validarMovimiento(int x, int y, int jugador)
    {
        while (jugador != jugadorActual)
        {
            bloqueoJuego.lock();

            try
            {
                turnoOtroJugador.await();

            } catch (InterruptedException ex)
            {
                System.out.println(ex.getMessage());

            } finally
            {
                bloqueoJuego.unlock();
            }
        }

        if (!estaOcupado())
        {
            jugadorActual = (jugadorActual + 1) % 2;

            bloqueoJuego.lock();

            try
            {
                turnoOtroJugador.signal();

            } finally
            {
                bloqueoJuego.unlock();
            }

            return true;
        }

        return false;

    }

    //Ya habia disparado en esta posicion el jugador anteriormente?
    private boolean estaOcupado()
    {
        return false;
    }

    //Validar aqui el tablero del jugador actual 
    private boolean seTerminoJuego()
    {
        return false;
    }

    private class Player implements Runnable
    {

        private final Socket socket;
        private final int numeroJugador;
        private boolean suspendido = true;

        public Player(Socket socket, int numeroJugador)
        {
            this.socket = socket;
            this.numeroJugador = numeroJugador;
        }

        @Override
        public void run()
        {
            try
            {
                if (numeroJugador == PLAYER1)
                {
                    bloqueoJuego.lock();

                    try
                    {
                        while (suspendido)
                        {
                            otroJugadorConectado.await();
                        }

                    } catch (InterruptedException e)
                    {
                        System.out.println(e.getMessage());

                    } finally
                    {
                        bloqueoJuego.unlock();
                    }
                }
                
                while(!seTerminoJuego())
                {
                    
                }

            } finally
            {
                try
                {
                    socket.close();

                } catch (IOException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }

        public void establecerSuspendido(boolean suspendido)
        {
            this.suspendido = suspendido;
        }

    }

}
