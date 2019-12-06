package calculadorabasica;

import javax.swing.JFrame;

public final class CalculadoraBásica extends JFrame
{

    private static final long serialVersionUID = 1L;

    private Display lamina;

    public CalculadoraBásica()
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

}
