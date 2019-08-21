package paneles;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class MiFrame extends JFrame
{

    private static final long serialVersionUID = 1L;

    public MiFrame()
    {

        Toolkit miPantalla = Toolkit.getDefaultToolkit();

        Dimension tamanoPantalla = miPantalla.getScreenSize();

        setSize(tamanoPantalla.width / 2, tamanoPantalla.height / 2);

        setLocation(tamanoPantalla.width / 4, tamanoPantalla.height / 4);

        setVisible(true);

        setTitle("Nuestro primer texto");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Lamina miLamina = new Lamina();

        add(miLamina);

    }

}
