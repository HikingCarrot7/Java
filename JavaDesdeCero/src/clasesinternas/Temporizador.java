package clasesinternas;

import javax.swing.JOptionPane;

public class Temporizador
{

    public static void main(String[] args)
    {
        new Reloj().enMarcha(1000, false);

        JOptionPane.showMessageDialog(null, "Pulsa aceptar para detener");

        System.exit(0);

    }
}
