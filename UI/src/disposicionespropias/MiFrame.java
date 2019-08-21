package disposicionespropias;

import javax.swing.JFrame;

public class MiFrame extends JFrame
{

    private static final long serialVersionUID = 1L;

    private Lamina lamina;

    public MiFrame()
    {
        lamina = new Lamina();

        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Disposiciones libres");
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
