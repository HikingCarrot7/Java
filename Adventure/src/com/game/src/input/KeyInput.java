package com.game.src.input;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.game.src.main.Game;

public class KeyInput implements KeyListener, FocusListener
{

    private final boolean[] keys;
    private final Game game;

    public KeyInput(Game game)
    {
        keys = new boolean[120];

        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == 45)
        {
            game.ZoomMin();
        } else if (key == 521)
        {
            game.ZoomMax();
        }

        if (key < keys.length)
        {
            keys[e.getKeyCode()] = true;
        }

        if (keys[KeyEvent.VK_CONTROL])
        {
            game.handleCTRL(keys);
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key < keys.length)
        {
            keys[e.getKeyCode()] = false;
        }

    }

    @Override
    public void focusLost(FocusEvent e)
    {
        for (int i = 0; i < keys.length; i++)
        {
            keys[i] = false;
        }

    }

    public boolean up()
    {
        return keys[KeyEvent.VK_UP];
    }

    public boolean down()
    {
        return keys[KeyEvent.VK_DOWN];
    }

    public boolean left()
    {
        return keys[KeyEvent.VK_LEFT];
    }

    public boolean right()
    {
        return keys[KeyEvent.VK_RIGHT];
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void focusGained(FocusEvent e)
    {
    }

}
