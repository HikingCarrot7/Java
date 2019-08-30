package ruletadelamor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.security.SecureRandom;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Lamina extends JPanel
{

    private static final long serialVersionUID = 1L;

    private Rotacion prueba;

    private JButton[] botones =
    {
        new JButton(new AccionBotones("Girar", "Gira la rueda", 0, 5)), new JButton(new AccionBotones("Reiniciar", "Reinicia la rueda", 1, 0))
    };

    private SecureRandom rand;

    public Lamina()
    {
        rand = new SecureRandom();

        prueba = new Rotacion();

        botones[1].setEnabled(false);

        for (JButton B : botones)
            add(B);

        add(prueba);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        setBackground(Color.green);

        prueba.setBounds(100, 100, 300, 300);
    }

    private class AccionBotones extends AbstractAction
    {

        private static final long serialVersionUID = 1L;

        public AccionBotones(String titulo, String descripcion, int i, int velocidad)
        {
            putValue(Action.NAME, titulo);
            putValue(Action.SHORT_DESCRIPTION, descripcion);
            putValue("i", i);
            putValue("velocidad", velocidad);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            for (int i = 0; i < botones.length; i++)
                botones[i].setEnabled(i != (int) getValue("i") ? true : false);

            if (!botones[0].isEnabled())
            {
                prueba.thicks = 0;
                prueba.tope = 5 + rand.nextInt(20);
                prueba.velocidad = 20 + rand.nextInt(25);

            } else
            {
                prueba.thicks = 0;
                prueba.velocidad = 0;
                prueba.rotacion = 180;
            }
        }
    }
}
