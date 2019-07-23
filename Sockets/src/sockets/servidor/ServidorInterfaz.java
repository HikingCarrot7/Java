package sockets.servidor;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import sockets.mensaje.Mensaje;

public final class ServidorInterfaz extends JPanel
{

    private final JTextArea texto;
    private Thread thread;
    private final int PUERTO = 9999;
    private final int PUERTO2 = 10000;
    private final ArrayList<String> ips;

    public ServidorInterfaz()
    {
        setLayout(new BorderLayout());

        ips = new ArrayList<>();

        texto = new JTextArea();

        iniciarServer();

        add(texto, BorderLayout.CENTER);

    }

    public void iniciarServer()
    {
        thread = new Thread(() ->
        {
            try
            {
                String nick, ip, mensaje;
                Mensaje paqueteRecibido;
                ServerSocket servidor = new ServerSocket(PUERTO);

                while (true)
                {
                    //El socket del cliente que envió el mensaje
                    Socket miSocket = servidor.accept();

                    //Canal de entrada del socket
                    ObjectInputStream paqueteDatos = new ObjectInputStream(miSocket.getInputStream());

                    //Leer el objeto SERIALIZADO del paquete
                    paqueteRecibido = (Mensaje) paqueteDatos.readObject();

                    //Leer los datos 
                    nick = paqueteRecibido.getNick();
                    ip = paqueteRecibido.getIp();
                    mensaje = paqueteRecibido.getMensaje();
                    
                    //-----DETECTA NEW USER ONLINE O ELIMINA A UN USER-----------------
                    if (!paqueteRecibido.isControl())
                    {
                        if (mensaje.equals("NEWUSER"))
                        {
                            ips.add(ip);
                            System.out.println("SE HA AÑADIDO A UN NUEVO USER");

                        } else
                        {
                            ips.remove(ip);
                            System.out.println("SE HA ELIMINADO A UN USER");
                        }
                        
                        updateUsers();

                        //-------------------------------------
                    } else
                    {
                        //Ponerlos en el JTextArea
                        texto.append("\n" + nick + ": " + mensaje + " para: " + ip);

                        //Crear un nuevo socket para enviar los datos al destinatario (Es posible que el puerto falle)
                        Socket destinatario = new Socket(ip, PUERTO2);

                        //Output stream para enviar los datos (obtener le output del socket que ya tiene la ip del destinatario)
                        ObjectOutputStream paqueteReenvio = new ObjectOutputStream(destinatario.getOutputStream());

                        //Enviamos el paquete recibido al destinatario
                        paqueteReenvio.writeObject(paqueteRecibido);

                        //Cerramos los sockets
                        paqueteReenvio.close();
                        destinatario.close();
                        miSocket.close();

                    }

                }

            } catch (IOException | ClassNotFoundException e)
            {
                System.out.println(e.getMessage());

            }
        });

        thread.start();
    }

    private void updateUsers()
    {
        if (ips.size() > 0)
        {
            for (int i = 0; i < ips.size(); i++)
            {
                try
                {
                    Socket clienteActual = new Socket(ips.get(i), PUERTO2);

                    ObjectOutputStream out = new ObjectOutputStream(clienteActual.getOutputStream());

                    out.writeObject(ips);

                } catch (IOException ex)
                {
                    System.out.println(ex.getMessage());
                }

            }
        }
    }

}
