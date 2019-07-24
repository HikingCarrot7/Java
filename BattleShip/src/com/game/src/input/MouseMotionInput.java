/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.src.input;

import com.game.src.UI.Menu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author HikingCarrot7
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
