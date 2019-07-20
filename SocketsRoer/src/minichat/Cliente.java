package minichat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author HikingCarrot
 */
public class Cliente implements Runnable
{
    private Socket cliente;
    private DataOutputStream out;
    private final int puerto;
    private String mensaje;
    
    public Cliente(int puerto, String mensaje)
    {
        this.puerto = puerto;
        this.mensaje = mensaje;
        
        new Thread(this).start();
        
    }
    
    @Override
    public void run() 
    {
        try 
        {
            cliente = new Socket("192.168.0.2", puerto);
            
            out = new DataOutputStream(cliente.getOutputStream());
            
            out.writeUTF(mensaje);
            
            cliente.close();
            
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
}
