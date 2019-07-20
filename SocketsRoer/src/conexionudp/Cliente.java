package conexionudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author HikingCarrot
 */
public class Cliente 
{
    public void main(String[] args) 
    {
        final int PUERTO = 9999;
        byte[] buffer = new byte[1024];
        
        try 
        {
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            DatagramSocket socketUDP = new DatagramSocket();
            
            buffer = "Hola desde el cliente ".getBytes();
            
            socketUDP.send(new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO));
            
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            
            socketUDP.receive(peticion);
            
            System.out.println(new String(peticion.getData()));
            
            socketUDP.close();
            
        } catch (SocketException | UnknownHostException ex) 
        {
            ex.printStackTrace();
            
        } catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }
    
}
