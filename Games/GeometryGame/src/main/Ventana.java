package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import gameObject.Constants;
import graphics.Assets;
import input.KeyBoard;
import states.GameState;

public class Ventana extends JFrame implements Runnable
{

    private static final long serialVersionUID = 1L;
    public static final int WIDHT = 1000, HEIGHT = 600;

    //ajustamos el tama�o de la ventana
    private final Canvas canvas;

    // esta nueva clase nos permitira empezar a dibujar
    private Thread thread;
    private boolean running = false;

    //controlador del ciclo while
    //objetos para dibujar
    private BufferStrategy bs;
    private Graphics g;
    //
    private final int FPS = 60;
    private final double TARGETTIME = 1000000000 / FPS;//ajuste del tiempo de los objetivos
    private double delta = 0;//variable que almacenara el tiempo transcurrido
    private int AVERAGEFPS = FPS;

    //estado del juego
    private GameState gameState;
    //vista de la ventana

    private final KeyBoard keyBoard;

    public Ventana()
    {
        super("Game_space");//nombre de la ventana
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*permitira que el programa se pueda cerrar y no se siga ejecutando */
        setResizable(false);
        //permitira que el programa no se pueda redimensionar
        setLocationRelativeTo(null);

        canvas = new Canvas();
        keyBoard = new KeyBoard();

        canvas.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        canvas.setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        canvas.setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        canvas.setFocusable(true);//permite recibir instrucciones del teclado

        //agregamos el canvas a la ventana
        add(canvas);
        canvas.addKeyListener(keyBoard);
        //permite que la ventana se despliegue en el centro de la pantalla al ejecutar
        setVisible(true);

    }

    public static void main(String[] args)
    {
        new Ventana().start();

    }

    private void update()
    {//actualizar
        keyBoard.update();
        gameState.update();

    }

    private void draw()
    {//dibujar
        bs = canvas.getBufferStrategy();

        if (bs == null)
        {
            canvas.createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();

        //-----------------------------------
        g.setColor(Color.DARK_GRAY);

        g.fillRect(0, 0, WIDHT, HEIGHT);

        gameState.draw(g);

        g.drawString("" + AVERAGEFPS, 10, 20);

        //-----------------------------------
        g.dispose();
        bs.show();
    }

    private void init()

    {
        Assets.init();
        gameState = new GameState();

    }

    @Override
    public void run()
    {

        //el ciclo permitira actualizar la posicion de los items
        //restringimos la velocidad del ciclo para ajustarlo a los FPS
        long now = 0; //registro del tiempo
        long LastTime = System.nanoTime();//hora actual del sistema
        int frames = 0;
        long Time = 0;

        init();

        while (running)
        {
            now = System.nanoTime();//el valor que tome now sera diferente al de lasttime
            delta += (now - LastTime) / TARGETTIME;
            Time += (now - LastTime);
            LastTime = now;

            if (delta >= 1)
            {
                update();
                draw();
                delta--;
                frames++;

            }

            if (Time >= 1000000000)
            {
                AVERAGEFPS = frames;
                frames = 0;
                Time = 0;
            }
        }
        stop();
    }
    //control de ejecucion

    private void start()
    {//inicio
        //llamamos a el metodo start desde la ventana

        thread = new Thread(this); //se inicia la clase hilo(subprograma) con la ejecucion de la interfaz Runnable
        thread.start();//llamamos al metodo run para que se inicie
        running = true; //sera un interruptor para indicar al ciclo que el juego se ha iniciado
    }

    private void stop()
    {
        try
        {
            thread.join();

        } catch (InterruptedException e)
        {
            e.printStackTrace();

            running = false;//indicara al ciclo que ha terminado la ejecucion del juego
        }

    }

}
