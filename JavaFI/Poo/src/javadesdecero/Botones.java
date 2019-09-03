package javadesdecero;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Botones extends JFrame implements ActionListener
{

    JButton boton1;

    public Botones()
    {
        setLayout(null);

        boton1 = new JButton("Cerrar");
        boton1.setBounds(300, 250, 100, 30);
        add(boton1);

        boton1.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if (e.getSource() == boton1)
            System.exit(0);
    }

    public static void main(String args[])
    {

        Botones boton = new Botones();

        boton.setBounds(0, 0, 450, 350);
        boton.setVisible(true);
        boton.setResizable(false);
        boton.setLocationRelativeTo(null);
    }

}
