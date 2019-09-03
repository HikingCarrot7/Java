package com.game.src.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Mohammed
 */
public interface InputListener
{

    public void mousePressed(MouseEvent e);

    public void mouseMoved(MouseEvent e);

    public void keyPressed(KeyEvent e);
}
