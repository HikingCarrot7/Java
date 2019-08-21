package botonesradio;

import javax.swing.*;

public class MiFrame extends JFrame
{

    private static final long serialVersionUID = 1L;

    Lamina lamina;

    public MiFrame()
    {
        lamina = new Lamina();

        setBounds(0, 0, 500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        add(lamina);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new MiFrame();

    }

}
