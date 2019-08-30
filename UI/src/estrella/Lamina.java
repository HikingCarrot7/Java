package estrella;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.security.SecureRandom;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Lamina extends JPanel
{

    private static final long serialVersionUID = 1L;

    private GeneralPath estrella, rectangulo;
    private SecureRandom rand;
    private int cambio = 0;
    private Timer timer;

    public Lamina()
    {
        estrella = new GeneralPath();

        rectangulo = new GeneralPath();

        timer = new Timer(60, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                repaint();
            }

        });

        rand = new SecureRandom();

        timer.start();

    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        g.setColor(Color.blue);

        g.fillRect(0, 0, getWidth(), getHeight());

        Graphics2D g2 = (Graphics2D) g;

        int puntosX[] =
        {
            55, 67, 109, 73, 83, 55, 27, 37, 1, 43
        };
        int puntosY[] =
        {
            0, 36, 36, 54, 96, 72, 96, 54, 36, 36
        };

        estrella.moveTo(puntosX[0], puntosY[0]);

        for (int i = 0; i < puntosX.length; i++)
            estrella.lineTo(puntosX[i], puntosY[i]);

        estrella.closePath();

        rectangulo.moveTo(50, 50);

        rectangulo.lineTo(80, 50);
        rectangulo.lineTo(80, 80);
        rectangulo.lineTo(50, 80);

        rectangulo.closePath();

        g2.fill(rectangulo);

        g2.translate(200, 200);

        g2.rotate(Math.toRadians(cambio += 5));

        for (int i = 0; i <= 20; i++)
        {
            g2.rotate(Math.toRadians(18));

            g2.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));

            g2.fill(estrella);

        }

        g.dispose();

        g2.dispose();

    }

}
