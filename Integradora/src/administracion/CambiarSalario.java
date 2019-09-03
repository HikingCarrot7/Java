package administracion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;

public class CambiarSalario extends JFrame
{

    private static final long serialVersionUID = 1L;

    private final Cambiarsalario cambiarsalario;

    public CambiarSalario()
    {
        cambiarsalario = new Cambiarsalario();

        setBounds(0, 0, 350, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Cambiar sueldo a un maestro");
        add(cambiarsalario);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private final class Cambiarsalario extends JPanel
    {

        private static final long serialVersionUID = 1L;

        private JSlider manipular;
        private JLabel infosalario, vaciosalario;
        private JTextField salario;
        private JButton aceptar;
        private JPanel info, boton;

        public Cambiarsalario()
        {

            iniciarElementos();
            anadirElementos();

        }

        private class AccionBoton extends AbstractAction
        {

            private static final long serialVersionUID = 1L;

            public AccionBoton()
            {
                putValue(Action.NAME, "Capturar salario");
                putValue(Action.SHORT_DESCRIPTION, "Capturar el nuevo salario");
            }

            @Override
            public void actionPerformed(ActionEvent e)
            {
                boolean datosCorrectos;

                datosCorrectos = Generales.intentarValidarDouble(salario, vaciosalario, 50000);

                if (datosCorrectos)
                {
                    LaminaMaestro.maestrosInstanciados.get(LaminaAdministrador.listaMaestros.getSelectedIndex()).cambiarSueldo(Double.parseDouble(salario.getText()));

                    Generales.actualizarTextoMaestros();

                    dispose();
                }

            }

        }

        public void iniciarElementos()
        {

            setLayout(new BorderLayout());

            info = new JPanel();
            info.setLayout(new GridLayout(1, 3));

            boton = new JPanel();

            manipular = new JSlider(0, 50000, 1500);
            manipular.addChangeListener((ChangeEvent e) ->
            {
                salario.setText("" + manipular.getValue());
            });

            manipular.setMinorTickSpacing(2000);
            manipular.setMajorTickSpacing(10000);
            manipular.setPaintTicks(true);
            manipular.setPaintLabels(true);

            infosalario = new JLabel("Salario: ");
            vaciosalario = new JLabel(" ");
            salario = new JTextField();
            aceptar = new JButton(new AccionBoton());

        }

        public void anadirElementos()
        {
            info.add(infosalario);
            info.add(salario);
            info.add(vaciosalario);

            boton.add(aceptar);

            add(info, BorderLayout.NORTH);
            add(manipular, BorderLayout.CENTER);
            add(boton, BorderLayout.SOUTH);
        }

    }

    public static synchronized <T> String getString(T t)
    {
        return t.toString();
    }

}
