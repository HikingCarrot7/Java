package com.game.src.math;

/**
 *
 * @author HikingCarrot7
 */
public class Vector2D
{

    private double x, y;

    public Vector2D(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2D()
    {
        this(0, 0);
    }

    public Vector2D add(Vector2D v)
    {
        return new Vector2D(x + v.getX(), y + v.getY());
    }

    public Vector2D subtract(Vector2D v)
    {
        return new Vector2D(x - v.getX(), y - v.getY());
    }

    public Vector2D scale(float value)
    {
        return new Vector2D(x * value, y * value);
    }

    public Vector2D limit(float value)
    {
        if (getMagnitude() > value)
        {
            return normalize().scale(value);
        }

        return this;

    }

    public Vector2D setDirection(double angle)
    {
        return new Vector2D(Math.cos(angle) * getMagnitude(), Math.sin(angle) * getMagnitude());
    }

    public double getAngle()
    {
        return Math.asin(y / getMagnitude());
    }

    public Vector2D normalize()
    {
        return new Vector2D(x / getMagnitude(), y / getMagnitude());
    }

    public double getMagnitude() //La hipotenusa con respecto al origen
    {
        return Math.sqrt(x * x + y * y);
    }

    public double getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

}
