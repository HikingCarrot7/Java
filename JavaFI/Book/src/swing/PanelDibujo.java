package swing;

import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelDibujo extends JPanel
{

    @Override
    public void paintComponent(Graphics g)
    {

        super.paintComponent(g);

        int altura = getWidth();
        int anchura = getHeight();

        g.drawLine(0, 0, anchura, altura);

        g.drawLine(0, anchura, altura, 0);

    }

}
