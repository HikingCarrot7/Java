package multiplesoyentes;

import javax.swing.JFrame;

public class MiFrame extends JFrame
{

    private static final long serialVersionUID = 1L;

    Lamina lamina;

    public MiFrame()
    {
        lamina = new Lamina();

        this.setBounds(0, 0, 500, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        this.add(lamina);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args)
    {

        new MiFrame();

    }

}
