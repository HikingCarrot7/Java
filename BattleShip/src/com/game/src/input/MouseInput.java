package com.game.src.input;

import com.game.src.UI.Menu;
import com.game.src.cliente.Cliente;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author HikingCarrot7
 */
public class MouseInput extends MouseAdapter
{

    private final Cliente cliente;
    private final Menu menu;

    public MouseInput(Cliente cliente, Menu menu)
    {
        this.cliente = cliente;
        this.menu = menu;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        cliente.mousePressed(e);
        menu.mousePressed(e);

    }

}
