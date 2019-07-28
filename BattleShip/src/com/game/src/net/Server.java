package com.game.src.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author HikingCarrot7
 */
public final class Server
{

    private ServerSocket server;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int contPlayer = 0;
    private final ArrayList<String> ips;

    public Server()
    {
        ips = new ArrayList<>();

        iniciarServer();
    }

    private void iniciarServer()
    {
        try
        {
            server = new ServerSocket(9999);

            while (true)
            {
                Socket jugador = server.accept();

                System.out.println("Se ha conectado un jugador");

                //Leemos el objeto que llega por primera vez al del cliente
                in = new ObjectInputStream(jugador.getInputStream());
                out = new ObjectOutputStream(jugador.getOutputStream());

                MensajeEnviar mensaje = (MensajeEnviar) in.readObject();

                //Detectamos si es nuevo el usuario
                if (mensaje.getFila() != -2)
                {
                    if (mensaje.isNuevo())
                    {
                        System.out.println("Se detecto a un nuevo jugador");

                        //Avisamos al jugador 1 que ya se conecto el jugador 2
                        if (ips.size() > 0)
                        {
                            Socket socket = new Socket(ips.get(0), 10000);

                            ObjectOutputStream out2 = new ObjectOutputStream(socket.getOutputStream());

                            MensajeEnviar mensajeAvisarConexion = new MensajeEnviar(-1, 0, 0, 2, false, false, ips.get(0));

                            out2.writeObject(mensajeAvisarConexion);

                            out2.close();
                            socket.close();

                            System.out.println("Le avise al jugador 1");

                        }

                        ips.add(mensaje.getIp());

                        System.out.println(ips);

                        mensaje = new MensajeEnviar(0, 0, contPlayer++, ips.size(), false, false, null);

                        out.writeObject(mensaje);

                        System.out.println("He enviado los datos al jugador " + contPlayer);

                    } else
                    {
                        System.out.println(mensaje.getMiMarca());

                        Socket envio = new Socket(mensaje.getMiMarca() == 0 ? ips.get(1) : ips.get(0), 10000);

                        out = new ObjectOutputStream(envio.getOutputStream());

                        out.writeObject(mensaje);

                    }
                }

            }

        } catch (IOException | ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());

        } finally
        {
            try
            {
                server.close();

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }
        }

    }

}
