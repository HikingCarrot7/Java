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
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private boolean miTurno;
    private volatile boolean disparo;
    private MensajeEnviar mensaje;

    public Cliente(String host)
    {
        barcos = new Cuadricula(130, 80);
        enemigo = new Cuadricula(130, 420);

        iniciarCliente(host);

    }

    public void iniciarCliente(String host)
    {
        try
        {
            cliente = new Socket(host, 9999);

            in = new ObjectInputStream(cliente.getInputStream());
            out = new ObjectOutputStream(cliente.getOutputStream());

            ExecutorService thread = Executors.newFixedThreadPool(1);

            thread.execute(this);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
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

    public void dibujarHUD(Graphics2D g)
    {
        Font title = new Font("serif", Font.BOLD, 25), instrucciones = new Font("serif", Font.BOLD, 15);

        g.setFont(title);
        g.drawString("SU TABLERO", 300, 30);
        g.drawString("TABLERO ENEMIGO", 250, 360);

        g.setFont(instrucciones);
        g.drawString(">Debe esperar su turno para disparar!", 10, 20);

    }

    public void setBarcos(int[][] tablero)
    {
        barcos.recibirTablero(tablero);
    }

    @Override
    public void run()
    {
        try
        {
            mensaje = (MensajeEnviar) in.readObject();

            miMarca = mensaje.getMiMarca();

            miTurno = miMarca == 0;

            while (true)
            {
                if (disparo)
                {
                    out.writeObject(mensaje);
                    miTurno = false;
                    disparo = false;
                }

                mensaje = (MensajeEnviar) in.readObject();
                barcos.modificarTablero(mensaje.getFila(), mensaje.getColumna(), 3, true);

                miTurno = true;

            }

        } catch (IOException | ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (miTurno)
        {
            enemigo.modificarTablero((e.getY() - 420) / 24, (e.getX() - 130) / 24, 3, false);

            disparo = true;

            mensaje = new MensajeEnviar((e.getY() - 420) / 24, (e.getX() - 130) / 24, miMarca);

        }

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {

    }

}
