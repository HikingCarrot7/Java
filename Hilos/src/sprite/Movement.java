package sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        ima = Toolkit.getDefaultToolkit().getImage("src/sprite/ninoReposo.png");

        bi = new BufferedImage(1000, 200, BufferedImage.TYPE_INT_RGB);

        hilo = new Thread(this);

        hilo.start();

    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        g.drawImage(bi, 0, 0, this);

        g = bi.createGraphics();

        g.setColor(Color.white);
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
                Thread.sleep(150);

            } catch (InterruptedException e)
            {

            }

            incremento++;
        }

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() != KeyEvent.VK_R)
        {
            teclas.add(e.getKeyCode());
        } else
        {
            r = true;
        }

        if (teclas.contains(KeyEvent.VK_RIGHT) && r)
        {
            ima = Toolkit.getDefaultToolkit().getImage("src/sprite/ninocorriendoright.png");

            xPos += 8;

        } else if (teclas.contains(KeyEvent.VK_LEFT) && r)
        {

            ima = Toolkit.getDefaultToolkit().getImage("src/sprite/ninocorriendoleft.png");

            xPos -= 8;

        } else if (teclas.contains(KeyEvent.VK_LEFT))
        {

            ima = Toolkit.getDefaultToolkit().getImage("src/sprite/ninoleft.png");

            xPos -= 5;

        } else if (teclas.contains(KeyEvent.VK_RIGHT))
        {

            ima = Toolkit.getDefaultToolkit().getImage("src/sprite/ninoright.png");

            xPos += 5;

        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        ima = Toolkit.getDefaultToolkit().getImage("src/sprite/ninoReposo.png");

        if (e.getKeyCode() == KeyEvent.VK_R)
        {
            r = false;
        }

        teclas.clear();

    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }
}
