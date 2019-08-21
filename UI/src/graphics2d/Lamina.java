package graphics2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Lamina extends JPanel
{

    private static final long serialVersionUID = 1L;

    public void paintComponent(Graphics g)
    {

        super.paintComponent(g);

        //Refundir
        Graphics2D g2 = (Graphics2D) g;

        g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

        //Dos objetos (uno de tipo rectangle2d y otro de tipo ellipse2d)
        Rectangle2D rect2d = new Rectangle2D.Double(100, 100, 200, 150);
        Ellipse2D elipse = new Ellipse2D.Double(100, 100, 200, 150);

        //Dibujamos el rectangulo2d
        g2.draw(rect2d);

        //Establecemos la elipse dentro del rectangulo
        elipse.setFrame(rect2d);

        //Dibujamos la elipse dentro del rectangulo
        g2.draw(elipse);

        //Dibujamos una linea
        g2.draw(new Line2D.Double(100, 100, 300, 250));

        //Una nueva elipse2D
        Ellipse2D ellipse2 = new Ellipse2D.Double();

        //dibujamos una elipse(centro en x,centro en y, radioX, radioY)
        ellipse2.setFrameFromCenter(rect2d.getCenterX(), rect2d.getCenterY(), rect2d.getCenterX() + 125, rect2d.getCenterY() + 125);

        //Dibujamos la elipse anterior
        g2.draw(ellipse2);

        //File miImagen = new File("src/graphics2d/1.jpg");
        try
        {
            imagen = ImageIO.read(new File("src/graphics2d/1.jpg"));

        } catch (IOException e)
        {
            System.out.println("La imagen no se encuentra.");
        }

        g.drawImage(imagen, 0, 0, null);

        for (int i = 0; i < 18; i++)
        {
            for (int j = 0; j < 32; j++)
            {
                g.copyArea(0, 0, imagen.getWidth(this), imagen.getHeight(this), imagen.getWidth(this) * j, imagen.getHeight(this) * i);
            }

        }

        int[] xPoints =
        {
            400, 500, 550, 450, 350
        };
        int[] yPoints =
        {
            400, 400, 350, 200, 350
        };
        char[] data =
        {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'
        };

        /*int[] xPoints = {400,500,350,550,450};
         int[] yPoints = {400,400,350,350,300};*/
        Polygon pol = new Polygon(xPoints, yPoints, 5);

        g.drawPolygon(pol);

        for (int i = 0; i < 8; i++)
        {

            g.drawArc(600 - (i * 20), 300 - (i * 20), 300 + (i * 40), 500 + (i * 40), 45, 90);
        }

        g.drawChars(data, 0, 10, 50, 100);

        g.drawRoundRect(600, 400, 100, 100, 45, 45);

        g2.drawString("Hola", 60, 60);

        g2.rotate(Math.toRadians(45), 500, 300);

        for (int i = 0; i < 6; i++)
        {
            g2.drawRect(500 - (i * 30), 300 + (i * 30), 30, 30);
        }

        g2.rotate(Math.toRadians(-45), 500, 300);

        for (int i = 0; i < 6; i++)
        {

            g2.setColor(getBackground());
            //g2.setColor(new Color((int)Math.random()*255,(int)Math.random()*255,(int)Math.random()*255));

            g2.fillOval(100 - (i * 20), 400 - (i * 20), 30 + (i * 40), 30 + (i * 40));

        }

        for (int i = 0; i < 6; i++)
        {

            g.setColor(Color.BLACK);
            g2.drawOval(100 - (i * 20), 400 - (i * 20), 30 + (i * 40), 30 + (i * 40));

        }
    }

    private Image imagen;

}
