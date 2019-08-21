package battleshipmejorado;

import javax.swing.*;

public class MiFrame extends JFrame
{

    private static final long serialVersionUID = 1L;

    Lamina lamina;

    public MiFrame()
    {
        lamina = new Lamina();

        setBounds(0, 0, 1000, 1000);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("BattleShip mejorado");
        add(lamina);
        addMouseListener(lamina);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        JOptionPane.showMessageDialog(null, "Bienvenido a battleship mejorado!", "Hola!", JOptionPane.DEFAULT_OPTION, new ImageIcon("src/multiplesfuentes/red.jpg"));

        new MiFrame();

    }
}
