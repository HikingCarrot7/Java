package multiplesoyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Lamina extends JPanel
{

    private static final long serialVersionUID = 1L;

    JButton nuevo;
    JButton cerrar;

    public Lamina()
    {
        nuevo = new JButton("Nueva ventana");
        cerrar = new JButton("Cerrar todas las ventanas");

        nuevo.addActionListener(new CrearVentanas());

        add(nuevo);
        add(cerrar);

    }

    private class CrearVentanas implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            new Ventanas(cerrar);
        }
    }

    private static class Ventanas extends JFrame
    {

        private static final long serialVersionUID = 1L;

        private static int i = 1;

        public Ventanas(JButton botonCerrar)
        {

            setTitle("Ventana " + i++);
            setBounds(40 * i, 40 * i, 400, 400);
            setVisible(true);
            setResizable(false);

            botonCerrar.addActionListener(new CerrarVentanas());
            //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        }

        private class CerrarVentanas implements ActionListener
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();

                i = 1;

            }
        }
    }
}
