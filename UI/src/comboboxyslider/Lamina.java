package comboboxyslider;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Lamina extends JPanel
{

    private static final long serialVersionUID = 1L;

    private JLabel texto;

    private String[] elementos =
    {
        "Serif", "Arial", "Onyx", "MonoSpaced", "Vani", "Vrinda"
    };

    private JComboBox desplegable;

    private JSlider tamanoTexto;

    private JPanel deslizante;

    private Timer timer;

    private int razon = 1, thicks = 0;

    private SecureRandom rand;

    String fuente = "serif";

    public Lamina()
    {
        anadirDatos();

        timer = new Timer(30, new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (tamanoTexto.getValue() <= 10 || tamanoTexto.getValue() >= 50)
                {
                    razon *= -1;
                }

                tamanoTexto.setValue(tamanoTexto.getValue() + razon);
            }

        });

        rand = new SecureRandom();

        timer.start();
    }

    public void anadirDatos()
    {
        setLayout(new BorderLayout());

        deslizante = new JPanel();

        texto = new JLabel("Sample text", JLabel.CENTER);
        texto.setFont(new Font("serif", Font.PLAIN, 25));

        desplegable = new JComboBox(elementos);
        desplegable.setEditable(true);
        desplegable.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                texto.setFont(new Font((String) desplegable.getSelectedItem(), Font.PLAIN, 25));
            }

        });

        tamanoTexto = new JSlider(10, 50, 25);
        tamanoTexto.setFont(new Font("serif", Font.PLAIN, 10));

        tamanoTexto.setMajorTickSpacing(10);
        tamanoTexto.setMinorTickSpacing(2);

        tamanoTexto.setSnapToTicks(false);
        tamanoTexto.setPaintTicks(true);
        tamanoTexto.setPaintLabels(true);

        tamanoTexto.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {

                if (thicks++ % 30 == 0)
                {
                    fuente = elementos[rand.nextInt(6)];
                }

                texto.setFont(new Font(fuente, Font.PLAIN, tamanoTexto.getValue()));
            }

        });

        deslizante.add(tamanoTexto);

        add(desplegable, BorderLayout.NORTH);
        add(texto, BorderLayout.CENTER);
        add(deslizante, BorderLayout.SOUTH);
    }
}
