package com.game.src.main;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

import com.game.src.graphics.RenderHandler;

public class Window extends JFrame
{

    private static final long serialVersionUID = 1L;

    public Window(int w, int h, String title, Game game, RenderHandler renderer)
    {
        game.setPreferredSize(new Dimension(w, h));
        game.setMaximumSize(new Dimension(w, h));
        game.setMinimumSize(new Dimension(w, h));

        setTitle(title);
        setResizable(false);
        setVisible(true);
        add(game);
        pack();
        setLocationRelativeTo(null);
        addComponentListener(new ComponentListener()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                game.getRenderer().getCamera().w = game.getWidth();
                game.getRenderer().getCamera().h = game.getHeight();

                int newWidth = game.getWidth();
                int newHeight = game.getHeight();

                if (newWidth > renderer.getMaxScreenWidth())
                {
                    newWidth = renderer.getMaxScreenWidth();
                }

                if (newHeight > renderer.getMaxScreenHeight())
                {
                    newHeight = renderer.getMaxScreenHeight();
                }

                renderer.getCamera().w = newWidth;
                renderer.getCamera().h = newHeight;

                game.setSize(newWidth, newHeight);

                pack();

            }

            @Override
            public void componentHidden(ComponentEvent arg0)
            {
            }

            @Override
            public void componentMoved(ComponentEvent arg0)
            {
            }

            @Override
            public void componentShown(ComponentEvent arg0)
            {
            }

        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Ticker(game);
    }

}
