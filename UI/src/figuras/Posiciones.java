package figuras;

import java.awt.Color;

public class Posiciones
{

    private int x;
    private int y;
    private Color color;
    private static int contFig = 0;

    public Posiciones(int x, int y, Color color)
    {

        this.x = x;
        this.y = y;
        this.color = color;

    }

    public int getXPos()
    {
        return x;
    }

    public int getYPos()
    {
        return y;
    }

    public Color getColor()
    {
        return color;

    }

    public static String getContFig()
    {

        return "" + contFig++;

    }

}
