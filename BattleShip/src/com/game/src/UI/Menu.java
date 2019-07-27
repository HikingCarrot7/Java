package com.game.src.UI;

import com.game.src.net.Cliente;
import com.game.src.graphics.Drawable;
import com.game.src.input.InputListener;
import com.game.src.main.Main;
import com.game.src.map.RandomLayout;
import com.game.src.net.MensajeEnviar;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author HikingCarrot7
 */
public class Menu implements Drawable, InputListener
{

    private final Rectangle play, connect, random, manual, continuar;
    private final Font title, buttonText, text;
    private final PlacingShips placingShips;
    private final RandomLayout randomLayout;
    private final Main main;
    private boolean ipValida = true, connecting = false, soyServer;
    private String ip = "";
    private Cliente cliente;

    public Menu(PlacingShips placingShips, RandomLayout randomLayout, Main main)
    {
        play = new Rectangle(Main.ANCHO / 2 - 210, 200, 200, 60);
        connect = new Rectangle(Main.ANCHO / 2 - 210, 300, 200, 60);
        random = new Rectangle(80, 200, 200, 60);
        manual = new Rectangle(Main.ANCHO - 480, 200, 200, 60);
        continuar = new Rectangle(Main.ANCHO / 2 - 210, 350, 200, 60);

        title = new Font("serif", Font.BOLD, 50);
        text = new Font("serif", Font.BOLD, 20);
        buttonText = new Font("serif", Font.BOLD, 45);

        this.randomLayout = randomLayout;
        this.placingShips = placingShips;
        this.main = main;

    }

    @Override
    public void tick()
    {
        if (Main.GAMESTATE.equals(Main.STATE.ColocandoBarcos))
        {
            placingShips.tick();
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Color.white);
        renderMenu(g);
    }

    public void renderMenu(Graphics2D g)
    {
        switch (Main.GAMESTATE)
        {
            case Menu:

                dibujarMenuPrincipal(g);

                break;

            case SelectingMode:

                dibujarSelectingMode(g);

                break;

            case ColocandoBarcos:

                placingShips.render(g);

                break;

            case ConnectingToServer:

                leyendoIP(g);

                break;

            default:

                break;
        }

    }

    private void dibujarMenuPrincipal(Graphics2D g)
    {
        g.setFont(title);
        g.drawString("BATTLESHIP!", Main.ANCHO / 2 - 270, 80);

        g.setFont(buttonText);
        g.draw(play);
        g.drawString("Play!", play.x + 55, play.y + 45);

        g.draw(connect);
        g.drawString("Connect...", connect.x + 5, connect.y + 45);

    }

    private void dibujarSelectingMode(Graphics2D g)
    {
        g.setFont(text);
        g.drawString("¿Como deseas generar tu tablero?", Main.ANCHO / 2 - 250, Main.ALTO / 2 - 180);

        g.setFont(buttonText);
        g.drawString("Random", random.x + 20, random.y + 45);
        g.draw(random);

        g.drawString("Manual", manual.x + 25, manual.y + 45);
        g.draw(manual);
    }

    private void leyendoIP(Graphics2D g)
    {
        Font title1 = new Font("serif", Font.BOLD, 30), title2 = new Font("serif", Font.BOLD, 60);

        g.setFont(title1);
        g.drawString("Inserta la IP del servidor", Main.ANCHO / 2 - 270, 60);

        g.setFont(title2);
        g.drawString("IP: " + ip, 40, 180);
        g.draw(continuar);

        g.setFont(buttonText);
        g.drawString("Continuar", continuar.x + 2, continuar.y + 45);

        if (!ipValida)
        {
            g.setFont(text);
            g.drawString("IP NO valida!", continuar.x + 40, continuar.y + 100);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        placingShips.mouseMoved(e);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        detectarRectangle(e);

        placingShips.mousePressed(e);
    }

    public void detectarRectangle(MouseEvent e)
    {
        Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);

        if (r.intersects(play) && Main.GAMESTATE.equals(Main.STATE.Menu))
        {
            Main.GAMESTATE = Main.STATE.SelectingMode;

        } else if (r.intersects(connect) && Main.GAMESTATE.equals(Main.STATE.Menu))
        {
            Main.GAMESTATE = Main.STATE.ConnectingToServer;

            connecting = true;

            soyServer = false;

        } else if (r.intersects(manual) && Main.GAMESTATE.equals(Main.STATE.SelectingMode))
        {

            if (!connecting)
            {
                crearServer();
            }

            Main.GAMESTATE = Main.STATE.ColocandoBarcos;

        } else if (r.intersects(random) && Main.GAMESTATE.equals(Main.STATE.SelectingMode))
        {
            if (connecting)
            {
                main.crearCliente(ip.trim());

            } else
            {
                crearServer();
            }

            cliente.setBarcos(randomLayout.generarTablero());

            Main.GAMESTATE = Main.STATE.Jugando;

        } else if (r.intersects(continuar) && Main.GAMESTATE.equals(Main.STATE.ConnectingToServer))
        {
            try
            {
                Socket socket = new Socket(ip.trim(), 9999);

                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                out.writeObject(new MensajeEnviar(-2, 0, 0, 0, false, null));

                out.close();

                ipValida = true;

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());

                ipValida = false;
            }

            if (ipValida)
            {
                Main.GAMESTATE = Main.STATE.SelectingMode;
            }
        }

    }

    private void crearServer()
    {
        try
        {
            main.crearClienteYServer(InetAddress.getLocalHost().getHostAddress());

            setServer(true);

        } catch (UnknownHostException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == 8)
        {
            if (ip.length() > 0)
            {
                ip = ip.substring(0, ip.length() - 1);
            }

        } else if (ip.length() < 16)
        {
            ip += e.getKeyChar();
        }

    }

    public void setIpValida(boolean ipValida)
    {
        this.ipValida = ipValida;
    }

    public void crearCliente()
    {
        main.crearCliente(ip.trim());
    }

    public void setServer(boolean soyServer)
    {
        this.soyServer = soyServer;
    }

    public boolean getServer()
    {
        return soyServer;
    }

}
