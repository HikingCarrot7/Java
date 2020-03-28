package swingworker;

import javax.swing.JFrame;

public class PruebaFibonacci extends JFrame
{

    private static final long serialVersionUID = 1L;

    private NumerosFibonacci numerosFibonacci;

    public PruebaFibonacci()
    {
        numerosFibonacci = new NumerosFibonacci();

        setBounds(0, 0, 275, 120);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("N�meros de fibonacci");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(numerosFibonacci);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new PruebaFibonacci();
    }

}
