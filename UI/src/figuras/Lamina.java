package figuras;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.security.SecureRandom;

public class Lamina extends JPanel
{

    private static final long serialVersionUID = 1L;

    SecureRandom rand = new SecureRandom();
    Cuadrados[] cuadros;
    Circulos[] circulos;
    Rectangulos[] rectangulos;
    Lineas[] lineas;

    public Lamina()
    {

        cuadros = new Cuadrados[50];
        circulos = new Circulos[50];
        rectangulos = new Rectangulos[50];
        lineas = new Lineas[50];

        for (int i = 0; i < cuadros.length; i++)
        {
            int x = 10 + rand.nextInt(300);
            int y = 10 + rand.nextInt(300);
            int lado = 10 + rand.nextInt(100);
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));

            cuadros[i] = new Cuadrados(x, y, lado, color);

        }

        for (int i = 0; i < circulos.length; i++)
        {
            int x = 405 + rand.nextInt(300);
            int y = 5 + rand.nextInt(300);
            int radio = 10 + rand.nextInt(100);
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));

            circulos[i] = new Circulos(x, y, radio, color);

        }

        for (int i = 0; i < rectangulos.length; i++)
        {
            int x = 5 + rand.nextInt(300);
            int y = 405 + rand.nextInt(300);
            int ancho = 10 + rand.nextInt(100);
            int altura = 10 + rand.nextInt(100);
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));

            rectangulos[i] = new Rectangulos(x, y, ancho, altura, color);

        }

        for (int i = 0; i < lineas.length; i++)
        {
            int x = 405 + rand.nextInt(300);
            int y = 405 + rand.nextInt(300);
            int x2 = 405 + rand.nextInt(350);
            int y2 = 405 + rand.nextInt(350);
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));

            lineas[i] = new Lineas(x, y, x2, y2, color);

        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(new Color(255, 195, 160));
        g.fillRect(3, 3, 395, 395);
        g.drawRect(3, 3, 395, 395);

        g.setColor(new Color(175, 255, 175));
        g.fillRect(402, 3, 388, 395);
        g.drawRect(402, 3, 388, 395);

        g.setColor(new Color(255, 255, 175));
        g.fillRect(3, 402, 395, 397);
        g.drawRect(3, 402, 395, 397);

        g.setColor(new Color(175, 240, 255));
        g.fillRect(401, 401, 399, 396);
        g.drawRect(401, 401, 399, 396);

        for (Cuadrados misFiguras : cuadros)
            misFiguras.dibujar(g);

        for (Circulos misFiguras : circulos)
            misFiguras.dibujar(g);

        for (Rectangulos misFiguras : rectangulos)
            misFiguras.dibujar(g);

        for (Lineas misFiguras : lineas)
            misFiguras.dibujar(g);
    }
}
