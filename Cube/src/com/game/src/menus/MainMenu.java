package com.game.src.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.game.src.audio.AudioPlayer;
import com.game.src.framework.ObjectId;
import com.game.src.graphics.HUD;
import com.game.src.graphics.Textures;
import com.game.src.main.Game;
import com.game.src.objects.BoundEnemy;
import com.game.src.objects.Handler;
import com.game.src.objects.Spawn;

public class MainMenu
{

    public Rectangle[] rectangles; //play, help, exit, back;
    private Handler handler;
    private Random rand;
    private HUD hud;
    private Spawn spawn;
    private Game game;
    private Textures tex;

    public MainMenu(Handler handler, HUD hud, Spawn spawn, Game game, Textures tex)
    {
        rectangles = new Rectangle[6];

        rectangles[0] = new Rectangle(Game.ANCHO / 2 - 100, 185, 200, 45);
        rectangles[1] = new Rectangle(Game.ANCHO / 2 - 100, 285, 200, 45);
        rectangles[2] = new Rectangle(Game.ANCHO / 2 - 100, 385, 200, 45);
        rectangles[3] = new Rectangle(Game.ANCHO / 2 - 100, 485, 200, 45);
        rectangles[4] = new Rectangle(Game.ANCHO / 2 - 250, 485, 200, 45);
        rectangles[5] = new Rectangle(Game.ANCHO / 2 + 50, 485, 200, 45);

        this.handler = handler;
        this.hud = hud;
        this.spawn = spawn;
        this.game = game;
        this.tex = tex;

        rand = new Random();

    }

    public void tick()
    {

    }

    public void render(Graphics2D g)
    {
        if (Game.gameState.equals(Game.STATE.Menu))
        {
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 70));
            g.drawString("Wave", Game.ANCHO / 2 - 90, 100);

            g.setFont(new Font("serif", Font.BOLD, 40));

            g.drawString("Play", rectangles[0].x + rectangles[0].width / 2 - 35, rectangles[0].y + rectangles[0].height / 2 + 12);

            g.drawString("Help", rectangles[1].x + rectangles[1].width / 2 - 35, rectangles[1].y + rectangles[1].height / 2 + 12);

            g.drawString("Exit", rectangles[2].x + rectangles[2].width / 2 - 35, rectangles[2].y + rectangles[2].height / 2 + 15);

            for (int i = 0; i < rectangles.length - 3; i++)
                g.draw(rectangles[i]);

        } else if (Game.gameState.equals(Game.STATE.Help))
        {
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 70));
            g.drawString("Help", Game.ANCHO / 2 - 90, 100);

            g.setFont(new Font("serif", Font.BOLD, 35));
            g.drawString("Use arrow keys to move player and dodge enemies", Game.ANCHO / 2 - 380, Game.ALTO / 2);

            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("Back", rectangles[3].x + rectangles[3].width / 2 - 45, rectangles[3].y + rectangles[3].height / 2 + 15);

            g.draw(rectangles[3]);

        } else if (Game.gameState.equals(Game.STATE.End))
        {
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 70));
            g.drawString("Game Over", Game.ANCHO / 2 - 170, 150);

            g.setFont(new Font("serif", Font.BOLD, 35));
            g.drawString("You lost with a score of: " + hud.getScore(), Game.ANCHO / 2 - 210, Game.ALTO / 2);

            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("Restart", rectangles[4].x + rectangles[3].width / 2 - 63, rectangles[3].y + rectangles[3].height / 2 + 15);

            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("Menu", rectangles[5].x + rectangles[3].width / 2 - 50, rectangles[3].y + rectangles[3].height / 2 + 15);

            g.draw(rectangles[4]);
            g.draw(rectangles[5]);

        } else if (Game.gameState.equals(Game.STATE.Select))
        {
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 45));
            g.drawString("SELECT DIFFICULTY", Game.ANCHO / 2 - 220, 100);

            g.setFont(new Font("serif", Font.BOLD, 40));

            g.drawString("Normal", rectangles[0].x + rectangles[0].width / 2 - 65, rectangles[0].y + rectangles[0].height / 2 + 12);

            g.drawString("Hard", rectangles[1].x + rectangles[1].width / 2 - 45, rectangles[1].y + rectangles[1].height / 2 + 12);

            g.drawString("Back", rectangles[2].x + rectangles[2].width / 2 - 45, rectangles[2].y + rectangles[2].height / 2 + 15);

            for (int i = 0; i < rectangles.length - 3; i++)
                g.draw(rectangles[i]);
        }

    }

    public void mousePressed(MouseEvent e)
    {
        Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);

        if (r.intersects(rectangles[0]) && Game.gameState.equals(Game.STATE.Menu))
        {
            Game.gameState = Game.STATE.Select;

            playSound();

        } else if (r.intersects(rectangles[0]) && Game.gameState.equals(Game.STATE.Select))
        {
            Game.gameState = Game.STATE.Game;

            Game.difficulty = false;

            handler.clearEnemies();

            for (int i = 0; i < 2; i++)
                handler.addObject(new BoundEnemy(rand.nextInt(Game.ANCHO - 32), rand.nextInt(Game.ALTO - 32), ObjectId.BoundEnemy, handler, tex));

            playSound();

        } else if (r.intersects(rectangles[1]) && Game.gameState.equals(Game.STATE.Menu))
        {
            Game.gameState = Game.STATE.Help;

            playSound();

        } else if (r.intersects(rectangles[1]) && Game.gameState.equals(Game.STATE.Select))
        {
            Game.gameState = Game.STATE.Game;

            handler.clearEnemies();

            Game.difficulty = true;

            for (int i = 0; i < 2; i++)
                handler.addObject(new BoundEnemy(rand.nextInt(Game.ANCHO - 32), rand.nextInt(Game.ALTO - 32), ObjectId.BoundEnemy, handler, tex));

            playSound();

        } else if (r.intersects(rectangles[2]) && Game.gameState.equals(Game.STATE.Menu))
        {
            playSound();

            System.exit(1);

        } else if (r.intersects(rectangles[2]) && Game.gameState.equals(Game.STATE.Select))
        {
            Game.gameState = Game.STATE.Menu;

            playSound();

        } else if (r.intersects(rectangles[3]) && Game.gameState.equals(Game.STATE.Help))
        {
            Game.gameState = Game.STATE.Menu;

            playSound();

        } else if (r.intersects(rectangles[4]) && Game.gameState.equals(Game.STATE.End))
        {
            Game.gameState = Game.STATE.Game;

            init();

            for (int i = 0; i < 2; i++)
                handler.addObject(new BoundEnemy(rand.nextInt(Game.ANCHO - 32), rand.nextInt(Game.ALTO - 32), ObjectId.BoundEnemy, handler, tex));

            playSound();

        } else if (r.intersects(rectangles[5]) && Game.gameState.equals(Game.STATE.End))
        {
            Game.gameState = Game.STATE.Menu;

            init();

            game.createBackground();

            playSound();
        }

    }

    public void init()
    {
        HUD.HEALTH = 100;
        hud.setScore(0);
        hud.setLevel(1);

        spawn.setScoreKeep(0);

        handler.clearEnemies();

        game.getExplosion().clear();

        handler.getObjects().get(0).setX(Game.ANCHO / 2 - 16);
        handler.getObjects().get(0).setY(Game.ALTO / 2 - 16);

    }

    public void playSound()
    {
        AudioPlayer.getSound("sound").play();
    }

}
