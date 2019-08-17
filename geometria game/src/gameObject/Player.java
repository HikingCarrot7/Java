package gameObject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import graphics.Assets;
import input.KeyBoard;
import math.Vector2D;
import states.GameState;

public class Player extends MovingObject
{

    private Vector2D heading;

    private Vector2D acceleration;

    private boolean accelerating = false;
    private Cronometro fireRate;

    private boolean spawning, visible;

    private Cronometro spawnTime, flickerTime;

    public Player(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState)
    {
        super(position, velocity, maxVel, texture, gameState);//constructor de objeto movible
        
        heading = new Vector2D(0, 1);
        acceleration = new Vector2D();
        
        fireRate = new Cronometro();
        spawnTime = new Cronometro();
        flickerTime = new Cronometro();

    }

    @Override
    public void update()
    {

        if (!spawnTime.isRunning())
        {//verificamos la posicion del cronometro del jugador, de esta manera sabemos si esta en estado visible o reapareciendo
            spawning = false;
            visible = true;
        }

        if (spawning)
        {

            if (!flickerTime.isRunning())
            {//efecto de parpadeo

                flickerTime.run(Constants.FLICKER_TIME);
                visible = !visible;
            }
        }

        if (KeyBoard.SHOOT && !fireRate.isRunning() && !spawning)
        {
            gameState.getMovingObjects().add(0, new laser(
                    getCenter().add(heading.scale(width)),
                    heading,
                    Constants.LASER_VEl,
                    angle,
                    Assets.redLaser,
                    gameState));

            fireRate.run(Constants.FIRERATE);
            collidesWith();

        }

        if (KeyBoard.RIGHT)
        {
            angle += Math.PI / 30;
        }
        if (KeyBoard.LEFT)
        {
            angle -= Math.PI / 30;
        }

        if (KeyBoard.UP)
        {
            acceleration = heading.scale(Constants.ACC);
            accelerating = true;
            
        } else
        {
            if (velocity.getMagnitude() != 0)
            {
                acceleration = (velocity.scale(-1).normalize()).scale(Constants.ACC);
            }
            
            accelerating = false;
        }

        velocity = velocity.add(acceleration);

        velocity = velocity.limit(Constants.PLAYER_MAX_VEL);

        heading = heading.setDirection(angle - Math.PI / 2);

        //velocidad
        position = position.add(velocity);

        //delimitar el area y no se pierda el jugador de la pantalla
        if (position.getX() > Constants.WIDTH)
        {
            position.setX(0);
        }
        if (position.getY() > Constants.HEIGHT)
        {
            position.setY(0);
        }

        if (position.getX() < 0)
        {
            position.setX(Constants.WIDTH);
        }
        if (position.getY() < 0)
        {
            position.setY(Constants.HEIGHT);
        }

        fireRate.update();
        spawnTime.update();
        flickerTime.update();
        collidesWith();

        //---------------------------------------------
    }

    @Override
    public void Destroy()
    {//cuando el jugador es destruido
        spawning = true;
        spawnTime.run(Constants.SPAWNIG_TIME);//inicia el cronometro para dar el efecto de parpadeo
        resetValues();
        gameState.subtractlife();

    }

    private void resetValues()
    {

        angle = 0;
        velocity = new Vector2D();
        position = new Vector2D(Constants.WIDTH / 2 - Assets.player.getWidth() / 2,
                Constants.HEIGHT / 2 - Assets.player.getHeight() / 2);
    }

    @Override
    public void draw(Graphics g)
    {//efecto de propulsion

        if (!visible)//si no esta en estado visible, no se ejecuta este codigo, pues el jugador esta apareciendo
        {
            return;
        }

        Graphics2D g2d = (Graphics2D) g;

        AffineTransform at1 = AffineTransform.getTranslateInstance(position.getX() + width / 2 + 5,
                position.getY() + height / 2 + 12);
        AffineTransform at2 = AffineTransform.getTranslateInstance(position.getX() + 7, position.getY() + height / 2 + 12);

        at1.rotate(angle, -5, -12);
        at2.rotate(angle, width / 2 - 7, -12);

        if (accelerating)
        {
            g2d.drawImage(Assets.speeds, at1, null);
            g2d.drawImage(Assets.speeds, at2, null);
        }

        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());

        at.rotate(angle, width / 2, height / 2);

        g2d.drawImage(texture, at, null);//Assets debe de ser texture

    }

    public boolean isSpawning()
    {
        return spawning;
    }

}
