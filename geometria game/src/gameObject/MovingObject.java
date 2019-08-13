package gameObject;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import math.Vector2D;
import states.GameState;

public abstract class MovingObject extends GameObject
{

    protected Vector2D velocity;
    protected AffineTransform at;//rotacion
    protected double angle;
    protected double maxVel;
    protected int width;
    protected int height;
    protected GameState gameState;

    public MovingObject(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState)
    {
        super(position, texture);
        this.velocity = velocity;
        this.maxVel = maxVel;
        this.gameState = gameState;
        width = texture.getWidth();
        height = texture.getHeight();
        angle = 0;

    }

    protected void collidesWith()
    {

        ArrayList<MovingObject> movingObjects = gameState.getMovingObjects();

        for (MovingObject m : movingObjects)
        {
            if (m.equals(this))
            {
                continue;
            }

            double distance = m.getCenter().subtract(getCenter()).getMagnitude();//se calcula la distancia del objeto

            if (distance < m.width / 2 + width / 2 && movingObjects.contains(this))
            {//comprobamos que se de la colision
                objectCollision(m, this);

            }
        }
    }

    private void objectCollision(MovingObject a, MovingObject b)
    {//funcion encargada de la deteccion de los objetos

        if (a instanceof Player && ((Player) a).isSpawning())
        {//se detecta al jugador, si esta apareciendo, no detectara las coliciones
            return;
        }

        if (b instanceof Player && ((Player) b).isSpawning())
        {//se detecta al jugador, si esta apareciendo, no detectara las coliciones
            return;
        }

        if (!(a instanceof Meteor && b instanceof Meteor))
        {
            gameState.playExplosion(getCenter());
            a.Destroy();
            b.Destroy();
        }
    }

    protected void Destroy()
    {
        gameState.getMovingObjects().remove(this);//elimina el objeto
    }

    protected Vector2D getCenter()//obtener el centro de la nave
    {
        return new Vector2D(position.getX() + width / 2, position.getY() + height / 2);
    }

}
