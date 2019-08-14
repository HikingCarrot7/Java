package math;

public class Vector2D
{
    //permite interactuar con otros vectores, angulos

    private double x, y;

    public Vector2D(double x, double y)
    {
        this.x = x;
        this.y = y;

    }

    public Vector2D()
    {
        this(0,0);
    }

    public Vector2D add(Vector2D v)//realizamos una suma de vectores
    {
        return new Vector2D(x + v.getX(), y + v.getY());
    }

    public Vector2D subtract(Vector2D v)//realizamos una resta de vectores,para obtener la distancia
    {
        return new Vector2D(x - v.getX(), y - v.getY());
    }

    public Vector2D scale(double value)//para modificar la magnitud del vector
    {
        return new Vector2D(x * value, y * value);
    }

    public Vector2D limit(double value)//limitar la velocidad maxima
    {
        if (getMagnitude() > value)
        {
            return normalize().scale(value);
        }
        
        return this;
    }

    public Vector2D normalize()
    {
        double Magnitude = getMagnitude();
        return new Vector2D(x / Magnitude, y / Magnitude);
    }

    public double getMagnitude()//devolvera la magnitud del vetor
    {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D setDirection(double angle)//devolvera las coordenadas en (x,y)
    {
        double Magnitude = getMagnitude();

        return new Vector2D(Math.cos(angle) * Magnitude, Math.sin(angle) * Magnitude);
    }

    public double getAngle()
    {
        return Math.asin(y / getMagnitude());
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

}
