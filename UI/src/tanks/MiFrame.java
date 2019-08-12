package tanks;

import javax.swing.JFrame;

public class MiFrame extends JFrame
{

    private static final long serialVersionUID = 1L;

    public static void main(String[] args)
    {

        JFrame miFrame = new JFrame();
        Tanks miLamina = new Tanks();

        miFrame.setSize(1000, 1000);
        miFrame.setResizable(false);
        miFrame.setVisible(true);
        miFrame.setTitle("Crazy Tanks");
        miFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        miFrame.addKeyListener(miLamina);
        miFrame.add(miLamina);
    }
}
