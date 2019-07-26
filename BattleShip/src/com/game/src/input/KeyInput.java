package com.game.src.input;

import com.game.src.UI.Menu;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author HikingCarrot7
 */
public class KeyInput extends KeyAdapter
{
    
    private final Menu menu;
    
    public KeyInput(Menu menu)
    {
        this.menu = menu;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        menu.keyPressed(e);
    }
    
}
