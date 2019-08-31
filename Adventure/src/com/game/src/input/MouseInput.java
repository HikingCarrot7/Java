package com.game.src.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.game.src.main.Game;

public class MouseInput implements MouseListener, MouseMotionListener
{

    private final Game game;

    public MouseInput(Game game)
    {
        this.game = game;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1)
            game.LeftPressed(e.getX(), e.getY());

        if (e.getButton() == MouseEvent.BUTTON3)
            game.RightPressed(e.getX(), e.getY());

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

}
