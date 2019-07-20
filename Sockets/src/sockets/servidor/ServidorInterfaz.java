package sockets.servidor;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ServidorInterfaz extends JPanel 
{

    private static final long serialVersionUID = 1L;

    private JTextArea texto;
    private Thread thread;

    public ServidorInterfaz() 
    {
        setLayout(new BorderLayout());

        texto = new JTextArea();
        
        thread = new Thread(() -> 
        {
            while (true) 
            {
                try 
                {
                    ServerSocket servidor = new ServerSocket(9999);
                    Socket miSocket = servidor.accept();
                    DataInputStream flujoEntrada = new DataInputStream(miSocket.getInputStream());

                    texto.append(flujoEntrada.readUTF() + "\n");

                    miSocket.close();
                    servidor.close();

                } catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        add(texto, BorderLayout.CENTER);

    }

}
