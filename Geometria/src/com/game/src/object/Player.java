package com.game.src.object;

import com.game.src.control.Cronometro;
import com.game.src.gamestate.GameState;
import com.game.src.math.Vector2D;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author HikingCarrot7
 */
public class Player extends MovingObject
{

    private final Vector2D heading, acceleration;
    private final Cronometro fireRate, spawnTime, flickerTime;
    private boolean playerSpawning, accelerating, visible;

    public Player(Vector2D posicion, Vector2D velocity, double maxVel, GameState gameState, BufferedImage texture)
    {
        super(posicion, velocity, maxVel, gameState, texture);

        heading = new Vector2D();
        acceleration = new Vector2D();

        fireRate = new Cronometro();
        spawnTime = new Cronometro();
        flickerTime = new Cronometro();

    }

    public boolean isPlayerSpawning()
    {
        return playerSpawning;
    }

    public void setPlayerSpawning(boolean playerSpawning)
    {
        this.playerSpawning = playerSpawning;
    }

    @Override
    public void tick()
    {
        if(!spawnTime.isRunning())
        {
            playerSpawning = false;
            
            visible = true;
            
        }
    }

    @Override
    public void render(Graphics2D g)
    {

    }

    @Override
    public void Destroy()
    {
        
    }

}
