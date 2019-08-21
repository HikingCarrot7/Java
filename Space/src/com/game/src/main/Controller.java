package com.game.src.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

import com.game.src.main.interfaces.Entity;
import com.game.src.main.interfaces.EntityA;
import com.game.src.main.interfaces.EntityB;

public class Controller
{

    private ArrayList<EntityA> entidadA;
    private ArrayList<EntityB> entidadB;

    private Game game;

    private Random rand;

    public Controller(Game game)
    {
        entidadA = new ArrayList<>();
        entidadB = new ArrayList<>();

        this.game = game;

        rand = new Random();
    }

    public void tick()
    {
        move(entidadA);

        move(entidadB);
    }

    public void generarEnemigo(int enemigos)
    {
        for (int i = 0; i < enemigos; i++)
        {
            addEntity(entidadB, new Enemy(rand.nextInt(Game.ANCHO * Game.ESCALA), - 10, this, game));
        }
    }

    public void render(Graphics g)
    {
        dibujar(g, entidadA);

        dibujar(g, entidadB);

    }

    public <T extends Entity> void addEntity(ArrayList<T> lista, T entidad)
    {
        lista.add(entidad);
    }

    public void removeEntity(ArrayList<? extends Entity> lista, Entity entidad)
    {
        lista.remove(entidad);
    }

    public void move(ArrayList<? extends Entity> entidad)
    {
        for (int i = 0; i < entidad.size(); i++)
        {
            entidad.get(i).tick();

            try
            {
                if (entidad.get(i).getY() <= - 50 && entidad.get(i) instanceof Bullet)
                {
                    removeEntity(entidadA, entidad.get(i));
                }

            } catch (IndexOutOfBoundsException e)
            {
            }
        }
    }

    public void dibujar(Graphics g, ArrayList<? extends Entity> entidad)
    {

        try
        {
            for (Entity E : entidad)
            {
                E.render(g);
            }

        } catch (ConcurrentModificationException e)
        {
        }
    }

    public ArrayList<EntityB> obtenerEntidadesB()
    {
        return entidadB;
    }

    public ArrayList<EntityA> obtenerEntidadesA()
    {
        return entidadA;
    }

}
