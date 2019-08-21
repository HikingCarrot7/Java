package com.game.src.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.game.src.framework.GameObject;
import com.game.src.framework.ObjectId;
import com.game.src.graphics.Animation;
import com.game.src.main.Camera;
import com.game.src.main.Game;

public class Player extends GameObject
{

    private int ancho = 32, alto = 64;

    private float gravity = 0.5f;

    private final float MAX_SPEED = 10;

    private Handler handler;

    private Camera camera;

    private Animation playerWalkRight, playerWalkLeft, playerJumpingRight, playerJumpingLeft;

    private BufferedImage[] right, left, jright, jleft;

    public Player(float x, float y, ObjectId id, Handler handler, Camera camera)
    {
        super(x, y, id);

        dividirArrays();

        this.handler = handler;

        this.camera = camera;

        playerWalkRight = new Animation(8, right);

        playerWalkLeft = new Animation(8, left);

        playerJumpingRight = new Animation(8, jright);

        playerJumpingLeft = new Animation(8, jleft);

    }

    @Override
    public void tick(ArrayList<GameObject> object)
    {
        y += velY;

        x += velX;

        if (velX < 0)
        {
            facing = -1;
        } else if (velX > 0)
        {
            facing = 1;
        }

        if (falling || jumping)
        {
            velY += gravity;

            if (velY > MAX_SPEED)
            {
                velY = MAX_SPEED;
            }
        }

        Collision(object);

        playerWalkRight.runAnimation();

        playerWalkLeft.runAnimation();

        playerJumpingRight.runAnimation();

        playerJumpingLeft.runAnimation();

    }

    private void Collision(ArrayList<GameObject> object)
    {
        for (int i = 0; i < object.size(); i++)
        {
            if (object.get(i).getId().equals(ObjectId.Block))
            {
                //top
                if (getBoundsTop().intersects(object.get(i).getBounds()))
                {
                    y = object.get(i).getY() + 32;
                    velY = 0;
                }

                //beneath
                if (getBounds().intersects(object.get(i).getBounds()))
                {
                    y = object.get(i).getY() - alto;
                    velY = 0;
                    falling = false;
                    jumping = false;

                } else
                {
                    falling = true;
                }

                //right
                if (getBoundsRight().intersects(object.get(i).getBounds()))
                {
                    x = object.get(i).getX() - ancho;
                }

                //left
                if (getBoundsLeft().intersects(object.get(i).getBounds()))
                {
                    x = object.get(i).getX() + 32;
                }

            } else if (object.get(i).getId().equals(ObjectId.Flag))
            {
                if (getBounds().intersects(object.get(i).getBounds()))
                {
                    handler.removeLevel();

                    handler.switchLevel();
                }
            }
        }
    }

    public void dividirArrays()
    {

        right = new BufferedImage[Game.getTexture().player.length / 2];

        left = new BufferedImage[right.length];

        jright = new BufferedImage[Game.getTexture().playerJump.length / 2];

        jleft = new BufferedImage[jright.length];

        for (int i = 0; i < right.length * 2; i++)
        {
            if (i < 7)
            {
                right[i] = Game.getTexture().player[i];
            } else
            {
                left[i - 7] = Game.getTexture().player[i];
            }
        }

        for (int i = 0; i < jright.length * 2; i++)
        {
            if (i < 3)
            {
                jright[i] = Game.getTexture().playerJump[i];
            } else
            {
                jleft[i - 3] = Game.getTexture().playerJump[i];
            }
        }
    }

    @Override
    public void render(Graphics2D g)
    {

        if (jumping && facing == 1)
        {
            playerJumpingRight.drawAnimation(g, (int) x, (int) y);
        } else if (jumping && facing == -1)
        {
            playerJumpingLeft.drawAnimation(g, (int) x, (int) y);
        } else if (velX != 0)
        {
            if (velX > 0)
            {
                playerWalkRight.drawAnimation(g, (int) x, (int) y);
            } else
            {
                playerWalkLeft.drawAnimation(g, (int) x, (int) y);
            }
        } else if (facing == 1)
        {
            g.drawImage(Game.getTexture().player[0], (int) x, (int) y, null);
        } else
        {
            g.drawImage(Game.getTexture().player[7], (int) x, (int) y, null);
        }

        /*g.setColor(Color.blue);
         g.drawRect((int) x, (int) y, ancho, alto);
		
         g.setColor(Color.green);
         g.draw(getBounds());
		
         g.setColor(Color.red);
         g.draw(getBoundsLeft());
		
         g.setColor(Color.white);
         g.draw(getBoundsRight());
		
         g.setColor(Color.gray);
         g.draw(getBoundsTop());*/
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) x + 8, (int) y + alto - 1, ancho - 16, 1);
    }

    public Rectangle getBoundsLeft()
    {
        return new Rectangle((int) x, (int) y + 5, 1, alto - 10);
    }

    public Rectangle getBoundsRight()
    {
        return new Rectangle((int) x + ancho - 1, (int) y + 5, 1, alto - 10);
    }

    public Rectangle getBoundsTop()
    {
        return new Rectangle((int) x + 8, (int) y, ancho - 16, 1);
    }

}
