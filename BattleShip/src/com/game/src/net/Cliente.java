package com.game.src.net;

import com.game.src.UI.Menu;
import com.game.src.map.Cuadricula;
import com.game.src.graphics.Drawable;
import com.game.src.graphics.Explosion;
import com.game.src.input.InputListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author HikingCarrot7
 */
public final class Cliente implements Drawable, InputListener, Runnable
{

    private final int PLAYER1 = 0;
    private int miMarca, filaAMostrar, columnaAMostrar;
    private volatile boolean miTurno, otroJugadorConectado;
    private boolean acertado;
    private final String host;
    private String miIp;
    private final Cuadricula barcos, enemigo;
    private final Menu menu;
    private final ArrayList<Explosion> explosiones;
    private MensajeEnviar mensaje;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket cliente;
    private ServerSocket serverSocket;
    private Thread thread;

    public Cliente(String host, Menu menu)
    {
        barcos = new Cuadricula(130, 80);
        enemigo = new Cuadricula(130, 420);
        explosiones = new ArrayList<>();

        this.host = host;
        this.menu = menu;

        iniciarCliente(host);

    }

    @Override
    public void tick()
    {

        explosiones.stream().forEach((e) ->
        {
            e.tick();

        });
    }

    @Override
    public void render(Graphics2D g)
    {
        barcos.render(g);
        enemigo.render(g);

        dibujarHUD(g);

        explosiones.stream().forEach((e) ->
        {
            e.render(g);
        });

    }

    public void iniciarCliente(String host)
    {
        try
        {
            cliente = new Socket(host, 9999);

            contactaServidor();

            thread = new Thread(this);
            thread.start();

            miIp = InetAddress.getLocalHost().getHostAddress();

        } catch (IOException | ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());

            menu.setIpValida(false);

        }
    }

    private void contactaServidor() throws IOException, ClassNotFoundException
    {

        out = new ObjectOutputStream(cliente.getOutputStream());

        in = new ObjectInputStream(cliente.getInputStream());

        MensajeEnviar mensajeEnvio = new MensajeEnviar(0, 0, 0, 2, true, false, InetAddress.getLocalHost().getHostAddress());
        out.writeObject(mensajeEnvio);

        System.out.println("Esperando los datos del server");

        mensaje = (MensajeEnviar) in.readObject();

        System.out.println("He recibido los primeros datos del servidor");

        miMarca = mensaje.getMiMarca();

        miTurno = miMarca == PLAYER1;

        otroJugadorConectado = mensaje.getJugadoresConectados() == 2;

        menu.setIpValida(true);

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

                acertado = mensajeRecibido.getAcertado();
                filaAMostrar = mensajeRecibido.getFila();
                columnaAMostrar = mensajeRecibido.getColumna();

                if (mensajeRecibido.getFila() == -1)
                {
                    otroJugadorConectado = true;

                } else if (!mensajeRecibido.getIp().equals("-1"))
                {
                    modificarTableroAliado(mensajeRecibido);
                }

            }

        } catch (IOException | ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());

        }
    }

    private void modificarTableroAliado(MensajeEnviar mensaje)
    {
        boolean acerto = barcos.obtenerTablero()[mensaje.getFila()][mensaje.getColumna()] == 2;

        barcos.modificarTablero(mensaje.getFila(), mensaje.getColumna(), 3, true);

        explosiones.add(new Explosion(mensaje.getColumna() * 24 + 130, mensaje.getFila() * 24 + 80, Color.cyan));

        //Avisar si el enemigo le dio a uno de mis barcos aliados
        try
        {
            Socket socketEnvio = new Socket(host, 9999);

            out = new ObjectOutputStream(socketEnvio.getOutputStream());

            System.out.println(acertado);

            MensajeEnviar mensajeEnvio = new MensajeEnviar(mensaje.getFila(), mensaje.getColumna(), miMarca, 2, false, acerto, "-1");

            out.writeObject(mensajeEnvio);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());

        }

        miTurno = true;

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        int fila = (e.getY() - 420) / 24, columna = (e.getX() - 130) / 24;

        if (fila < 10 && fila >= 0 && columna < 20 && columna >= 0 && miTurno && otroJugadorConectado)
        {
            try
            {

                Socket envioDatos = new Socket(host, 9999);
                
                enemigo.modificarTablero(fila, columna, 3, false);

                explosiones.add(new Explosion(columna * 24 + 130, fila * 24 + 420, Color.red));

                out = new ObjectOutputStream(envioDatos.getOutputStream());

                mensaje = new MensajeEnviar(fila, columna, miMarca, 2, false, false, InetAddress.getLocalHost().getHostAddress());

                out.writeObject(mensaje);

                miTurno = false;

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());

            }

        }

    }

    public void dibujarHUD(Graphics2D g)
    {
        Font title = new Font("serif", Font.BOLD, 25), instrucciones = new Font("serif", Font.BOLD, 13);

        g.setFont(title);
        g.drawString("SU TABLERO", 300, 30);
        g.drawString("TABLERO ENEMIGO", 250, 360);

        g.setFont(instrucciones);

        if (otroJugadorConectado)
        {
            g.drawString(miTurno ? ">Es su turno, dispare!" : ">Debe esperar su turno para disparar!", 5, 15);

        } else
        {
            g.drawString(">Esperando la conexion del otro jugador...", 5, 15);
        }

        g.drawString(">Su direccion IP es: " + miIp, 5, 30);

        if (menu.getServer())
        {
            g.drawString(">Eres un server!", 630, 20);
        }

        if (acertado)
        {
            g.drawString(">Le diste a un barco enemigo en las coordenadas: " + (char) (65 + filaAMostrar) + (columnaAMostrar + 1), 5, 45);
        }

    }

    public void setBarcos(int[][] tablero)
    {
        barcos.recibirTablero(tablero);
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
    }

}
