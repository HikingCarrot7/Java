package com.game.src.object;

import com.game.src.control.Cronometro;
import com.game.src.gamestate.GameState;
import com.game.src.math.Vector2D;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Graphics is the abstract base class for all graphics contexts
 * which allow an application to draw onto components realized on
 * various devices or onto off-screen images.
 * A Graphics object encapsulates the state information needed
 * for the various rendering operations that Java supports.  This
 * state information includes:
 * <ul>
 * <li>The Component to draw on
 * <li>A translation origin for rendering and clipping coordinates
 * <li>The current clip
 * <li>The current color
 * <li>The current font
 * <li>The current logical pixel operation function (XOR or Paint)
 * <li>The current XOR alternation color
 *     (see <a href="#setXORMode">setXORMode</a>)
 * </ul>
 * 
 * @author      Sami Shaio
 * @author      Arthur van Hoff
 * @version     %I%, %G%
 * @since       1.0
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

    /**
     * Retorna si el jugador está reapareciendo. 
     * 
     * @return if this <ul><li>Player</ul> está reapareciendo.
     * 
     */

    public boolean isPlayerSpawning()
    {
        return playerSpawning;
    }
    
    /**
     * @deprecated 
     * Establece si el jugador está reapareciendo.
     * 
     * @param playerSpawning if this <code>Player</code> está reapareciendo.
     * 
     * 
     * @see MovingObject#CollidesWith() 
     * 
     * @since 1.0
     * 
     */

    public void setPlayerSpawning(boolean playerSpawning)
    {
        this.playerSpawning = playerSpawning;
    }

    @Override
    public void tick()
    {
        if (!spawnTime.isRunning())
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
