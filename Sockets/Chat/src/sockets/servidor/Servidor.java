package sockets.servidor;

import javax.swing.JFrame;

public class Servidor extends JFrame
{

    private static final long serialVersionUID = 1L;

    private ServidorInterfaz interfaz;

    public Servidor()
    {
        interfaz = new ServidorInterfaz();

        setBounds(0, 0, 400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setAlwaysOnTop(true);
        setTitle("Servidor");
        add(interfaz);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new Servidor();
    }

}
