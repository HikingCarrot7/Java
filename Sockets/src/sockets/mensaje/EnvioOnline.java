package sockets.mensaje;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
        enviarMensaje("NEWUSER", false);
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        enviarMensaje("CLOSEDUSER", false);
    }
    
    private void enviarMensaje(String id, boolean control)
    {
        try
        {
            Socket miSocket = new Socket(InetAddress.getLocalHost().getHostName(), 9999);
            
            ObjectOutputStream salida = new ObjectOutputStream(miSocket.getOutputStream());
            
            Mensaje salidaDatos = new Mensaje();
            
            salidaDatos.setMensaje(id);
            salidaDatos.setIp(InetAddress.getLocalHost().getHostAddress());
            salidaDatos.setControl(control);
            
            salida.writeObject(salidaDatos);

            salida.close();
            
        }catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
}
