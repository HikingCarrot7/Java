package com.game.src.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.game.src.main.Camera;
import com.game.src.objects.Bullet;
import com.game.src.objects.Handler;

public class KeyInput extends KeyAdapter
{

    private Handler handler;

    private Camera camera;

    public KeyInput(Handler handler, Camera camera)
    {
        this.handler = handler;

        this.camera = camera;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.getObjects().size(); i++)
            if (handler.getObjects().get(i).getId().equals(ObjectId.Player))
            {
                if (key == KeyEvent.VK_RIGHT)
                    handler.getObjects().get(i).setVelX(5);
                if (key == KeyEvent.VK_LEFT)
                    handler.getObjects().get(i).setVelX(-5);

                if (key == KeyEvent.VK_UP && !handler.getObjects().get(i).isJumping())
                {
                    handler.getObjects().get(i).setJumping(true);
                    handler.getObjects().get(i).setVelY(-15);
                }

                /*if(key == KeyEvent.VK_SPACE) 
                 handler.addObject(new Bullet(handler.getObjects().get(i).getX(), handler.getObjects().get(i).getY() + 32, ObjectId.Bullet, handler.getObjects().get(i).getFacing() * 8));	*/
                break;
            }

        if (key == KeyEvent.VK_S)
            camera.setY(-300);

        if (key == KeyEvent.VK_ESCAPE)
            System.exit(1);

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.getObjects().size(); i++)
            if (handler.getObjects().get(i).getId().equals(ObjectId.Player))
            {
                if (key == KeyEvent.VK_RIGHT)
                    handler.getObjects().get(i).setVelX(0);
                if (key == KeyEvent.VK_LEFT)
                    handler.getObjects().get(i).setVelX(0);

            }
    }

}
