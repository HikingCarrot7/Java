package firsttry;

import javax.swing.JFrame;

public class MiFrame extends JFrame
{

    private static final long serialVersionUID = 1L;

    public static void main(String[] args)
    {
        JFrame miFrame = new JFrame();
        Gameplay miLamina = new Gameplay();

        miFrame.setSize(800, 800);
        miFrame.setResizable(false);
        miFrame.setVisible(true);
        miFrame.setTitle("Cringe");
        miFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        miFrame.addKeyListener(miLamina);
        miFrame.add(miLamina);
    }

}
