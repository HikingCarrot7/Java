package figuras;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangulos extends Posiciones
{

    private int ancho;
    private int altura;

    public Rectangulos(int x, int y, int ancho, int altura, Color color)
    {

        super(x, y, color);

        this.ancho = ancho;
        this.altura = altura;

    }

    public void dibujar(Graphics g)
    {

        g.setColor(getColor());
        g.drawRect(getXPos(), getYPos(), ancho, altura);
        g.drawString(getContFig(), getXPos() + 10, getYPos() + 10);

    }

}
