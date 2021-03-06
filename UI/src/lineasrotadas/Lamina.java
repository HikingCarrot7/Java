package lineasrotadas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Lamina extends JPanel
{

    private static final long serialVersionUID = 1L;

    private Timer timer;

    private int[] anguloLineas;

    private String[] texto =
    {
        "PYTHON", "PHP", "JAVASCRIPT", "JAVA", "C++"
    };

    private int razon = -1, angulo = 0;

    public Lamina()
    {
        timer = new Timer(8, (ActionEvent e) ->
        {
            repaint();
        });

        timer.start();

        anguloLineas = new int[5];
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        anadirFiguras((Graphics2D) g);
    }

    public void anadirFiguras(Graphics2D g)
    {
        g.setColor(Color.white);
        g.fillRect(0, 0, 500, 500);

        g.setColor(Color.green);
        g.fillOval(50, 50, 400, 400);

        g.setColor(Color.black);

        g.rotate(Math.toRadians(angulo += razon), 250, 250);

        g.setFont(new Font("serif", Font.BOLD, 18));

        dibujarLineas(g);
    }

    public void dibujarLineas(Graphics2D g)
    {
        for (int i = 0; i < anguloLineas.length; i++)
        {
            g.rotate(Math.toRadians(360 / anguloLineas.length), 250, 250);
            g.drawLine(250, 250, 450, 250);
            g.drawString(texto[i], 300, 150);
        }
    }
}
