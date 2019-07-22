package sockets.cliente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sockets.mensaje.Mensaje;

public class ClienteInterfaz extends JPanel implements Runnable
{

    private static final long serialVersionUID = 1L;

    private JLabel info;
    private JButton enviar;
    private JTextField texto, nombreUser, ip;
    private JTextArea areaTexto;
    private JPanel soporteTexto, soporteEnvio, soporteDatos;
    
    public ClienteInterfaz()
    {
        iniciarElementos();
        anadirElementos();
        
        //Thread para ejecutar el método run
        new Thread(this).start();
        
    }

    private void iniciarElementos()
    {
        setLayout(new BorderLayout());

        soporteTexto = new JPanel(new BorderLayout());
        soporteEnvio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        soporteDatos = new JPanel(new GridLayout(1, 3, 5, 15));

        info = new JLabel("-Chat-", JLabel.CENTER);
        enviar = new JButton("Enviar");
        areaTexto = new JTextArea();

        texto = new JTextField(28);

        nombreUser = new JTextField("User: 1");
        nombreUser.setEditable(false);

        ip = new JTextField("192.168.0.14");

        ip.addFocusListener(new FocusListener()
        {

            @Override
            public void focusGained(FocusEvent e)
            {
                ip.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                ip.setText(ip.getText());
            }

        });

        accionBoton();

    }

    private void anadirElementos()
    {
        //Se añaden todos los elementos Swing a sus correspondientes JPanels
        soporteDatos.add(nombreUser); 
        soporteDatos.add(info);
        soporteDatos.add(ip);
        
        areaTexto.setEditable(false);
        soporteTexto.add(areaTexto, BorderLayout.CENTER);

        soporteEnvio.add(texto);
        soporteEnvio.add(enviar);

        add(soporteDatos, BorderLayout.NORTH);
        add(soporteTexto, BorderLayout.CENTER);
        add(soporteEnvio, BorderLayout.SOUTH);
    }

    private void accionBoton()
    {
        enviar.addActionListener((ActionEvent e) ->
        {
            try
            {
                //Socket para establecer una conexión con el servidor principal
                Socket miSocket = new Socket("192.168.0.2", 9999);

                //Se crea un objeto datos
                Mensaje datos = new Mensaje();

                //Se establecen los valores al objeto datos
                datos.setNick(nombreUser.getText());
                datos.setIp(ip.getText());
                datos.setMensaje(texto.getText());
                
                //Flujo de salida para los datos(objeto datos)
                ObjectOutputStream salidaPaquete = new ObjectOutputStream(miSocket.getOutputStream());
                
                //Se envían los datos
                salidaPaquete.writeObject(datos);
                
                //Se cierra el socket para evitar "fugas de recursos"
                miSocket.close();

            } catch (IOException e1)
            {
                //Se imprime en consola un mensaje en consola en caso de que las cosas salgan mal
                System.out.println(e1.getMessage());
            }

        });
    }

    @Override
    public void run()
    {
        try
        {
            //El cliente también actuará como un servidor(recibirá los datos del servidor principal)
            ServerSocket server = new ServerSocket(10000);
            
            //Socket para establecer la conexión con el servidor principal
            Socket cliente;
            
            //El mensaje recibido
            Mensaje paqueteRecibido;
            
            while(true)
            {
                //Espera hasta aceptar los datos del servidor principal
                cliente = server.accept();
                
                //Entrada del paquete enviado desde el servidor principal
                ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
                
                //Leemos el objeto que llega desde el servidor principal
                paqueteRecibido = (Mensaje) in.readObject();
                
                //Mostramos el mensaje en el área de texto
                areaTexto.append(paqueteRecibido.getNick() + "says: " + paqueteRecibido.getMensaje());
                
            }
            
        }catch(IOException | ClassNotFoundException e)
        {
            //Se imprime un mensaje en consola en caso de que las cosas salgan mal
            System.out.println(e.getMessage());
            
        }
    }

}
