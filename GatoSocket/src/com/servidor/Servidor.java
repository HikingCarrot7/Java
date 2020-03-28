package com.servidor;

import static java.awt.BorderLayout.CENTER;
import java.io.IOException;
import static java.lang.System.exit;
import static java.lang.System.out;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import static java.util.concurrent.Executors.newFixedThreadPool;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import static javax.swing.SwingUtilities.invokeLater;

/**
 *
 * @author HikingCarrot7
 */
public final class Servidor extends JFrame
{

    private String[] tablero;
    private JTextArea textoSalida;
    private Jugador[] jugadores;
    private ServerSocket servidor;
    private int jugadorActual;
    private final static int JUGADOR_X = 0;
    private final static int JUGADOR_O = 1;

    private final static String[] MARCAS =
    {
        "X", "O"
    };

    private ExecutorService ejecutarJuego;
    private Lock bloqueoJuego;
    private Condition otroJugadorConectado, turnoOtroJugador;

    public Servidor()
    {
        iniciarElementos();
        iniciarServer();
        elementosVentana();
    }

    //Iniciamos todos los elementos
    public void iniciarElementos()
    {
        tablero = new String[9];

        ejecutarJuego = newFixedThreadPool(2);
        bloqueoJuego = new ReentrantLock();
        otroJugadorConectado = bloqueoJuego.newCondition();
        turnoOtroJugador = bloqueoJuego.newCondition();

        for (int i = 0; i < 9; i++)
            tablero[i] = "";

        jugadores = new Jugador[2];
        jugadorActual = JUGADOR_X;

        textoSalida = new JTextArea();

        textoSalida.setText("Servidor esperando conexiones \n");
    }

    //Crear el servidor
    public void iniciarServer()
    {
        try
        {
            servidor = new ServerSocket(9999, 2);

        } catch (IOException ex)
        {
            out.println(ex.getMessage());
            exit(1);
        }

    }

    /**
     * Ejecutar los hilos de los jugadores (Espera hasta que se conecten).
     */
    public void execute()
    {
        for (int i = 0; i < jugadores.length; i++)
            try
            {
                jugadores[i] = new Jugador(servidor.accept(), i);
                ejecutarJuego.execute(jugadores[i]);

            } catch (IOException ex)
            {
                out.println(ex.getMessage());
            }

        bloqueoJuego.lock();

        try
        {
            jugadores[JUGADOR_X].establecerSuspendido(false);
            otroJugadorConectado.signal();

        } finally
        {
            bloqueoJuego.unlock();
        }

    }

    //Muestra el mensaje en el textarea pero con una cierta particularidad ...
    private void mostrarMensaje(final String mensajeAMostrar)
    {
        invokeLater(() -> textoSalida.append(mensajeAMostrar));
    }

    public boolean validarYMover(int ubicacion, int jugador)
    {
        while (jugador != jugadorActual)
        {
            bloqueoJuego.lock();

            try
            {
                turnoOtroJugador.await();

            } catch (InterruptedException ex)
            {
                out.println(ex.getMessage());

            } finally
            {
                bloqueoJuego.unlock();
            }
        }

        if (!estaOcupado(ubicacion))
        {
            tablero[ubicacion] = MARCAS[jugadorActual];
            jugadorActual = (jugadorActual + 1) % 2;
            jugadores[jugadorActual].otroJugadorMovio(ubicacion);

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

    //Esta ocupada esta posicion ?
    public boolean estaOcupado(int ubicacion)
    {
        return tablero[ubicacion].equals(MARCAS[JUGADOR_X]) || tablero[ubicacion].equals(MARCAS[JUGADOR_O]);
    }

    /**
     * Lógica de los movimientos.
     *
     * @return
     */
    public boolean seTerminoJuego()
    {
        return false;
    }

    //Elementos de la ventana
    public void elementosVentana()
    {
        setBounds(0, 0, 500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setAlwaysOnTop(true);
        setTitle("Servidor");
        add(textoSalida, CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    //Clase de los jugadores
    private final class Jugador implements Runnable
    {

        private Socket conexion;
        private int numeroJugador;
        private String marca;
        private boolean estado = true;
        private Scanner in;
        private Formatter out;

        public Jugador(Socket conexion, int numeroJugador)
        {
            this.conexion = conexion;
            this.numeroJugador = numeroJugador;
            marca = MARCAS[numeroJugador];

            try
            {
                in = new Scanner(conexion.getInputStream());
                out = new Formatter(conexion.getOutputStream());

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
                exit(1);
            }
        }

        @Override
        public void run()
        {
            try
            {
                mostrarMensaje("Jugador " + marca + " conectado\n");
                out.format("%s\n", marca);
                out.flush();

                if (numeroJugador == JUGADOR_X)
                {
                    out.format("%s\n%s", "Jugador X conectado", "Esperando al otro jugador");
                    out.flush();

                    bloqueoJuego.lock();

                    try
                    {
                        while (estado)
                            otroJugadorConectado.await();

                    } catch (InterruptedException ex)
                    {
                        System.out.println(ex.getMessage());

                    } finally
                    {
                        bloqueoJuego.unlock();
                    }

                    out.format("\nEl otro jugador se conectó. \nAhora es su turno\n");
                    out.flush();

                } else
                {
                    out.format("El jugador O se conectó, por favor espere\n");
                    out.flush();
                }

                while (!seTerminoJuego())
                {
                    int ubicacion = 0;

                    if (in.hasNext())
                        ubicacion = in.nextInt();

                    if (validarYMover(ubicacion, numeroJugador))
                    {
                        mostrarMensaje("\nUbicación " + ubicacion);
                        out.format("Movimiento válido.\n");
                        out.flush();

                    } else
                    {
                        out.format("Movimiento inválido, intente de nuevo.\n");
                        out.flush();
                    }
                }

            } finally
            {
                try
                {
                    conexion.close();

                } catch (IOException e)
                {
                    System.out.println(e.getMessage());
                    exit(1);
                }

            }

        }

        public void otroJugadorMovio(int ubicacion)
        {
            out.format("El oponente realizó un movimiento.\n");
            out.format("%d\n", ubicacion);
            out.flush();
        }

        public void establecerSuspendido(boolean estado)
        {
            this.estado = estado;
        }

    }

}
