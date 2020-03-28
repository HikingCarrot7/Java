package com.cliente;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import static java.net.InetAddress.getByName;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import static java.util.concurrent.Executors.newFixedThreadPool;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.SwingUtilities.invokeLater;

/**
 *
 * @author HikingCarrot7
 */
public final class Cliente extends JFrame implements Runnable
{

    private JTextField campoId;
    private JTextArea areaPantalla;
    private JPanel panelTablero, soporte;
    private Cuadro[][] tablero;
    private Cuadro cuadroActual;
    private Socket conexion;
    private Scanner in;
    private Formatter out;
    private String miMarca;
    private final String host;
    private final String MARCA_X = "X", MARCA_O = "O";
    private boolean miTurno;

    public Cliente(String host)
    {
        this.host = host;
        iniciarElementos();
        iniciarVentana();
    }

    public void iniciarElementos()
    {
        areaPantalla = new JTextArea(4, 30);
        areaPantalla.setEnabled(false);

        panelTablero = new JPanel(new GridLayout(3, 3, 0, 0));
        tablero = new Cuadro[3][3];

        //Iniciamos el tablero
        for (int i = 0; i < tablero.length; i++)
            for (int j = 0; j < tablero[i].length; j++)
            {
                tablero[i][j] = new Cuadro("", i * tablero.length + j);
                panelTablero.add(tablero[i][j]);
            }

        campoId = new JTextField();
        campoId.setEnabled(false);

        soporte = new JPanel();
        soporte.add(panelTablero, CENTER);

        iniciarCliente();
    }

    public void iniciarCliente()
    {
        try
        {
            conexion = new Socket(getByName(host), 9999);

            in = new Scanner(conexion.getInputStream());
            out = new Formatter(conexion.getOutputStream());

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        ExecutorService thread = newFixedThreadPool(1);
        thread.execute(this);
    }

    public void iniciarVentana()
    {
        setBounds(0, 0, 325, 225);
        setResizable(false);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setTitle("Cliente");
        add(campoId, NORTH);
        add(soporte, CENTER);
        add(new JScrollPane(areaPantalla), SOUTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void run()
    {
        miMarca = in.nextLine();

        invokeLater(() ->
        {
            campoId.setText("Usted es el jugador \"" + miMarca + "\"");
        });

        miTurno = (miMarca.equals(MARCA_X));

        while (true)
            if (in.hasNext())
                procesarMensaje(in.nextLine());
    }

    public void procesarMensaje(String mensaje)
    {
        switch (mensaje)
        {
            case "Movimiento válido.":
                mostrarMensaje("Movimiento válido, por favor espere\n");
                establecerMarca(cuadroActual, miMarca);
                break;

            case "Movimiento inválido, intente de nuevo.":
                mostrarMensaje(mensaje + "\n");
                miTurno = true;
                break;

            case "El oponente realizó un movimiento.":
                int ubicacion = in.nextInt();
                in.nextLine();
                int fila = ubicacion / 3;
                int columna = ubicacion % 3;
                establecerMarca(tablero[fila][columna], miMarca.equals(MARCA_X) ? MARCA_O : MARCA_X);
                mostrarMensaje("El oponenete hizo un movimiento. Ahora es su turno.\n");
                miTurno = true;
                break;

            default:
                mostrarMensaje(mensaje + "\n");
                break;
        }

    }

    private void mostrarMensaje(final String mensajeMostrar)
    {
        invokeLater(() ->
        {
            areaPantalla.append(mensajeMostrar);
        });
    }

    private void establecerMarca(final Cuadro cuadroMarcar, final String marca)
    {
        invokeLater(() ->
        {
            cuadroMarcar.establecerMarca(marca);
        });
    }

    public void enviarCuadroClic(int ubicacion)
    {
        if (miTurno)
        {
            out.format("%d\n", ubicacion);
            out.flush();

            miTurno = false;
        }
    }

    public void establecerCuadroActual(Cuadro cuadroActual)
    {
        this.cuadroActual = cuadroActual;
    }

    private final class Cuadro extends JPanel
    {

        private String marca;
        private final int ubicacion;

        public Cuadro(String marca, int ubicacion)
        {
            this.marca = marca;
            this.ubicacion = ubicacion;

            anadirMouseListener();
        }

        public void anadirMouseListener()
        {
            addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseReleased(MouseEvent e)
                {
                    establecerCuadroActual(Cuadro.this);
                    enviarCuadroClic(obtenerUbicacionCuadro());
                }

            });

        }

        @Override
        public Dimension getPreferredSize()
        {
            return new Dimension(30, 30);
        }

        @Override
        public Dimension getMinimumSize()
        {
            return getPreferredSize();
        }

        public int obtenerUbicacionCuadro()
        {
            return ubicacion;
        }

        public void establecerMarca(String marca)
        {
            this.marca = marca;

            repaint();
        }

        @Override
        public void paint(Graphics g)
        {
            super.paint(g);

            g.drawRect(0, 0, 29, 29);
            g.drawString(marca, 11, 20);
        }
    }

}
