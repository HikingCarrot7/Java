package sprite;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Animation extends JFrame
{

    private static final long serialVersionUID = 1L;

    private Lamina lamina;

    public Animation()
    {
        lamina = new Lamina();

        setBounds(0, 0, 500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Animation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(lamina);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new Animation();
    }
}

class Lamina extends JPanel implements Runnable
{

    private static final long serialVersionUID = 1L;

    private Thread thread;

    private int ancho = 112, altura = 156, incremento = 0, mx = 0, my = 0;

    private Image ima;

    private BufferedImage bi;

    public Lamina()
    {
        bi = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);

        ima = Toolkit.getDefaultToolkit().getImage("src/sprite/persona.png");

        new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                try
                {
                    while (true)
                    {
                        Thread.sleep(1500);

                        System.out.println("Este hilo se encarga de mostrar en pantalla este mensaje cada 1500 milisegundos");
                    }

                } catch (InterruptedException e)
                {

                }

            }

        }).start();

        thread = new Thread(this);

        thread.start();
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        //g.setColor(Color.black);
        //g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(bi, 0, 0, this);

        g = bi.createGraphics();

        g.fillRect(0, 0, getWidth(), getHeight());

        mx = (incremento % 6) * ancho;

        //my = (incremento / 5) * altura;
        g.drawImage(ima, getWidth() / 2 - ancho, getHeight() / 2 - altura, getWidth() / 2 + ancho, getHeight() / 2 + altura, mx, my, mx + ancho, my + altura, this);

        //g.copyArea(getWidth()/2, getHeight()/2, ancho, altura, -200, -200);
        repaint();
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Thread.sleep(200);

            } catch (InterruptedException e)
            {

            }

            if (incremento >= 6)
            {
                Thread.currentThread().interrupt();

                incremento = 0;
            }

            incremento++;
        }
    }
}
