package com.game.src.cliente;

import com.game.src.map.Cuadricula;
import com.game.src.graphics.Drawable;
import com.game.src.input.Listener;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 *
 * @author HikingCarrot7
 */
public class Cliente implements Drawable, Listener
{

    private final Cuadricula barcos, enemigo;

    public Cliente()
    {
        barcos = new Cuadricula(80, 70);
        enemigo = new Cuadricula(80, 400);

    }

    @Override
    public void render(Graphics2D g)
    {
        barcos.render(g);
        enemigo.render(g);

    }

    @Override
    public void tick()
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        //enemigo.modificarTablero((e.getY() - 400) / 24, (e.getX() - 80) / 24, 1);
        
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        
    }


}
