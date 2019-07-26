package com.game.src.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private final int PLAYER1 = 0;
    private int jugadorActual = 1;
    private ServerSocket server;
    private ExecutorService ejecutarJuego;
    private Lock bloqueoJuego;
    private Condition turnoOtroJugador, otroJugadorConectado;
    private ObjectOutputStream out;
    private ObjectInputStream in;

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
        int i = 0;
        
        try
        {
            Socket SOCKETPLAYER1 = server.accept();
            Socket SOCKETPLAYER2 = server.accept();

            players[0] = new Player(SOCKETPLAYER1, SOCKETPLAYER2, i);
            ejecutarJuego.execute(players[0]);

            players[1] = new Player(SOCKETPLAYER2, SOCKETPLAYER1, ++i);

            ejecutarJuego.execute(players[1]);

        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void validarTurno(int jugador)
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

    }

    //Validar aqui el tablero del jugador actual 
    private boolean seTerminoJuego()
    {
        return false;
    }

    private class Player implements Runnable
    {

        private final Socket miSocket, enemigoSocket;
        private final int numeroJugador;

        public Player(Socket miSocket, Socket enemigoSocket, int numeroJugador)
        {
            this.miSocket = miSocket;
            this.enemigoSocket = enemigoSocket;
            this.numeroJugador = numeroJugador;

            try
            {
                in = new ObjectInputStream(enemigoSocket.getInputStream());
                out = new ObjectOutputStream(miSocket.getOutputStream());

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

        }

        @Override
        public void run()
        {
            try
            {
                out.writeObject(new MensajeEnviar(0, 0, numeroJugador));

                while (!seTerminoJuego())
                {
                    validarTurno(numeroJugador);

                    out.writeObject(in.readObject());

                    jugadorActual = (jugadorActual + 1) % 2;

                    bloqueoJuego.lock();

                    try
                    {
                        turnoOtroJugador.signal();

                    } finally
                    {
                        bloqueoJuego.unlock();
                    }

                }

            } catch (IOException | ClassNotFoundException ex)
            {
                System.out.println(ex.getMessage());

            } finally
            {
                try
                {
                    miSocket.close();
                    enemigoSocket.close();

                } catch (IOException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }

    }

}
