package pruebabotones;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class LaminaBotones extends JPanel
{

    private static final long serialVersionUID = 1L;

    private JButton[] botones =
    {
        new JButton(new AccionBotones("Azul", 0, Color.blue)), new JButton(new AccionBotones("Amarillo", 1, Color.yellow)), new JButton(new AccionBotones("Verde", 2, Color.green))
    };

    JPanel abajo;

    private boolean cambio = false;

    public LaminaBotones()
    {
        setLayout(new BorderLayout());

        abajo = new JPanel();

        abajo.setLayout(new FlowLayout(FlowLayout.CENTER));

        for (JButton boton : botones)
        {
            abajo.add(boton);
        }

        add(abajo, BorderLayout.SOUTH);

    }

    private class AccionBotones extends AbstractAction
    {

        private static final long serialVersionUID = 1L;

        public AccionBotones(String titulo, int i, Color color)
        {
            putValue(Action.NAME, titulo);
            putValue(Action.SHORT_DESCRIPTION, "Pondr� el fondo de color " + titulo);
            putValue("color", color);
            putValue("titulo", titulo);
            putValue("i", i);

        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            for (int i = 0; i < botones.length; i++)
            {
                if (i != (int) getValue("i"))
                {
                    botones[i].setEnabled(cambio);
                }
            }

            if (!cambio)
            {
                setBackground((Color) getValue("color"));

                botones[(int) getValue("i")].setText("Negro");

            } else
            {
                setBackground(Color.black);

                botones[(int) getValue("i")].setText((String) getValue("titulo"));
            }

            cambio = !cambio;

        }
    }
}
