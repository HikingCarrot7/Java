package paneles;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Lamina extends JPanel
{

    private static final long serialVersionUID = 1L;

    @Override
    public void paintComponent(Graphics g)
    {

        setBackground(new Color(234, 56, 67));

        super.paintComponent(g);

        g.drawString("A�n no podemos hacer mucho", 50, 50);

    }
}
