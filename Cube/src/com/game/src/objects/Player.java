package com.game.src.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.game.src.framework.GameObject;
import com.game.src.framework.ObjectId;
import com.game.src.graphics.HUD;
import com.game.src.graphics.Textures;
import com.game.src.graphics.Trail;
import com.game.src.main.Game;

public class Player extends GameObject
{

    private ArrayList<Integer> keys;

    private boolean controlHorizontal = false;

    private Handler handler;

    private Textures tex;

    public Player(float x, float y, ObjectId id, Handler handler, Textures tex)
    {
        super(x, y, id);

        keys = new ArrayList<>();

        this.handler = handler;
        this.tex = tex;

    }

    @Override
    public void tick()
    {
        if (Game.gameState.equals(Game.STATE.Game))
        {
            keyTracker();
            handler.addObject(new Trail(x, y, ObjectId.Trail, handler, Color.green, 32, 32, 0.04f));
        }

        x += velX;
        y += velY;

        x = Game.clamp((int) x, Game.ANCHO - 32, 0);

        y = Game.clamp((int) y, Game.ALTO - 32, 0);

        Collision(handler.getObjects());
    }

    @Override
    public void render(Graphics2D g)
    {
        if (Game.gameState.equals(Game.STATE.Game) || Game.gameState.equals(Game.STATE.Shop))
            //g.setColor(Color.white);
            //g.fillRect((int) x, (int) y, 32, 32);
            g.drawImage(tex.player, (int) x, (int) y, null);
    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public void Collision(ArrayList<GameObject> object)
    {
        for (int i = 0; i < object.size(); i++)
            if (object.get(i).getId().equals(ObjectId.BoundEnemy) || object.get(i).getId().equals(ObjectId.FastEnemy) || object.get(i).getId().equals(ObjectId.SmartEnemy))
                if (getBounds().intersects(object.get(i).getBounds()))
                    HUD.HEALTH -= Game.difficulty ? 2 : 0.5;
    }

    public void keyTracker()
    {
        if (keys.contains(38))
            setVelY(Game.difficulty ? -8 : -5);

        if (keys.contains(40))
            setVelY(Game.difficulty ? 8 : 5);

        if (!controlHorizontal && keys.contains(39))
        {
            setVelX(Game.difficulty ? 8 : 5);

            if (keys.contains(37))
            {
                setVelX(Game.difficulty ? -8 : -5);
                controlHorizontal = false;
            }

        } else if (keys.contains(37))
        {
            setVelX(Game.difficulty ? -8 : -5);

            controlHorizontal = true;

            if (keys.contains(39))
                setVelX(Game.difficulty ? 8 : 5);
        }

        if (keys.isEmpty() || (keys.size() == 1 && keys.contains(39)))
            controlHorizontal = false;
    }

    public void addKey(Integer key)
    {
        keys.add(key);
    }

    public void removeKey(Integer key)
    {
        keys.remove(key);
    }

    public ArrayList<Integer> getKeys()
    {
        return keys;
    }
}
