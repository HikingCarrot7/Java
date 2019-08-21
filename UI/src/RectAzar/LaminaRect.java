package RectAzar;

import java.awt.Color;
import java.awt.Graphics;
import java.security.SecureRandom;
import javax.swing.JPanel;

public class LaminaRect extends JPanel
{

    private static final long serialVersionUID = 1L;

    private SecureRandom numerosAleatorios = new SecureRandom();

    private ObjetosRectangulo[] rectangulos;

    public LaminaRect()
    {
        setBackground(Color.WHITE);

        rectangulos = new ObjetosRectangulo[5 + numerosAleatorios.nextInt(35)];

        for (int i = 0; i < rectangulos.length; i++)
        {
            int x1 = numerosAleatorios.nextInt(500);
            int y1 = numerosAleatorios.nextInt(200);
            int ancho = numerosAleatorios.nextInt(100);
            int altura = numerosAleatorios.nextInt(200);

            Color color = new Color(numerosAleatorios.nextInt(255), numerosAleatorios.nextInt(255), numerosAleatorios.nextInt(255));

            rectangulos[i] = new ObjetosRectangulo(x1, y1, ancho, altura, color);
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        for (ObjetosRectangulo rectangulos : rectangulos)
        {
            rectangulos.dibijarRect(g);
        }

        g.drawString("Java is cool", 300, 300);
    }
}
