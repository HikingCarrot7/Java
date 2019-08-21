package com.game.src.framework;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.game.src.main.Camera;
import com.game.src.main.Game;
import com.game.src.objects.Bullet;
import com.game.src.objects.Handler;
import com.game.src.objects.Player;

public class MouseInput extends MouseAdapter
{

    private Handler handler;
    private Player player;
    private Camera camera;

    private float razon = 10;

    public MouseInput(Handler handler, Camera camera)
    {
        this.handler = handler;
        this.camera = camera;

        for (int i = 0; i < handler.getObjects().size(); i++)
        {
            if (handler.getObjects().get(i).getId().equals(ObjectId.Player))
            {
                player = (Player) handler.getObjects().get(i);

                break;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        float mouseCoordenadasX = e.getX() + player.getX() - Game.ANCHO / 2;
        float mouseCoordenadasY = e.getY() + player.getY() - Game.ALTO / 2;

        float diffX = player.getX() - mouseCoordenadasX;
        float diffY = player.getY() - mouseCoordenadasY;

        System.out.println("-------------------------");
        System.out.println("CoordenadasMouseX " + mouseCoordenadasX);
        System.out.println("CoordenadasMouseY " + mouseCoordenadasY);
        System.out.println("CoordenadasJugadorX " + player.getX());
        System.out.println("CoordenadasJugadorY " + player.getY());

        float distance = distanciaPuntos((int) e.getX(), (int) e.getY(), (int) player.getX(), (int) player.getY());

        handler.addObject(new Bullet(player.getX() + 16, player.getY() + 32, ObjectId.Bullet, (float) ((-razon / 300) * diffX), (float) ((-razon / 300) * diffY)));

    }

    public float distanciaPuntos(int x1, int y1, int x2, int y2)
    {
        float distancia = (float) (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));

        return distancia;
    }

}
