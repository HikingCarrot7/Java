package com.game.src.object;

import com.game.src.gamestate.GameState;
import com.game.src.math.Vector2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author HikingCarrot7
 */
public abstract class MovingObject extends GameObject
{

    protected GameState gameState;
    protected Vector2D velocity;
    protected AffineTransform at;
    protected int width, height;
    protected double angle, maxVel;

    public MovingObject(Vector2D posicion, Vector2D velocity, double maxVel, GameState gameState, BufferedImage texture)
    {
        super(posicion, texture);

        this.gameState = gameState;
        this.velocity = velocity;
        this.maxVel = maxVel;

        width = texture.getWidth();
        height = texture.getHeight();

        angle = 0;

    }

    protected void CollidesWith()
    {
        ArrayList<MovingObject> objects = gameState.getMovingObjects();

        for (MovingObject currentObject : objects)
        {

            MovingObject tempObject = currentObject;

            if (tempObject.equals(this))
            {
                continue;
            }

            double distance = tempObject.getCenter().subtract(getCenter()).getMagnitude();

            if (distance < tempObject.width / 2 + width / 2 && objects.contains(this))
            {
                if (ObjectCollision(tempObject, this))
                {
                    tempObject.Destroy();

                    this.Destroy();

                }

            }
        }

    }

    public abstract void Destroy();

    private boolean ObjectCollision(MovingObject a, MovingObject b)
    {
        if (a instanceof Player && ((Player) a).isPlayerSpawning())
        {
            return false;

        }

        if (b instanceof Player && ((Player) b).isPlayerSpawning())
        {
            return false;
        }

        return !(a instanceof Meteor && b instanceof Meteor);

    }

    public Vector2D getCenter()
    {
        return new Vector2D((posicion.getX() + width) / 2, (posicion.getY() + height) / 2);
    }

}
