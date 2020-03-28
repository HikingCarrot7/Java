package sprite;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import static java.awt.Toolkit.getDefaultToolkit;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
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
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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

    private int ancho = 112, altura = 156, incremento = 0, mx = 0, my = 0;
    private Thread thread;
    private Image ima;
    private BufferedImage bi;

    public Lamina()
    {

        bi = new BufferedImage(500, 500, TYPE_INT_RGB);

        ima = getDefaultToolkit().getImage("src/sprite/persona.png");

        new Thread(() ->
        {
            try
            {
                while (true)
                {
                    sleep(1500);
                    out.println("Este hilo se encarga de mostrar en pantalla este mensaje cada 1500 milisegundos");
                }
            }catch (InterruptedException e)
            {
                
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
                sleep(200);

            } catch (InterruptedException e)
            {

            }

            if (incremento >= 6)
            {
                currentThread().interrupt();

                incremento = 0;
            }

            incremento++;
        }

    }

}
