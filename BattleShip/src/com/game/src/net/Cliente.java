package com.game.src.net;

import com.game.src.map.Cuadricula;
import com.game.src.graphics.Drawable;
import com.game.src.input.InputListener;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 *
 * @author HikingCarrot7
 */
public class Cliente implements Drawable, InputListener
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
    
    public void setBarcos(int[][] tablero)
    {
        barcos.recibirTablero(tablero);
    }


}
