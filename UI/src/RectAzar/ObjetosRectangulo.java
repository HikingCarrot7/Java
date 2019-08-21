package RectAzar;

import java.awt.Color;
import java.awt.Graphics;

public class ObjetosRectangulo
{

    private int x1, y1, ancho, altura;
    private Color color;

    public ObjetosRectangulo(int x1, int y1, int ancho, int altura, Color color)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.ancho = ancho;
        this.altura = altura;
        this.color = color;
    }

    public void dibijarRect(Graphics g)
    {
        g.setColor(color);
        g.fillRect(x1, y1, ancho, altura);
        g.drawRect(x1, y1, ancho, altura);
    }
}
