package com.game.src.main;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author HikingCarrot7
 */
public class Window extends JFrame
{

    private static final long serialVersionUID = 1L;

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

        new Ticker(main);
    }

}
