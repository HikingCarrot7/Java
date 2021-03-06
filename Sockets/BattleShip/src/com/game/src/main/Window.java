package com.game.src.main;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Mohammed
 */
public class Window extends JFrame
{

    public Window(int w, int h, String title, Main main)
    {
        main.setPreferredSize(new Dimension(w, h));
        main.setMaximumSize(new Dimension(w, h));
        main.setMinimumSize(new Dimension(w, h));

        setTitle(title);
        setResizable(false);
        setAlwaysOnTop(true);
        add(main);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        new Ticker(main, this);
    }

}
