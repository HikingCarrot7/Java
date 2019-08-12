package carreradecaballos;

import javax.swing.JFrame;

public class Home extends JFrame
{

    private static final long serialVersionUID = 1L;

    private final Hipodromo hipo;

    public Home()
    {
        hipo = new Hipodromo();

        setBounds(0, 0, 500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Carrera de caballos");
        add(hipo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new Home();

    }

}
