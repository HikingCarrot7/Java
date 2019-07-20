package minichat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

/**
 *
 * @author HikingCarrot
 */
public class Servidor extends Observable implements Runnable
{
    private final int puerto;
    private final Thread thread;
    private ServerSocket server;
    private Socket cliente;
    private DataInputStream in;
    
    public Servidor(int puerto)
    {
        this.puerto = puerto;
        
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() 
    {
        try 
        {
            server = new ServerSocket(puerto);
            System.out.println("Servidor iniciado");
            
            while(true)
            {
                cliente = server.accept();
                System.out.println("Cliente conectado");
                
                in = new DataInputStream(cliente.getInputStream());
                
                setChanged();
                notifyObservers(in.readUTF());
                clearChanged();
                
                
                cliente.close();
            }
            
        } catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }


    
}
