package ruletadelamor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Rotacion extends JPanel
{

    private static final long serialVersionUID = 1L;

    private Timer timer;
    protected int rotacion;
    protected int velocidad;
    protected int thicks;
    protected int tope = 20;
    private int razon = 1;

    private ImageIcon ruleta;
    private ImageIcon flecha;

    public Rotacion()
    {
        timer = new Timer(8, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                repaint();
            }

        });

        rotacion = 180;
        velocidad = 0;

        setBackground(Color.blue);

        timer.start();
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        dibujarfiguras((Graphics2D) g);

        if (thicks++ % tope == 0 && velocidad != 0)
        {
            velocidad -= razon;

        } else if (velocidad < 0)
        {
            velocidad = 0;
        }
    }

    public void dibujarfiguras(Graphics2D g)
    {
        g.setColor(Color.pink);

        g.fill(new Rectangle2D.Double(0, 0, 300, 300));

        g.setColor(Color.blue);

        ruleta = new ImageIcon("src/ruletadelamor/ruleta.png");

        ruleta.paintIcon(this, g, 0, 0);

        g.rotate(Math.toRadians(rotacion += velocidad), 150, 150);

        flecha = new ImageIcon("src/ruletadelamor/flecha.png");

        flecha.paintIcon(this, g, 145, 150);

        g.dispose();
    }
}
