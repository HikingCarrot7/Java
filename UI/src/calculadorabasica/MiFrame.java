package calculadorabasica;

import javax.swing.JFrame;

public final class MiFrame extends JFrame
{

    private static final long serialVersionUID = 1L;

    private Display lamina;

    public MiFrame()
    {

        lamina = new Display();

        setBounds(0, 0, 400, 350);
        setLocationRelativeTo(null);
        setResizable(true);
        setTitle("Calculadora básica");
        add(lamina);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args)
    {
        new MiFrame();
    }

}
