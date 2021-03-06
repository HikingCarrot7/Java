package sprite;

import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import static java.awt.Toolkit.getDefaultToolkit;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_R;
import static java.awt.event.KeyEvent.VK_RIGHT;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Movement extends JFrame
{

    private static final long serialVersionUID = 1L;

    private LaminaMovement lamina;

    public Movement()
    {
        lamina = new LaminaMovement();

        setBounds(0, 0, 1000, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Animation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(lamina);
        add(lamina);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new Movement();
    }
}

class LaminaMovement extends JPanel implements Runnable, KeyListener
{

    private static final long serialVersionUID = 1L;

    private Image ima;

    private BufferedImage bi;

    private Thread hilo;

    private int ancho = 49, largo = 76, mx = 0, my = 0, incremento = 0, xPos, yPos;

    private ArrayList<Integer> teclas;

    private boolean r = false;

    public LaminaMovement()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        teclas = new ArrayList<>();

        xPos = 500;

        yPos = 70;

        ima = getDefaultToolkit().getImage("src/sprite/ninoReposo.png");

        bi = new BufferedImage(1000, 200, TYPE_INT_RGB);

        hilo = new Thread(this);

        hilo.start();

    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        g.drawImage(bi, 0, 0, this);

        g = bi.createGraphics();

        g.setColor(white);
        g.fillRect(0, 0, 1000, 200);

        mx = (incremento % 6) * ancho;

        g.drawImage(ima, xPos - ancho / 2, yPos - largo / 2, xPos + ancho / 2, yPos + largo / 2, mx, my, mx + ancho, my + largo, this);

        repaint();
    }

    @Override
    public void run()
    {

        while (true)
        {
            try
            {
                sleep(150);

            } catch (InterruptedException e)
            {

            }

            incremento++;
        }

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() != VK_R)
            teclas.add(e.getKeyCode());
        else
            r = true;

        if (teclas.contains(VK_RIGHT) && r)
        {
            ima = getDefaultToolkit().getImage("src/sprite/ninocorriendoright.png");

            xPos += 8;

        } else if (teclas.contains(VK_LEFT) && r)
        {

            ima = getDefaultToolkit().getImage("src/sprite/ninocorriendoleft.png");

            xPos -= 8;

        } else if (teclas.contains(VK_LEFT))
        {

            ima = getDefaultToolkit().getImage("src/sprite/ninoleft.png");

            xPos -= 5;

        } else if (teclas.contains(VK_RIGHT))
        {

            ima = getDefaultToolkit().getImage("src/sprite/ninoright.png");

            xPos += 5;

        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        ima = getDefaultToolkit().getImage("src/sprite/ninoReposo.png");

        if (e.getKeyCode() == VK_R)
            r = false;

        teclas.clear();

    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }
}
