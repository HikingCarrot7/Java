package com.game.src.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.game.src.audio.AudioPlayer;
import com.game.src.objects.Handler;
import com.game.src.objects.Player;

public class KeyInput extends KeyAdapter
{

    private Handler handler;
    private Player player;

    public KeyInput(Handler handler, Player player)
    {
        this.handler = handler;
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        Integer key = (Integer) e.getKeyCode();

        if (key.equals(38))
        {
            if (!player.getKeys().contains(38))
            {
                player.addKey(38);
            }
        }

        if (key.equals(40))
        {
            if (!player.getKeys().contains(40))
            {
                player.addKey(40);
            }
        }

        if (key.equals(37))
        {
            if (!player.getKeys().contains(37))
            {
                player.addKey(37);
            }
        }

        if (key.equals(39))
        {
            if (!player.getKeys().contains(39))
            {
                player.addKey(39);
            }
        }

        if (key.equals(KeyEvent.VK_ESCAPE))
        {
            System.exit(1);
        }

        if (key.equals(KeyEvent.VK_P) && Game.gameState.equals(Game.STATE.Game))
        {
            if (Game.paused)
            {
                Game.paused = false;
            } else
            {
                Game.paused = true;
            }
        }

        if (key.equals(KeyEvent.VK_N))
        {
            AudioPlayer.getMusic("paladins").loop();
        }

        if (key.equals(KeyEvent.VK_SPACE))
        {
            if (Game.gameState.equals(Game.STATE.Shop))
            {
                Game.gameState = Game.STATE.Game;
            } else if (Game.gameState.equals(Game.STATE.Game))
            {
                Game.gameState = Game.STATE.Shop;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        Integer key = (Integer) e.getKeyCode();

        if (key.equals(38))
        {
            player.removeKey(38);
            player.setVelY(0);

        }

        if (key.equals(40))
        {
            player.removeKey(40);
            player.setVelY(0);

        }

        if (key.equals(37))
        {
            player.removeKey(37);
            player.setVelX(0);

        }

        if (key.equals(39))
        {
            player.removeKey(39);
            player.setVelX(0);
        }

    }

}
