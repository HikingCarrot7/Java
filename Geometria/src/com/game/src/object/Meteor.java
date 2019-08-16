package com.game.src.object;

import com.game.src.gamestate.GameState;
import com.game.src.math.Vector2D;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author HikingCarrot7
 */
public class Meteor extends MovingObject
{

    public Meteor(Vector2D posicion, Vector2D velocity, double maxVel, GameState gameState, BufferedImage texture)
    {
        super(posicion, velocity, maxVel, gameState, texture);
    }

    @Override
    public void tick()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics2D g)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Destroy()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
