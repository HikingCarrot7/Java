package dialog;

import javax.swing.JOptionPane;

public class DialogNombre
{

    public static void main(String[] args)
    {

        String nombre = JOptionPane.showInputDialog("�Cu�l es su nombre?");

        String mensaje
                = String.format("Bienvenido, %s, a la programaci�n en Java", nombre);

        JOptionPane.showMessageDialog(null, mensaje);

    }

}
