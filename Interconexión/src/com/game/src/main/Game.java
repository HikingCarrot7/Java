package com.game.src.main;

import com.game.src.graphics.RenderHandler;
import com.game.src.input.KeyInput;
import com.game.src.net.Cliente;
import com.game.src.net.Servidor;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

/**
 *
 * @author HikingCarrot
 */
public class Game extends Canvas
{

    public static int ANCHO = 600, ALTO = 500;

    private RenderHandler renderHandler;
    private Servidor server;
    private Cliente cliente;

    public static void main(String[] args)
    {
        new Window(ANCHO, ALTO, "Interconexion", new Game());
    }

    public synchronized void start()
    {

    }

    public void init()
    {
        createBufferStrategy(3);

        renderHandler = new RenderHandler();

        server = new Servidor();
        cliente = new Cliente(renderHandler);

        addKeyListener(new KeyInput(renderHandler));
        requestFocus();

    }

    public void tick()
    {
        renderHandler.tick();

    }

    public void render()
    {
        BufferStrategy bs = getBufferStrategy();

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, ANCHO, ALTO);

        renderHandler.render(g);

        g.dispose();
        bs.show();

    }

}
