package disposiciones;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Lamina extends JPanel
{

    private static final long serialVersionUID = 1L;

    JButton verde;
    JButton naranja;
    JButton azul;

    public Lamina()
    {

        setLayout(new BorderLayout(80, 80));

        verde = new JButton(new AccionColor("Verde", Color.green));
        //rosado = new JButton(new AccionColor("Rosado", Color.pink));
        naranja = new JButton(new AccionColor("Naranja", Color.orange));
        azul = new JButton(new AccionColor("Azul", Color.blue));

        add(verde, BorderLayout.WEST);
        add(naranja, BorderLayout.EAST);
        add(azul, BorderLayout.CENTER);

    }

    private class AccionColor extends AbstractAction
    {

        private static final long serialVersionUID = 1L;

        public AccionColor(String titulo, Color color)
        {
            putValue(Action.NAME, titulo);
            putValue(Action.SHORT_DESCRIPTION, "Cambia el fondo a " + titulo);
            putValue("Color", color);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            Color color = (Color) getValue("Color");

            setBackground(color);

        }
    }
}

class Lamina2 extends JPanel
{

    private static final long serialVersionUID = 1L;

    JButton rosado;

    public Lamina2()
    {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        rosado = new JButton("Rosado");

        add(rosado);

    }
}
