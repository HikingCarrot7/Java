package carreradecaballos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public final class Hipodromo extends JPanel implements Observer
{

    private static final long serialVersionUID = 1L;

    private JProgressBar[] caballosBars;
    private JLabel[] nombres;
    private JLabel nomGanador;
    private JPanel carrera, ganador, iniciar;
    private JButton dale;
    private Thread[] threads;

    public Hipodromo()
    {

        setLayout(new BorderLayout());

        iniciarElementos();

        anadirElementos();

        add(carrera, BorderLayout.CENTER);
        add(ganador, BorderLayout.NORTH);
        add(iniciar, BorderLayout.SOUTH);

    }

    public void iniciarElementos()
    {
        carrera = new JPanel();
        carrera.setLayout(new Diseno());

        nombres = new JLabel[4];
        caballosBars = new JProgressBar[4];

        ganador = new JPanel();
        nomGanador = new JLabel("");

        iniciar = new JPanel();
        dale = new JButton("Dale!");

        accionBoton();

    }

    public void anadirElementos()
    {

        for (int i = 0; i < nombres.length; i++)
        {
            nombres[i] = new JLabel("Caballo: " + (i + 1));
            carrera.add(nombres[i]);

            caballosBars[i] = new JProgressBar();
            caballosBars[i].setStringPainted(true);
            caballosBars[i].setString("0%");
            carrera.add(caballosBars[i]);

        }

        ganador.add(nomGanador);
        iniciar.add(dale);

    }

    public void accionBoton()
    {

        dale.addActionListener((e) ->
        {

            nomGanador.setText("");

            threads = new Thread[4];

            dale.setEnabled(false);

            for (int i = 0; i < threads.length; i++)
            {

                Caballo c = new Caballo((i + 1) + "");

                c.addObserver(this);

                threads[i] = new Thread(c);

                threads[i].start();

            }

        });

    }

    private void terminar()
    {
        for (Thread thread : threads)
            thread.interrupt();
    }

    @Override
    public void update(Observable o, Object obj)
    {
        int index = Integer.parseInt(((Caballo) o).getNombre()) - 1;

        caballosBars[index].setValue((int) obj);
        caballosBars[index].setString((int) obj < 100 ? obj + "%" : "100%");

        if ((int) obj >= 100)
        {
            terminar();

            dale.setEnabled(true);

            nomGanador.setText("El ganador es: " + ((Caballo) o).getNombre());
        }

    }

    private class Diseno implements LayoutManager
    {

        @Override
        public void layoutContainer(Container c)
        {
            int x = 20, y = 50;

            for (int i = 0; i < c.getComponentCount(); i++)
            {

                if (i + 1 % 2 == 0)
                    c.getComponent(i).setBounds(x, y, 100, 50);

                else
                    c.getComponent(i).setBounds(x, y, 350, 30);

                x += 80;

                if (i % 2 != 0)
                {
                    y += 100;
                    x = 20;
                }

            }

        }

        @Override
        public void addLayoutComponent(String name, Component comp)
        {
        }

        @Override
        public Dimension minimumLayoutSize(Container parent)
        {
            return null;
        }

        @Override
        public Dimension preferredLayoutSize(Container parent)
        {
            return null;
        }

        @Override
        public void removeLayoutComponent(Component comp)
        {
        }

    }

}
