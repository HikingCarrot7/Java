package com.game.src.input;

import com.game.src.UI.Menu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Mohammed
 */
public class MouseMotionInput implements MouseMotionListener
{

    private final Menu menu;

    public MouseMotionInput(Menu menu)
    {
        this.menu = menu;
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        menu.mouseMoved(e);
    }

}
