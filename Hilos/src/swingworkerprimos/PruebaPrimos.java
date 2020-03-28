package swingworkerprimos;

import javax.swing.JFrame;

public class PruebaPrimos extends JFrame
{

    private static final long serialVersionUID = 1L;

    private Primos primos;

    public PruebaPrimos()
    {
        primos = new Primos();

        setBounds(0, 0, 500, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Primos!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(primos);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new PruebaPrimos();
    }
}
