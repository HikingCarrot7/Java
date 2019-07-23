package sockets.mensaje;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import sockets.cliente.ClienteInterfaz;

/**
 *
 * @author HikingCarrot7
 */
public class EnvioOnline extends WindowAdapter
{
    
    private ClienteInterfaz cliente;
    
    public EnvioOnline(ClienteInterfaz cliente)
    {
        this.cliente = cliente;
    }

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
            
            salidaDatos.setNick(cliente.getNick());
            salidaDatos.setIp(InetAddress.getLocalHost().getHostAddress());
            salidaDatos.setMensaje(id);
            salidaDatos.setControl(control);
            
            salida.writeObject(salidaDatos);

            salida.close();
            miSocket.close();
            
        }catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
}
