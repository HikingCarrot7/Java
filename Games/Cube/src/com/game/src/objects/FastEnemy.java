package com.game.src.objects;

import com.game.src.framework.GameObject;
import com.game.src.framework.ObjectId;
import com.game.src.graphics.Texture;
import com.game.src.graphics.Trail;
import com.game.src.main.Game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class FastEnemy extends GameObject
{

    private Handler handler;
    private Texture tex;

    public FastEnemy(float x, float y, ObjectId id, Handler handler, Texture tex)
    {
        super(x, y, id);

        velX = Game.difficulty ? 12 : 8;
        velY = Game.difficulty ? 15 : 12;

        this.handler = handler;
        this.tex = tex;

    }

    @Override
    public void tick()
    {
        x += velX;

        y += velY;

        if (y < 0 || y > Game.ALTO - 25)
            velY *= -1;

        if (x < 0 || x > Game.ANCHO - 32)
            velX *= -1;

        handler.addObject(new Trail(x, y, ObjectId.Trail, handler, Color.cyan, 16, 16, 0.02f));

    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Color.cyan);
        g.fillRect((int) x, (int) y, 16, 16);

        //g.drawImage(tex.fastEnemy, (int) x, (int) y, null);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

}
