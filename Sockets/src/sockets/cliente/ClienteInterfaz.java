package sockets.cliente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import sockets.mensaje.Mensaje;

public final class ClienteInterfaz extends JPanel implements Runnable
{

    private static final long serialVersionUID = 1L;

    private JLabel info, nombreUser;
    private JButton enviar;
    private JTextField texto;
    private JTextArea areaTexto;
    private JPanel soporteTexto, soporteEnvio, soporteDatos;
    private String nick;
    private JComboBox<String> usersOnline;

    public ClienteInterfaz()
    {
        pedirNick();
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

        nombreUser = new JLabel("User: " + nick);
        nombreUser.setBorder(new TitledBorder(new EtchedBorder()));

        usersOnline = new JComboBox<>();

        accionBoton();

    }

    private void anadirElementos()
    {
        //Se añaden todos los elementos Swing a sus correspondientes JPanels
        soporteDatos.add(nombreUser);
        soporteDatos.add(info);
        soporteDatos.add(usersOnline);

        areaTexto.setEditable(false);
        soporteTexto.add(areaTexto, BorderLayout.CENTER);

        soporteEnvio.add(texto);
        soporteEnvio.add(enviar);

        add(soporteDatos, BorderLayout.NORTH);
        add(soporteTexto, BorderLayout.CENTER);
        add(soporteEnvio, BorderLayout.SOUTH);
    }

    //Validacion del nick del nuevo usuario
    public void pedirNick()
    {
        boolean validar = false;

        while (!validar)
        {
            nick = JOptionPane.showInputDialog(null, "Inserta tu nick", "New user", JOptionPane.PLAIN_MESSAGE);

            if (nick == null)
            {
                System.exit(1);
            }

            if (nick.length() <= 6)
            {
                validar = false;

                Toolkit.getDefaultToolkit().beep();

                JOptionPane.showMessageDialog(null, "User NO válido. Debe contener al menos 7 caracteres", "User inválido", JOptionPane.ERROR_MESSAGE);

            } else
            {
                validar = true;
            }
        }

    }

    private void accionBoton()
    {
        enviar.addActionListener((ActionEvent e) ->
        {
            try
            {

                //Socket para establecer una conexión con el servidor principal
                Socket miSocket = new Socket(InetAddress.getLocalHost().getHostAddress(), 9999);

                //Se crea un objeto datos
                Mensaje datos = new Mensaje();

                //Se establecen los valores al objeto datos
                datos.setNick(nombreUser.getText());
                
                //Cortamos los "()" del combo
                String[] ip = usersOnline.getSelectedItem().toString().split("-");;
                
                datos.setIp(ip[1]);
                datos.setMensaje(texto.getText());
                datos.setControl(true);

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

            while (true)
            {
                //Espera hasta aceptar los datos del servidor principal
                cliente = server.accept();

                //Entrada del paquete enviado desde el servidor principal
                ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());

                Object obj = in.readObject();

                //Revisamos si tenemos que actualizar los combo
                if (obj instanceof HashMap)
                {
                    usersOnline.removeAllItems();

                    HashMap<String, String> datos = (HashMap<String, String>) obj;

                    Iterator<String> nombres = ((HashMap<String, String>) obj).keySet().iterator();

                    for (int i = 0; i < datos.size(); i++)
                    {
                        String nombreActual = nombres.next();

                        //si se trata de nuestra misma ip no la añadimos al combo
                        if (!InetAddress.getLocalHost().getHostAddress().equals(datos.get(nombreActual)))
                        {
                            usersOnline.addItem(nombreActual + " -" + datos.get(nombreActual) + "-");
                        }

                    }

                } else
                {
                    //Leemos el objeto que llega desde el servidor principal
                    paqueteRecibido = (Mensaje) obj;

                    //Mostramos el mensaje en el área de texto
                    areaTexto.append(paqueteRecibido.getNick() + " says: " + paqueteRecibido.getMensaje() + "\n");

                }

                in.close();

            }

        } catch (IOException | ClassNotFoundException e)
        {
            //Se imprime un mensaje en consola en caso de que las cosas salgan mal
            System.out.println(e.getMessage());

        }
    }

    public String getNick()
    {
        return nick;
    }
}
