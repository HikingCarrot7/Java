package com.game.src.main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window  extends JFrame
{
	
	private static final long serialVersionUID = 1L;

	public Window(int w, int h, String title, Game game) 
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		new Ticker(game);
	}

	
}