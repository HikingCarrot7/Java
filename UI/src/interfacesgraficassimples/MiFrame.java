package interfacesgraficassimples;

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
        setIconImage(miPantalla.getImage("src/interfacesgraficassimples/icono.jpg"));

        int altura = tamanoPantalla.height;
        int ancho = tamanoPantalla.width;

        setSize(ancho / 2, altura / 2);
        setTitle("Hola!");
        setResizable(false);
        setLocation(ancho / 4, altura / 4);
        setVisible(true);
        //setExtendedState(Frame.MAXIMIZED_BOTH);
    }

}
