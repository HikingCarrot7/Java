package com.game.src.objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author HikingCarrot
 */
public abstract class GameObject 
{
    protected float x, y, velX, velY;
    protected GameId gameId;
    
    public GameObject(float x, float y, GameId gameId)
    {
        this.x = x;
        this.y = y;
        this.gameId = gameId;
    }
    
    public abstract void render(Graphics2D g);
    public abstract void tick();
    public abstract Rectangle getBounds();

    public float getX() 
    {
        return x;
    }

    public void setX(float x) 
    {
        this.x = x;
    }

    public float getY() 
    {
        return y;
    }

    public void setY(float y) 
    {
        this.y = y;
    }

    public float getVelX() 
    {
        return velX;
    }

    public void setVelX(float velX) 
    {
        this.velX = velX;
    }

    public float getVelY()
    {
        return velY;
    }

    public void setVelY(float velY) 
    {
        this.velY = velY;
    }

    public GameId getGameId()
    {
        return gameId;
    }

    public void setGameId(GameId gameId) 
    {
        this.gameId = gameId;
    }
    
}
