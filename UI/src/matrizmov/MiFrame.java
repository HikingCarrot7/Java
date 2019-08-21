package matrizmov;

import javax.swing.JFrame;

public class MiFrame extends JFrame
{

    private static final long serialVersionUID = 1L;

    public static void main(String[] args)
    {
        MiFrame miFrame = new MiFrame();

        Lamina miLamina = new Lamina();

        miFrame.setBounds(0, 0, 1000, 1000);
        miFrame.setVisible(true);
        miFrame.setResizable(false);
        miFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        miFrame.add(miLamina);
        miFrame.addMouseListener(miLamina);
    }
}
