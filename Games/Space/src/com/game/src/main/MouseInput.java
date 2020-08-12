package com.game.src.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter
{

    private Menu menu;

    public MouseInput(Menu menu)
    {
        this.menu = menu;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        menu.mousePressed(e);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

}
