package com.game.src.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.game.src.main.Game;
import com.game.src.objects.Handler;

public class HUD
{

    public static int HEALTH = 100;
    public int bounds = 0;
    private boolean LevelAnimation = false;

    private float y = - 30, velY = 8f;

    private int score = 0, level = 1;

    private Game game;
    private Handler handler;

    public HUD(Game game, Handler handler)
    {
        this.game = game;
        this.handler = handler;
    }

    public void tick()
    {
        Game.clamp(HEALTH, 100 + bounds, 0);

        score++;

        if (LevelAnimation)
        {
            y += velY;

            if (y >= Game.ALTO / 2)
            {
                LevelAnimation = false;

                y = -30;
            }
        }

        if (HEALTH <= 0)
        {
            Game.gameState = Game.STATE.End;

            game.addExplosion(new ExplosionAnimation(handler.getObjects().get(0).getX(), handler.getObjects().get(0).getY()));
        }
    }

    public void render(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect(5, 5, 200 + bounds, 32);

        g.setColor(Color.getHSBColor((1f * HEALTH) / 360, 1f, 1f));
        g.fillRect(5, 5, HEALTH * 2, 32);

        g.setColor(Color.white);
        g.drawRect(5, 5, 200 + bounds, 32);

        g.setFont(new Font("serif", Font.BOLD, 15));
        g.drawString("Score: " + score, 5, 55);

        g.drawString("Level: " + level, 5, 70);

        g.drawString(Game.difficulty ? "Difficulty: HARD" : "Difficulty: NORMAL", 5, 85);

        g.drawString("Space for shop", 5, 100);

        g.setFont(new Font("serif", Font.BOLD, 50));
        g.drawString(getLevel() % 10 == 0 ? "BOSS FIGHT!!" : "Level: " + level + "!", getLevel() % 10 == 0 ? Game.ANCHO / 2 - 180 : Game.ANCHO / 2 - 100, (int) y);

    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public void setLevelAnimation(boolean levelAnimation)
    {
        LevelAnimation = levelAnimation;
    }

}
