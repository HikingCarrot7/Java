package com.game.src.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import com.game.src.framework.GameObject;
import com.game.src.main.Game;

public class Handler
{

    private ArrayList<GameObject> objetos;

    private Game game;

    private BufferedImage imagen;

    public Handler(Game game, BufferedImage imagen)
    {
        objetos = new ArrayList<>();

        this.game = game;

        this.imagen = imagen;

    }

    public void tick()
    {
        try
        {
            for (GameObject O : objetos)
            {
                O.tick(objetos);
            }

        } catch (ConcurrentModificationException e)
        {
        }

    }

    public void render(Graphics2D g)
    {
        try
        {
            for (GameObject O : objetos)
            {
                O.render(g);
            }

        } catch (ConcurrentModificationException e)
        {
        }

    }

    public void switchLevel()
    {
        Game.LEVEL++;

        game.LoadImageLevel(imagen);

    }

    public void removeLevel()
    {
        objetos.clear();
    }

    public void addObject(GameObject objeto)
    {
        objetos.add(objeto);
    }

    public void removeObject(GameObject objeto)
    {
        objetos.remove(objeto);
    }

    public ArrayList<GameObject> getObjects()
    {
        return objetos;
    }

}
