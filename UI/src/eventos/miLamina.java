package eventos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class miLamina extends JPanel
{

    private static final long serialVersionUID = 1L;

    private JButton azul = new JButton("Blue");
    private JButton amarillo = new JButton("Yellow");
    private JButton rojo = new JButton("Red");

    private JTextField cuadro1;
    private JTextField cuadro2;

    public miLamina()
    {
        add(azul);
        add(amarillo);
        add(rojo);

        azul.addActionListener(new ColorFondo(Color.blue));

        amarillo.addActionListener(new ColorFondo(Color.yellow));

        rojo.addActionListener(new ColorFondo(Color.red));

        setBackground(Color.cyan);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        setLayout(null);

        cuadro1 = new JTextField();
        cuadro2 = new JTextField();

        cuadro1.setBounds(50, 50, 150, 20);
        cuadro2.setBounds(50, 100, 150, 20);

        add(cuadro1);
        add(cuadro2);

        cuadro1.addFocusListener(new LanzaFocos());

    }

    private class LanzaFocos implements FocusListener
    {

        @Override
        public void focusGained(FocusEvent e)
        {

            cuadro1.setText("");

        }

        @Override
        public void focusLost(FocusEvent e)
        {
            String text = cuadro1.getText();

            if (text.contentEquals(""))
            {
                System.out.println("No puedes dejar los campos vac�os");
            }

        }
    }

    private class ColorFondo implements ActionListener
    {

        private Color c;

        public ColorFondo(Color c)
        {
            this.c = c;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            setBackground(c);

        }
    }
}

class EventoMouse implements MouseListener, MouseMotionListener
{

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("Coordinates in X: " + e.getX() + " Y: " + e.getY());
        System.out.println("-----------------------------------------------");
        System.out.println(e.getClickCount());

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK)
        {
            System.out.println("You pressed the left button");

        } else if (e.getModifiersEx() == MouseEvent.BUTTON3_DOWN_MASK)
        {
            System.out.println("Let me guess... I think you just pressed the right button");

        } else if (e.getModifiersEx() == MouseEvent.BUTTON2_DOWN_MASK)
        {
            System.out.println("Congratulations, your wheel button works properly");

        }

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {

        if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK)
        {
            System.out.println("You are moving somethig, invisible btw");
        }

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
		//System.out.println("Coordinates in X: " + e.getX() + " Y: " + e.getY());

    }

    @Override
    public void mouseEntered(MouseEvent arg0)
    {
		// TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0)
    {
		// TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0)
    {
		// TODO Auto-generated method stub

    }

}
