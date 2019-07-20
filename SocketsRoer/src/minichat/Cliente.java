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
    private final String mensaje, host;
    
    public Cliente(int puerto, String mensaje, String host)
    {
        this.puerto = puerto;
        this.mensaje = mensaje;
        this.host = host;
        
        new Thread(this).start();
        
    }
    
    @Override
    public void run() 
    {
        try 
        {
            cliente = new Socket(host, puerto);
            
            out = new DataOutputStream(cliente.getOutputStream());
            
            out.writeUTF(mensaje);
            
            cliente.close();
            
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
}
