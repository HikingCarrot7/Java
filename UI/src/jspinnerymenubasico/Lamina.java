package jspinnerymenubasico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public final class Lamina extends JPanel
{

    private static final long serialVersionUID = 1L;

    private JSpinner spinner;

    private JMenuBar menubarra;

    private JMenu opcion;

    private JButton boton;

    private JMenu[] menu =
    {
        new JMenu("Archivo"), new JMenu("Edicion"), new JMenu("Herramientas")
    };

    private JMenuItem[][] opciones =
    {
        {
            new JMenuItem("Guardar"), new JMenuItem("Guardar como...")
        },
        {
            new JMenuItem("Editar")
        },
        {
            new JMenuItem("Copiar"), new JMenuItem("Pegar"), new JMenuItem("Cortar")
        }
    };

    public Lamina()
    {

        setLayout(new BorderLayout());

        anadirSpinner();

        anadirMenu();

        add(menubarra, BorderLayout.NORTH);
        add(spinner, BorderLayout.SOUTH);
        add(boton, BorderLayout.EAST);

    }

    public void anadirSpinner()
    {
        spinner = new JSpinner(new SpinnerNumberModel(500, 300, 1000, 100)
        {

            private static final long serialVersionUID = 1L;

            @Override
            public Object getNextValue()
            {
                return super.getPreviousValue();
            }

            @Override
            public Object getPreviousValue()
            {
                return super.getNextValue();
            }

        });

        spinner.setPreferredSize(new Dimension(50, 20));
    }

    public void anadirMenu()
    {

        menubarra = new JMenuBar();

        opcion = new JMenu("Opciones");

        boton = new JButton();

        boton.addActionListener((ActionEvent e) ->
        {
            setBackground(Color.green);

        });

        opcion.add(new JMenuItem("Opci�n 1"));
        opcion.add(new JMenuItem("Opci�n 2"));

        for (int i = 0; i < menu.length; i++)
        {
            menubarra.add(menu[i]);

            anadirItems(menu[i], opciones[i]);

        }

        menu[2].addSeparator();
        menu[2].add(opcion);

    }

    public void anadirItems(JMenu menu, JMenuItem[] items)
    {
        for (JMenuItem i : items)
            menu.add(i);
    }

}
