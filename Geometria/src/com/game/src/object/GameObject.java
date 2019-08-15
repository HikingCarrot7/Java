package com.game.src.object;

import com.game.src.math.Vector2D;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author HikingCarrot7
 */
public abstract class GameObject
{
    protected BufferedImage texture;
    protected Vector2D posicion;
    
    public GameObject(Vector2D posicion, BufferedImage texture)
    {
        this.posicion = posicion;
        this.texture = texture;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics2D g);
    
    public Vector2D getPosicion()
    {
        return posicion;
    }
    
    public void setPosition(Vector2D posicion)
    {
        this.posicion = posicion;
    }
    
}
