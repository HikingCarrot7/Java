package matrizmov;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Lamina extends JPanel implements MouseListener
{

    private static final long serialVersionUID = 1L;

    private int mov = 0;
    private Mapa tablero;
    private Timer timer;

    public Lamina()
    {
        tablero = new Mapa();

        timer = new Timer(60, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                repaint();
            }

        });

        timer.start();
    }

    @Override
    public void paint(Graphics g)
    {
        g.fillRect(0, 0, 1000, 1000);
        tablero.draw((Graphics2D) g, mov += 0);

        g.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        int x = e.getX(), y = e.getY();

        System.out.println("-------\nX: " + x + "\nY: " + y);

        //System.out.println("Fila: " + y + "\nColumna: " + x);
        tablero.modificarMapa((int) Math.floor((x / 20)), (int) Math.floor(((y -= (25 + mov)) / 20)));
    }

    @Override
    public void mouseEntered(MouseEvent arg0)
    {
    }

    @Override
    public void mouseExited(MouseEvent arg0)
    {
    }

    @Override
    public void mousePressed(MouseEvent arg0)
    {
    }

    @Override
    public void mouseReleased(MouseEvent arg0)
    {
    }
}
