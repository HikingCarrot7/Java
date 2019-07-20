package sockets.cliente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sockets.mensaje.Mensaje;

public class ClienteInterfaz extends JPanel 
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

        ip = new JTextField("Inserte la IP (¿?)");
        
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
        soporteDatos.add(nombreUser);
        soporteDatos.add(info);
        soporteDatos.add(ip);

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
                Socket miSocket = new Socket("192.168.0.9", 9999);

                Mensaje datos = new Mensaje();

                datos.setNick(nombreUser.getText());
                datos.setIp(ip.getText());
                datos.setMensaje(texto.getText());

                DataOutputStream flujo = new DataOutputStream(miSocket.getOutputStream());

                flujo.writeUTF(texto.getText());

                flujo.close();
                miSocket.close();

            } catch (IOException e1) 
            {
                System.out.println(e1.getMessage());
            }

        });
    }

}
