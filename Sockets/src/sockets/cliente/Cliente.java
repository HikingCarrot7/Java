package sockets.cliente;

import javax.swing.JFrame;
import sockets.mensaje.EnvioOnline;

public class Cliente extends JFrame
{

    private static final long serialVersionUID = 1L;

    private final ClienteInterfaz interfaz;

    public Cliente()
    {
        interfaz = new ClienteInterfaz();

        setBounds(0, 0, 400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setAlwaysOnTop(true);
        setTitle("Cliente");
        add(interfaz);
        addWindowListener(new EnvioOnline(interfaz));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new Cliente();
    }

}
