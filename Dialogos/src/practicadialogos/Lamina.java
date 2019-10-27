package practicadialogos;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Lamina extends JPanel
{

    private static final long serialVersionUID = 1L;

    private JPanel mensajes, boton;

    protected static JButton listo;

    protected static LaminaSecciones[] secciones;

    private String[][] opciones =
    {

        {
            "Tipo", "Mensaje", "Confirmar", "Opci�n", "Entrada"
        },
        {
            "Tipo mensaje", "ERROR_MESSAGE", "INFORMATION_MESSAGE", "WARNING_MENSSAGE", "QUESTION_MENSSAGE", "PLAIN_MENSSAGE"
        },
        {
            "Mensaje", "Cadena", "Icono", "Componente", "Otros", "Object[]"
        },
        {
            "Confirmar", "DEFAUT_OPTION", "YES_NO_OPTION", "YES_NO_CANCEL_OPTION", "OK_CANCEL_OPTION"
        },
        {
            "Opci�n", "String[]", "Icon[]", "Object[]"
        },
        {
            "Entrada", "Campo de texto", "Combo"
        }

    };

    public Lamina()
    {
        setLayout(new BorderLayout());

        anadirComponentes();

        anadirMensajes();

        add(mensajes, BorderLayout.CENTER);
        add(boton, BorderLayout.SOUTH);

    }

    public void anadirMensajes()
    {
        for (int i = 0; i < secciones.length; i++)
        {
            secciones[i] = new LaminaSecciones(opciones[i][0], opciones[i]);

            mensajes.add(secciones[i]);

        }

    }

    public void anadirComponentes()
    {
        secciones = new LaminaSecciones[6];

        listo = new JButton("Listo");

        BotonAcciones.AccionesBoton();

        mensajes = new JPanel();
        mensajes.setLayout(new GridLayout(2, 3));

        boton = new JPanel();

        boton.add(listo);
    }

}
