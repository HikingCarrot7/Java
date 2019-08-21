package com.game.src.main;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author HikingCarrot7
 */
public class Window extends JFrame
{

    public Window(String titulo, int w, int h, Main main)
    {
        super(titulo);

        main.setPreferredSize(new Dimension(w, h));
        main.setMaximumSize(new Dimension(w, h));
        main.setMinimumSize(new Dimension(w, h));

        setResizable(false);
        setVisible(true);
        add(main);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Ticker(main);
    }
}
