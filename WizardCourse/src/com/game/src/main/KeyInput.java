package com.game.src.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.game.src.framework.GameObject;
import com.game.src.framework.ObjectId;
import com.game.src.objects.Handler;
import com.game.src.objects.Wizard;

public class KeyInput extends KeyAdapter
{

    private Wizard wizard;

    public KeyInput(Handler handler)
    {
        for (GameObject O : handler.getObjects())
            if (O.getId().equals(ObjectId.Player))
            {
                wizard = (Wizard) O;

                break;
            }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        Integer key = e.getKeyCode();

        if (key.equals(KeyEvent.VK_UP))
            if (!wizard.getKeys().contains(KeyEvent.VK_UP))
                wizard.addKey(KeyEvent.VK_UP);

        if (key.equals(KeyEvent.VK_DOWN))
            if (!wizard.getKeys().contains(KeyEvent.VK_DOWN))
                wizard.addKey(KeyEvent.VK_DOWN);

        if (key.equals(KeyEvent.VK_LEFT))
            if (!wizard.getKeys().contains(KeyEvent.VK_LEFT))
                wizard.addKey(KeyEvent.VK_LEFT);

        if (key.equals(KeyEvent.VK_RIGHT))
            if (!wizard.getKeys().contains(KeyEvent.VK_RIGHT))
                wizard.addKey(KeyEvent.VK_RIGHT);

        if (key.equals(KeyEvent.VK_ESCAPE))
            System.exit(1);

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        Integer key = e.getKeyCode();

        if (key.equals(KeyEvent.VK_UP))
        {
            wizard.removeKey(KeyEvent.VK_UP);
            wizard.setVelY(0);
        }

        if (key.equals(KeyEvent.VK_DOWN))
        {
            wizard.removeKey(KeyEvent.VK_DOWN);
            wizard.setVelY(0);
        }

        if (key.equals(KeyEvent.VK_LEFT))
        {
            wizard.removeKey(KeyEvent.VK_LEFT);
            wizard.setVelX(0);
        }

        if (key.equals(KeyEvent.VK_RIGHT))
        {
            wizard.removeKey(KeyEvent.VK_RIGHT);
            wizard.setVelX(0);
        }

    }

}
