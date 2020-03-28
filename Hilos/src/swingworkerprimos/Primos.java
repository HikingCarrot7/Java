package swingworkerprimos;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import static java.lang.Integer.parseInt;
import java.util.InputMismatchException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class Primos extends JPanel
{

    private static final long serialVersionUID = 1L;

    private JTextField primoMayor;
    private JButton obtenerPrimos, cancelar;
    private JTextArea mostrarPrimos;
    private JProgressBar progreso;
    private JLabel estado;
    private CalculadoraPrimos calculadora;
    private JPanel norte, centro, sur;
    private JScrollPane soporte;

    public Primos()
    {
        setLayout(new BorderLayout());

        iniciarElementos();

        anadirElementos();

        add(norte, NORTH);
        add(centro, CENTER);
        add(sur, SOUTH);
    }

    private void iniciarElementos()
    {
        norte = new JPanel();
        centro = new JPanel();
        sur = new JPanel();

        primoMayor = new JTextField();
        obtenerPrimos = new JButton("Calcular!");
        cancelar = new JButton("Cancelar!");
        mostrarPrimos = new JTextArea();
        progreso = new JProgressBar();
        estado = new JLabel();
        soporte = new JScrollPane(mostrarPrimos, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);

        accionBotones();

    }

    private void accionBotones()
    {
        obtenerPrimos.addActionListener((ActionEvent e) ->
        {
            int numero = 0;
            estado.setText("");
            mostrarPrimos.setText("");
            progreso.setValue(0);

            try
            {
                numero = parseInt(primoMayor.getText());

            } catch (NumberFormatException | InputMismatchException ex)
            {
                estado.setText("Inserte un n�mero v�lido");

                return;
            }

            calculadora = new CalculadoraPrimos(numero, mostrarPrimos, obtenerPrimos, cancelar, estado);

            calculadora.addPropertyChangeListener((PropertyChangeEvent p) ->
            {
                if (p.getPropertyName().equals("progress"))
                    progreso.setValue((Integer) p.getNewValue());
            });

            obtenerPrimos.setEnabled(false);
            cancelar.setEnabled(true);

            calculadora.execute();

        });

        cancelar.addActionListener((ActionEvent e) ->
        {
            calculadora.detenerCalculo();
        });
    }

    private void anadirElementos()
    {
        norte.add(new JLabel("Calcular primos menores de: "));
        primoMayor.setColumns(5);
        norte.add(primoMayor);
        norte.add(obtenerPrimos);

        centro.setLayout(new BorderLayout());
        mostrarPrimos.setEditable(false);
        soporte.setWheelScrollingEnabled(true);
        centro.add(soporte, CENTER);

        sur.setLayout(new GridLayout(1, 3, 10, 10));
        cancelar.setEnabled(false);
        sur.add(cancelar);
        progreso.setStringPainted(true);
        sur.add(progreso);
        sur.add(estado);

    }

}
