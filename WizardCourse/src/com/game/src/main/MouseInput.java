package com.game.src.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.game.src.framework.GameObject;
import com.game.src.framework.ObjectId;
import com.game.src.graphics.Camera;
import com.game.src.objects.Bullet;
import com.game.src.objects.Handler;
import com.game.src.objects.Wizard;

public class MouseInput extends MouseAdapter
{

    private Handler handler;
    private Camera camera;
    private Wizard wizard;

    public MouseInput(Handler handler, Camera camera)
    {
        this.handler = handler;
        this.camera = camera;

        for (int i = 0; i < handler.getObjects().size(); i++)
        {
            GameObject tempObject = handler.getObjects().get(i);

            if (tempObject.getId().equals(ObjectId.Player))
            {
                wizard = (Wizard) tempObject;
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        float mx = e.getX() - camera.getX();
        float my = e.getY() - camera.getY();

        wizard.setAmmo(wizard.getAmmo() - 1);

        if (wizard.getAmmo() >= 0)
        {
            handler.addObject(new Bullet(wizard.getX() + 16, wizard.getY() + 32, ObjectId.Bullet, mx, my, handler));
        }

    }

}
