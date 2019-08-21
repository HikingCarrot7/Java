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
            if (Lamina.secciones[0].toString().contentEquals("Mensaje"))
            {
                JOptionPane.showMessageDialog(null, obtenerMensaje(), "T�tulo", obtenerTipo(1), null);
            } else if (Lamina.secciones[0].toString().contentEquals("Confirmar"))
            {
                JOptionPane.showConfirmDialog(null, obtenerMensaje(), "T�tulo", obtenerTipo(3), obtenerTipo(1), null);
            } else if (Lamina.secciones[0].toString().contentEquals("Opci�n"))
            {
                JOptionPane.showOptionDialog(null, obtenerMensaje(), "T�tulo", obtenerTipo(3), obtenerTipo(1), null, obtenerObjetos(4), null);
            } else
            {
                JOptionPane.showInputDialog(null, obtenerMensaje(), "T�tulo", obtenerTipo(1), null, obtenerObjetos(5), null);
            }
        });
    }

    public static Object obtenerMensaje()
    {
        if (Lamina.secciones[2].toString().contentEquals("Cadena"))
        {
            return objetos[0];
        } else if (Lamina.secciones[2].toString().contentEquals("Icono"))
        {
            return objetos[1];
        } else if (Lamina.secciones[2].toString().contentEquals("Componente"))
        {
            return objetos[2];
        } else if (Lamina.secciones[2].toString().contentEquals("Otros"))
        {
            return objetos[3];
        } else
        {
            return objetos;
        }
    }

    public static int obtenerTipo(int i)
    {
        String texto = Lamina.secciones[i].toString();

        if (texto.contentEquals("ERROR_MESSAGE") || texto.contentEquals("YES_NO_OPTION"))
        {
            return 0;
        } else if (texto.contentEquals("INFORMATION_MESSAGE") || texto.contentEquals("YES_NO_CANCEL_OPTION"))
        {
            return 1;
        } else if (texto.contentEquals("WARNING_MENSSAGE") || texto.contentEquals("OK_CANCEL_OPTION"))
        {
            return 2;
        } else if (texto.contentEquals("QUESTION_MENSSAGE"))
        {
            return 3;
        } else
        {
            return -1;
        }
    }

    public static Object[] obtenerObjetos(int i)
    {
        String texto = Lamina.secciones[i].toString();

        if (texto.contentEquals("String[]") || texto.contentEquals("Combo"))
        {
            return cadenaseimagenes[0];
        } else if (texto.contentEquals("Icon[]"))
        {
            return cadenaseimagenes[1];
        } else if (texto.contentEquals("Object[]"))
        {
            return objetos;
        } else
        {
            return null;
        }
    }
}
