package RectAzar;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class PruebaRectangulos extends JFrame
{

    private static final long serialVersionUID = 1L;

    public PruebaRectangulos()
    {
        LaminaRect miLamina1 = new LaminaRect();

        Toolkit miPantalla = Toolkit.getDefaultToolkit();

        Dimension tamanoPantalla = miPantalla.getScreenSize();

        setBounds(tamanoPantalla.width / 4, tamanoPantalla.height / 4, tamanoPantalla.width / 2, tamanoPantalla.height / 2);
        setVisible(true);
        add(miLamina1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        new PruebaRectangulos();
    }
}
