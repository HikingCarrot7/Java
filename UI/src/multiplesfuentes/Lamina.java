package multiplesfuentes;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Lamina extends JPanel
{

    private static final long serialVersionUID = 1L;

    public Lamina()
    {
        AccionColor accionYellow = new AccionColor("Yellow", new ImageIcon("src/multiplesfuentes/yellow.jpg"), Color.yellow);
        AccionColor accionBlue = new AccionColor("Blue", new ImageIcon("src/multiplesfuentes/blue.jpg"), Color.blue);
        AccionColor accionRed = new AccionColor("Red", new ImageIcon("src/multiplesfuentes/red.jpg"), Color.red);

        add(new JButton(accionYellow));
        add(new JButton(accionBlue));
        add(new JButton(accionRed));

        //Crear un mapa de entrda
        //InputMap mapaEntrada = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        //Crear un v�nculo entre la combinaci�n de teclas y una llave (nombre) 
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ctrl A"), "Yellow");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ctrl S"), "Blue");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ctrl D"), "Red");

        //Crear un actionmap
        //ActionMap mapaAccion = getActionMap();
        //Crear un v�nculo entre la combinaci�n de teclas y la accion (poner de colores el fondo)
        getActionMap().put("Yellow", accionYellow);
        getActionMap().put("Blue", accionBlue);
        getActionMap().put("Red", accionRed);

    }

    private class AccionColor extends AbstractAction
    {

        private static final long serialVersionUID = 1L;

        public AccionColor(String nombre, Icon icono, Color color)
        {

            putValue(Action.NAME, nombre);
            putValue(Action.SMALL_ICON, icono);
            putValue(Action.SHORT_DESCRIPTION, "Poner la l�mina de color " + nombre);
            putValue("Color", color);

        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            Color c = (Color) getValue("Color");

            setBackground(c);

        }
    }
}
