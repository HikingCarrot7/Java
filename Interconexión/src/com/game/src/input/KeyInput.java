package com.game.src.input;

import com.game.src.graphics.RenderHandler;
import com.game.src.main.Game;
import com.game.src.objects.Player;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author HikingCarrot
 */
public class KeyInput implements KeyListener
{
    private Player player;
    private final RenderHandler renderHandler;
    
    public KeyInput(RenderHandler renderHandler)
    {
        this.renderHandler = renderHandler;
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        int key = e.getKeyCode();
        
        player = renderHandler.getPlayer();
        
        if(key == KeyEvent.VK_LEFT)
            player.setVelX(-5); player.setMoving(true);
        
        if(key == KeyEvent.VK_RIGHT)
            player.setVelX(5); player.setMoving(true);
        
        if(key == KeyEvent.VK_UP)
            player.setVelY(-5); player.setMoving(true);
        
        if(key == KeyEvent.VK_DOWN)
            player.setVelY(5); player.setMoving(true);
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_LEFT)
            player.setVelX(0); player.setMoving(false);
        
        if(key == KeyEvent.VK_RIGHT)
            player.setVelX(0); player.setMoving(false);
        
        if(key == KeyEvent.VK_UP)
            player.setVelY(0); player.setMoving(false);
        
        if(key == KeyEvent.VK_DOWN)
            player.setVelY(0); player.setMoving(false);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
}
