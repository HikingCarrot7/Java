package com.game.src.objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.game.src.framework.GameObject;
import com.game.src.framework.ObjectId;
import com.game.src.main.Game;

public class Block extends GameObject
{

    private int type;

    public Block(int x, int y, int type, ObjectId id)
    {
        super(x, y, id);

        this.type = type;
    }

    @Override
    public void tick(ArrayList<GameObject> object)
    {

    }

    @Override
    public void render(Graphics2D g)
    {
        if (type == 0)
        {
            g.drawImage(Game.getTexture().block[0], (int) x, (int) y, null);
        } else
        {
            g.drawImage(Game.getTexture().block[1], (int) x, (int) y, null);
        }
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

}
