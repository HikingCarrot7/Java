package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class Menu
{

    private final Rectangle play, help, exit;
    private final Game game;

    public Menu(Game game)
    {
        this.game = game;

        play = new Rectangle(Game.ANCHO / 2 + 70, 150, 200, 50);
        help = new Rectangle(Game.ANCHO / 2 + 70, 250, 200, 50);
        exit = new Rectangle(Game.ANCHO / 2 + 70, 350, 200, 50);
    }

    public void render(Graphics2D g)
    {
        g.setColor(Color.white);

        g.setFont(new Font("serif", Font.BOLD, 50));

        g.drawString("Space Game", Game.ANCHO - 130, 60);

        g.drawString("Play", play.x + 52, play.y + 37);

        g.drawString("Help", help.x + 52, help.y + 37);

        g.drawString("Exit", exit.x + 52, exit.y + 40);

        g.draw(play);
        g.draw(help);
        g.draw(exit);

    }

    public void mousePressed(MouseEvent e)
    {
        Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);

        if (r.intersects(play))
            Game.state = Game.STATE.GAME;

        else if (r.intersects(help))
        {

        } else if (r.intersects(exit))
            game.stop();

        if (r.intersects(game.getReset()) && game.getMuerte())
            game.reset();
    }

}
