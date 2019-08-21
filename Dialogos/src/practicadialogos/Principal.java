package practicadialogos;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Principal extends JFrame
{

    private static final long serialVersionUID = 1L;

    private Lamina lamina;

    public Principal()
    {
        lamina = new Lamina();

        setBounds(0, 0, 800, 600);
        setLocationRelativeTo(null);
        setResizable(true);
        setMinimumSize(new Dimension(600, 500));
        setTitle("Di�logos");
        add(lamina);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new Principal();
    }
}
