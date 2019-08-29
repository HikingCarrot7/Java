package administracion;

import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Generales
{

    public static boolean intentarValidarEntero(JTextField campo, JLabel texto)
    {
        int entero;

        try
        {
            entero = Integer.parseInt(campo.getText());

            if (!(entero >= 0 && entero <= 100))
            {
                texto.setText("*N�mero muy grande");

                return false;

            } else
            {
                texto.setText("");

            }

        } catch (Exception E)
        {
            texto.setText("*Dato no v�lido");

            return false;
        }

        return true;

    }

    public static boolean intentarValidarDouble(JTextField campo, JLabel texto, int tope)
    {
        double doublex;

        try
        {
            doublex = Double.parseDouble(campo.getText());

            if (!(doublex >= 0 && doublex <= tope))
            {
                texto.setText("*N�mero muy grande");

                return false;

            } else
            {
                texto.setText("");

            }

        } catch (Exception E)
        {
            texto.setText("*Dato no v�lido");

            return false;
        }

        return true;

    }

    public static boolean validarDatos(JTextField texto, JLabel campo)
    {
        if (texto.getText().trim().contentEquals(" "))
        {
            campo.setText("*Campo vac�o");

            return false;

        } else
        {
            return true;
        }

    }

    public static String pasarAString(char[] contra)
    {
        String pass = "";

        for (int i = 0; i < contra.length; i++)
        {
            pass += contra[i];
        }

        return pass;
    }

    public static void actualizarTextoMaestros()
    {
        LaminaAdministrador.mostrarMaestros.setText("");

        LaminaAdministrador.listaMaestros.removeAllItems();

        for (Maestro M : LaminaMaestro.maestrosInstanciados)
        {
            LaminaAdministrador.mostrarMaestros.append(M.mostrarDatos());

            LaminaAdministrador.listaMaestros.addItem(M.toString());
        }
    }

    public static void actualizarTextoAlumnos()
    {
        LaminaMaestro.mostrarAlumnos.setText("");

        LaminaMaestro.alumnosInstancias.get(Login.indiceMaestro).stream().forEach((A) ->
        {
            LaminaMaestro.mostrarAlumnos.append(A.mostrarDatos());
        });
    }

    public static int alerta(String info, String titulo)
    {
        Toolkit.getDefaultToolkit().beep();

        return JOptionPane.showConfirmDialog(null, info, titulo, JOptionPane.WARNING_MESSAGE);
    }

}
