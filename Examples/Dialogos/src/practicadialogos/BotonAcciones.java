package practicadialogos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BotonAcciones
{

    private static Object[] objetos =
    {
        "Nicol�s", new ImageIcon("src/practicadialogos/lifes.png"), new JPanel()
        {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);

                g.setColor(Color.yellow);
                g.fillRect(0, 0, getWidth(), getHeight());

            }

        }, new Date()
    };

    private static Object[][] cadenaseimagenes =
    {
        {
            "Nicol�s", "Carlos", "Iv�n"
        },
        {
            new ImageIcon("src/practicadialogos/lifes.png"), new ImageIcon("src/practicadialogos/lifes.png"), new ImageIcon("src/practicadialogos/lifes.png"),
        }
    };

    public static void AccionesBoton()
    {
        Lamina.listo.addActionListener((ActionEvent e) ->
        {
            switch (Lamina.secciones[0].toString())
            {
                case "Mensaje":
                    JOptionPane.showMessageDialog(null, obtenerMensaje(), "T�tulo", obtenerTipo(1), null);
                    break;

                case "Confirmar":
                    JOptionPane.showConfirmDialog(null, obtenerMensaje(), "T�tulo", obtenerTipo(3), obtenerTipo(1), null);
                    break;

                case "Opci�n":
                    JOptionPane.showOptionDialog(null, obtenerMensaje(), "T�tulo", obtenerTipo(3), obtenerTipo(1), null, obtenerObjetos(4), null);
                    break;

                default:
                    JOptionPane.showInputDialog(null, obtenerMensaje(), "T�tulo", obtenerTipo(1), null, obtenerObjetos(5), null);
                    break;

            }

        });

    }

    public static Object obtenerMensaje()
    {
        switch (Lamina.secciones[2].toString())
        {
            case "Cadena":
                return objetos[0];

            case "Icono":
                return objetos[1];

            case "Componente":
                return objetos[2];

            case "Otros":
                return objetos[3];

            default:
                return objetos;

        }

    }

    public static int obtenerTipo(int i)
    {
        String texto = Lamina.secciones[i].toString();

        switch (texto)
        {
            case "ERROR_MESSAGE":
            case "YES_NO_OPTION":
                return 0;

            case "INFORMATION_MESSAGE":
            case "YES_NO_CANCEL_OPTION":
                return 1;

            case "WARNING_MENSSAGE":
            case "OK_CANCEL_OPTION":
                return 2;

            case "QUESTION_MENSSAGE":
                return 3;

            default:
                return -1;

        }

    }

    public static Object[] obtenerObjetos(int i)
    {
        String texto = Lamina.secciones[i].toString();

        switch (texto)
        {
            case "String[]":
            case "Combo":
                return cadenaseimagenes[0];

            case "Icon[]":
                return cadenaseimagenes[1];

            case "Object[]":
                return objetos;

            default:
                return null;

        }

    }

}
