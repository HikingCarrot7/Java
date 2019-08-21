package com.game.src.net;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author HikingCarrot
 */
public final class Servidor extends JFrame
{

    private ServerSocket serverSocket;
    private Socket cliente;
    private DataInputStream in;
    private DataOutputStream out;
    private Thread thread;
    private String coordenadas, jugador;
    private int contPlayers = 1;

    private JPanel panel;
    private JLabel player1, player2, infoPlayer1, infoPlayer2;

    public Servidor()
    {
        iniciarServer();

        iniciarLayout();

        iniciarJFrame();

        iniciarElementos();

        anadirElementos();

    }

    public void iniciarElementos()
    {
        player1 = new JLabel();
        player2 = new JLabel();
        infoPlayer1 = new JLabel("Player 1");
        infoPlayer2 = new JLabel("Player 2");

    }

    public void anadirElementos()
    {
        panel.add(infoPlayer1);
        panel.add(player1);
        panel.add(infoPlayer2);
        panel.add(player2);
    }

    public void iniciarJFrame()
    {
        setBounds(0, 0, 600, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Server!");
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void iniciarServer()
    {
        thread = new Thread(() ->
        {
            while (true)
            {
                try
                {
                    serverSocket = new ServerSocket(9999);
                    System.out.println("Waiting for a connection...");
                    cliente = serverSocket.accept();

                    in = new DataInputStream(cliente.getInputStream());
                    out = new DataOutputStream(cliente.getOutputStream());

                    if (in.readUTF().equals("newUser"))
                    {
                        out.writeUTF("" + getPlayers());
                        setPlayers(getPlayers() + 1);
                    }

                    jugador = in.readUTF();

                    switch (jugador)
                    {
                        case "1":

                            coordenadas = in.readUTF();

                            player1.setText(coordenadas);

                            break;

                        case "2":

                            coordenadas = in.readUTF();

                            player2.setText(coordenadas);

                            break;
                    }

                    serverSocket.close();
                    cliente.close();
                    in.close();

                } catch (IOException ex)
                {
                    System.out.println(ex.getMessage());
                }

            }
        });

        thread.start();
    }

    public void iniciarLayout()
    {
        panel = new JPanel(new LayoutManager()
        {
            @Override
            public void layoutContainer(Container c)
            {
                int x = 20, y = 20;

                for (int i = 0; i < c.getComponentCount(); i++)
                {
                    Component component = c.getComponent(i);

                    if ((i + 1) % 2 == 0)
                    {
                        component.setBounds(x, y, 300, 25);
                    } else
                    {
                        component.setBounds(x, y, 60, 25);
                    }

                    x += 50;

                    if ((i + 1) % 2 == 0)
                    {
                        x = 20;

                        y += 30;
                    }

                }

            }

            @Override
            public void addLayoutComponent(String name, Component comp)
            {
            }

            @Override
            public void removeLayoutComponent(Component comp)
            {
            }

            @Override
            public Dimension preferredLayoutSize(Container parent)
            {
                return null;
            }

            @Override
            public Dimension minimumLayoutSize(Container parent)
            {
                return null;
            }

        });
    }

    public String getCoordenadas()
    {
        return coordenadas;
    }

    public int getPlayers()
    {
        return contPlayers;
    }

    public void setPlayers(int contPlayers)
    {
        this.contPlayers = contPlayers;
    }

}
