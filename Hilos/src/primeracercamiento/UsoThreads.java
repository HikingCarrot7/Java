package primeracercamiento;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import static java.lang.System.exit;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.interrupted;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

public class UsoThreads
{

    public static void main(String[] args)
    {

        JFrame marco = new MarcoRebote();

        marco.setDefaultCloseOperation(EXIT_ON_CLOSE);

        marco.setVisible(true);

    }

}

class PelotaHilos implements Runnable
{

    private Container lamina;

    private Pelota pelota;

    public PelotaHilos(Container lamina, Pelota pelota)
    {
        this.lamina = lamina;
        this.pelota = pelota;
    }

    @Override
    public void run()
    {

        // System.out.println(Thread.currentThread().isInterrupted());
        while (!interrupted())
            try
            {
                sleep(4);

                pelota.mueve_pelota(lamina.getBounds());

                lamina.paint(lamina.getGraphics());

            } catch (InterruptedException e)
            {
                currentThread().interrupt();
            }

        // System.out.println(Thread.currentThread().isInterrupted());

        // System.out.println(Thread.currentThread().isInterrupted());
    }

}

//Movimiento de la pelota-----------------------------------------------------------------------------------------
class Pelota
{

    // Mueve la pelota invirtiendo posici�n si choca con l�mites
    public void mueve_pelota(Rectangle2D limites)
    {

        x += dx;

        y += dy;

        if (x < limites.getMinX())
        {

            x = limites.getMinX();

            dx = -dx;
        }

        if (x + TAMX >= limites.getMaxX())
        {

            x = limites.getMaxX() - TAMX;

            dx = -dx;
        }

        if (y < limites.getMinY())
        {

            y = limites.getMinY();

            dy = -dy;
        }

        if (y + TAMY >= limites.getMaxY())
        {

            y = limites.getMaxY() - TAMY;

            dy = -dy;

        }

    }

    // Forma de la pelota en su posici�n inicial
    public Ellipse2D getShape()
    {

        return new Ellipse2D.Double(x, y, TAMX, TAMY);
    }

    private static final int TAMX = 15;

    private static final int TAMY = 15;

    private double x = 0;

    private double y = 0;

    private double dx = 1;

    private double dy = 1;

}

// L�mina que dibuja las pelotas----------------------------------------------------------------------
class LaminaPelota extends JPanel
{

    // A�adimos pelota a la l�mina
    private static final long serialVersionUID = 1L;

    public void add(Pelota b)
    {

        pelotas.add(b);
    }

    public void paintComponent(Graphics g)
    {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        for (Pelota b : pelotas)
            g2.fill(b.getShape());
    }

    private ArrayList<Pelota> pelotas = new ArrayList<>();
}

//Marco con l�mina y botones------------------------------------------------------------------------------
class MarcoRebote extends JFrame
{

    private static final long serialVersionUID = 1L;

    public MarcoRebote()
    {

        setBounds(600, 300, 400, 350);

        setTitle("Rebotes");

        lamina = new LaminaPelota();

        add(lamina, CENTER);

        JPanel laminaBotones = new JPanel();

        ponerBoton(laminaBotones, "Dale!", (ActionEvent evento) ->
        {
            comienza_el_juego();
        });

        ponerBoton(laminaBotones, "Salir", (ActionEvent evento) ->
        {
            exit(0);
        });

        ponerBoton(laminaBotones, "Detener", (ActionEvent evento) ->
        {
            detener();
        });

        add(laminaBotones, SOUTH);
    }

    // Ponemos botones
    public void ponerBoton(Container c, String titulo, ActionListener oyente)
    {

        JButton boton = new JButton(titulo);

        c.add(boton);

        boton.addActionListener(oyente);

    }

    // A�ade pelota y la bota 1000 veces
    public void comienza_el_juego()
    {

        Pelota pelota = new Pelota();

        lamina.add(pelota);

        t = new Thread(new PelotaHilos(lamina, pelota));

        t.start();
    }

    public void detener()
    {
        t.interrupt();
    }

    private LaminaPelota lamina;

    private Thread t;

}
