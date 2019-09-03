package com.game.src.input;

import com.game.src.UI.Menu;
import com.game.src.net.Cliente;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Mohammed
 */
public class MouseInput extends MouseAdapter
{

    private final Menu menu;
    private Cliente cliente;

    public MouseInput(Menu menu)
    {
        this.menu = menu;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (cliente != null)
            cliente.mousePressed(e);

        menu.mousePressed(e);

    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

}
