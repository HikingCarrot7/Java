package com.game.src.objects;

import com.game.src.framework.ObjectId;
import com.game.src.graphics.HUD;
import com.game.src.graphics.Texture;
import com.game.src.main.Game;
import java.util.Random;

public class Spawn
{

    private Handler handler;
    private HUD hud;
    private Random rand;
    private Texture tex;

    private int scoreKeep = 0;

    private boolean bossFight = false;

    public Spawn(Handler handler, HUD hud, Texture tex)
    {
        this.handler = handler;
        this.hud = hud;
        this.tex = tex;

        rand = new Random();

    }

    public void tick()
    {
        scoreKeep++;

        if (scoreKeep >= 100 * hud.getLevel())
        {
            scoreKeep = 0;

            hud.setLevel(hud.getLevel() + 1);

            hud.setLevelAnimation(true);

            if (hud.getLevel() % 10 != 0 && !bossFight)
            {
                handler.addObject(new BoundEnemy(rand.nextInt(Game.ANCHO - 32), rand.nextInt(Game.ALTO - 32), ObjectId.BoundEnemy, handler, tex));

                if (hud.getLevel() % 3 == 0)
                    handler.addObject(new FastEnemy(rand.nextInt(Game.ANCHO - 32), rand.nextInt(Game.ALTO - 32), ObjectId.FastEnemy, handler, tex));

                if (hud.getLevel() == 3)
                {
                    SmartEnemy tempEnemy = new SmartEnemy(Game.ANCHO / 2, Game.ALTO / 2, ObjectId.SmartEnemy, handler, tex);
                    handler.addObject(tempEnemy);
                    tempEnemy.setRazon((float) (hud.getLevel() * 0.5));

                }

            } else if (!bossFight)
            {
                handler.clearEnemies();

                handler.addObject(new EnemyBoss(Game.ANCHO / 2 - 48, - 120, ObjectId.EnemyBoss, handler));

                bossFight = true;

            }

        }

    }

    public void setScoreKeep(int scoreKeep)
    {
        this.scoreKeep = scoreKeep;
    }

}
