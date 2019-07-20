package conexionudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author HikingCarrot
 */
public class Servidor 
{

    public void main(String[] args)
    {
        byte[] buffer = new byte[1024];
        final int PUERTO = 9999;
        
        try 
        {
            System.out.println("Iniciado el servidor UDP");
            
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            
            socketUDP.receive(peticion);
            
            System.out.println(new String(peticion.getData()));
            
            buffer = "Hola desde el servidor ".getBytes();
            
            socketUDP.send(new DatagramPacket(buffer, buffer.length, peticion.getAddress(), peticion.getPort()));
            
            socketUDP.close();
            
        } catch (SocketException ex) 
        {
            System.out.println(ex.getMessage());
            
        } catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }

    }

}
