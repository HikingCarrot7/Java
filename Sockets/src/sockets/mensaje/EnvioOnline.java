package sockets.mensaje;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author HikingCarrot7
 */
public class EnvioOnline extends WindowAdapter
{

    @Override
    public void windowOpened(WindowEvent e)
    {
        try
        {
            Socket miSocket = new Socket(InetAddress.getLocalHost().getHostName(), 9999);
            
            
            
        }catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
}
