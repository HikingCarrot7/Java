package com.game.src.graphics;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.src.framework.GameObject;
import com.game.src.framework.ObjectId;
import com.game.src.objects.Handler;

public class Trail extends GameObject
{

    private final Handler handler;
    private final Color color;
    private float alpha = 1;
    private final float life;
    private final int w, h;

    public Trail(float x, float y, ObjectId id, Handler handler, Color color, int w, int h, float life)
    {
        super(x, y, id);

        this.color = color;
        this.w = w;
        this.h = h;
        this.life = life;
        this.handler = handler;

    }

    @Override
    public void render(Graphics2D g)
    {
        g.setComposite(makeTransparent(alpha));
        g.setColor(color);
        g.fillRect((int) x, (int) y, w, h);
        g.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha)
    {
        return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
    }

    @Override
    public void tick()
    {
        if (alpha > life)
            alpha -= (life - 0.0001f);
        else
            handler.removeObject(this);
    }

    @Override
    public Rectangle getBounds()
    {
        return null;
    }

}
