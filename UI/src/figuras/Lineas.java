package figuras;

import java.awt.Color;
import java.awt.Graphics;

public class Lineas extends Posiciones
{

    private int x2;
    private int y2;

    public Lineas(int x, int y, int x2, int y2, Color color)
    {

        super(x, y, color);

        this.x2 = x2;
        this.y2 = y2;

    }

    public void dibujar(Graphics g)
    {
        g.setColor(getColor());
        g.drawLine(getXPos(), getYPos(), x2, y2);
        g.drawString(getContFig(), getXPos(), getYPos());
    }

}
