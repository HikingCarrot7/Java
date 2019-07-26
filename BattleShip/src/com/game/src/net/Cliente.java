package com.game.src.net;

import com.game.src.map.Cuadricula;
import com.game.src.graphics.Drawable;
import com.game.src.input.InputListener;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author HikingCarrot7
 */
public final class Cliente implements Drawable, InputListener, Runnable
{

    private final Cuadricula barcos, enemigo;
    private final int PLAYER1 = 0, PLAYER2 = 1;
    private int miMarca;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket cliente;
    private ServerSocket serverSocket;
    private boolean miTurno;
    private MensajeEnviar mensaje;
    private Thread thread;

    public Cliente(String host)
    {
        barcos = new Cuadricula(130, 80);
        enemigo = new Cuadricula(130, 420);

        iniciarCliente(host);

    }

    @Override
    public void tick()
    {

    }

    @Override
    public void render(Graphics2D g)
    {
        barcos.render(g);
        enemigo.render(g);

        dibujarHUD(g);

    }

    public void iniciarCliente(String host)
    {
        try
        {
            cliente = new Socket(host, 9999);

            contactaServidor();

            thread = new Thread(this);
            thread.start();

        } catch (IOException | ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());

        }
    }

    private void contactaServidor() throws IOException, ClassNotFoundException
    {

        out = new ObjectOutputStream(cliente.getOutputStream());
        in = new ObjectInputStream(cliente.getInputStream());

        MensajeEnviar mensajeEnvio = new MensajeEnviar(0, 0, 0, true, InetAddress.getLocalHost().getHostAddress());
        out.writeObject(mensajeEnvio);

        mensaje = (MensajeEnviar) in.readObject();

        miMarca = mensaje.getMiMarca();

        miTurno = miMarca == 0;

    }

    @Override
    public void run()
    {
        try
        {
            serverSocket = new ServerSocket(10000);

            Socket otroJugador;

            MensajeEnviar mensajeRecibido;

            while (true)
            {
                otroJugador = serverSocket.accept();

                in = new ObjectInputStream(otroJugador.getInputStream());

                mensajeRecibido = (MensajeEnviar) in.readObject();

                modificarTableroAliado(mensajeRecibido);

            }

        } catch (IOException | ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());

        }
    }

    private void modificarTableroAliado(MensajeEnviar mensaje)
    {
        barcos.modificarTablero(mensaje.getFila(), mensaje.getColumna(), 3, true);

        miTurno = true;

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        int fila = (e.getY() - 420) / 24, columna = (e.getX() - 130) / 24;
        
        if (fila < 10 && fila >= 0 && columna < 20 && columna >= 0 && miTurno)
        {
            try
            {
                System.out.println("Envie el paquete");

                enemigo.modificarTablero(fila, columna, 3, false);
                mensaje = new MensajeEnviar(fila, columna, miMarca, false, InetAddress.getLocalHost().getHostAddress());

                out.writeObject(mensaje);

                miTurno = false;

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());

            }

        }

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {

    }

    public void dibujarHUD(Graphics2D g)
    {
        Font title = new Font("serif", Font.BOLD, 25), instrucciones = new Font("serif", Font.BOLD, 15);

        g.setFont(title);
        g.drawString("SU TABLERO", 300, 30);
        g.drawString("TABLERO ENEMIGO", 250, 360);

        g.setFont(instrucciones);
        g.drawString(miTurno ? ">Es su turno, dispare!" : ">Debe esperar su turno para disparar!", 10, 20);

    }

    public void setBarcos(int[][] tablero)
    {
        barcos.recibirTablero(tablero);
    }

}
