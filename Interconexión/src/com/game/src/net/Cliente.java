package com.game.src.net;

import com.game.src.graphics.RenderHandler;
import com.game.src.objects.GameId;
import com.game.src.objects.Player;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author HikingCarrot
 */
public final class Cliente 
{
    private Socket cliente;
    private DataOutputStream out;
    private DataInputStream in;
    private int currentPlayer;
    private Thread thread;
    private String newUser = "newUser";
    private RenderHandler renderHandler;
    
    public Cliente(RenderHandler renderHandler)
    {
        this.renderHandler = renderHandler;
        
        iniciarCliente();
    }
    
    public void iniciarCliente()
    {
        thread = new Thread(()->
        {
            while(true)
            {
                try 
                {
                    Thread.sleep(1);
                    
                } catch (InterruptedException ex) 
                {
                    System.out.println(ex.getMessage());
                }
                
                while (renderHandler.getPlayer() == null ? true : renderHandler.getPlayer().isMoving()) 
                {
                    try 
                    {
                        cliente = new Socket("192.168.0.9", 9999);
                        out = new DataOutputStream(cliente.getOutputStream());
                        in = new DataInputStream(cliente.getInputStream());
                        
                        out.writeUTF(newUser);
                        
                        if(!newUser.equals("null"))
                        {
                            currentPlayer = Integer.parseInt(in.readUTF());
                            renderHandler.addObject(new Player(currentPlayer * 50, 100, GameId.Player, currentPlayer));
                            System.out.println("xdxddd");
                            newUser = "null";
                        }
                        
                        out.writeUTF("X: " + renderHandler.getPlayer().getX() + " Y: " + renderHandler.getPlayer().getY());
                        
                        /*cliente.close();
                        out.close();*/
                        
                    } catch (IOException ex) 
                    {
                        System.out.println(ex.getMessage());
                    }

                }
            }
        });
        
        thread.start();
    
    }
    
    public int getCurrentPlayer()
    {
        return currentPlayer;
    }
    
}
