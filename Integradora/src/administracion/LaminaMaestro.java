package administracion;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LaminaMaestro extends JPanel
{

    private static final long serialVersionUID = 1L;

    private JScrollPane scrollAlumnos;

    private Login login;

    protected static JTextArea mostrarAlumnos;

    protected static JButton anadirAlumno, borrarAlumno, califAlumno, administrador, maestro;

    protected static JComboBox<String> listaAlumnos;

    protected static ArrayList<ArrayList<Alumno>> alumnosInstancias;

    protected static ArrayList<Maestro> maestrosInstanciados;

    protected static JLabel infoMaestro;

    public LaminaMaestro()
    {

        iniciarElementos();

        ponerElementos();

        anadirElementos();

    }

    private class AccionBotones extends AbstractAction
    {

        private static final long serialVersionUID = 1L;

        public AccionBotones(String titulo, String descripcion)
        {
            putValue(Action.NAME, titulo);
            putValue(Action.SHORT_DESCRIPTION, descripcion);

        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == anadirAlumno)
            {
                new CapturarAlumno();

            } else if (e.getSource() == borrarAlumno)
            {
                int opcion = Generales.alerta("Se perder�n los datos del alumno. �Est�s seguro?", "!Se perder�n datos!");

                if (opcion == 0)
                {
                    alumnosInstancias.get(Login.indiceMaestro).remove(listaAlumnos.getSelectedIndex());

                    LeerDatos.actualizarListaAlumnos();

                    Generales.actualizarTextoAlumnos();

                    listaAlumnos.removeItemAt(listaAlumnos.getSelectedIndex());

                    borrarAlumno.setEnabled(listaAlumnos.getItemCount() < 1 ? false : true);

                    califAlumno.setEnabled(listaAlumnos.getItemCount() < 1 ? false : true);
                }

            } else if (e.getSource() == califAlumno)
            {
                String calif = ".";

                if (listaAlumnos.getItemCount() > 0)
                {

                    try
                    {
                        while (true)
                        {
                            calif = JOptionPane.showInputDialog(null, "Inserta la nueva calificaci�n para: " + listaAlumnos.getSelectedItem(), "Nueva calificaci�n", JOptionPane.DEFAULT_OPTION).trim();

                            if (Double.parseDouble(calif) >= 0 && Double.parseDouble(calif) <= 100)
                            {

                                alumnosInstancias.get(Login.indiceMaestro).get(listaAlumnos.getSelectedIndex()).cambiarCalif(Double.parseDouble(calif));

                                Generales.actualizarTextoAlumnos();

                                break;

                            } else
                            {
                                error("La calificaci�n es demasiado grande!");

                            }
                        }

                    } catch (Exception E)
                    {
                        error("No insertaste un dato correcto!");

                    }

                }

            } else if (e.getSource() == maestro)
            {
                updatePantalla("Iniciar sesi�n como maestro", "Maestro: ", false, true);

                Login.maestro.setEnabled(false);

            } else
            {

                updatePantalla("Iniciar sesi�n como administrador", "Admin: ", true, false);

            }

        }

    }

    public void iniciarElementos()
    {
        setLayout(null);

        alumnosInstancias = new ArrayList<>();

        maestrosInstanciados = new ArrayList<>();

        mostrarAlumnos = new JTextArea();

        infoMaestro = new JLabel();

        scrollAlumnos = new JScrollPane(mostrarAlumnos);

        anadirAlumno = new JButton(new AccionBotones("A�adir a un alumno", "A�adir�s a un alumno"));
        anadirAlumno.setEnabled(false);

        borrarAlumno = new JButton(new AccionBotones("Borrar a un alumno", "Se borrar� al alumno que est� seleccionado actualmente"));
        borrarAlumno.setEnabled(false);

        califAlumno = new JButton(new AccionBotones("Poner calificaci�n", "Establecer calificaci�n al alumno seleccionado actualmente"));
        califAlumno.setEnabled(false);

        administrador = new JButton(new AccionBotones("ADMINISTRADOR", "Iniciar sesi�n como administrador"));
        administrador.setEnabled(true);

        maestro = new JButton(new AccionBotones("MAESTRO", "Iniciar sesi�n como maestro"));
        maestro.setEnabled(maestrosInstanciados.size() > 0);

        listaAlumnos = new JComboBox<>();

    }

    public void ponerElementos()
    {
        listaAlumnos.setBounds(100, 5, 600, 30);

        scrollAlumnos.setBounds(5, 50, 400, 600);

        anadirAlumno.setBounds(450, 100, 300, 25);

        borrarAlumno.setBounds(450, 150, 300, 25);

        califAlumno.setBounds(450, 200, 300, 25);

        administrador.setBounds(655, 740, 130, 25);

        maestro.setBounds(500, 740, 130, 25);

        infoMaestro.setBounds(5, 750, 525, 25);

    }

    public void anadirElementos()
    {
        add(scrollAlumnos);
        add(listaAlumnos);
        add(anadirAlumno);
        add(borrarAlumno);
        add(califAlumno);
        add(administrador);
        add(maestro);
        add(infoMaestro);
    }

    public void updatePantalla(String info, String roll, boolean admin, boolean botonadmin)
    {
        login = new Login();

        login.setTitle(info);

        Login.infoUser.setText(roll);

        Login.administrador = admin;

        Login.admin.setEnabled(botonadmin);
    }

    public void error(String infoMensaje)
    {
        Toolkit.getDefaultToolkit().beep();

        JOptionPane.showMessageDialog(null, infoMensaje, "Datos no v�lidos!", JOptionPane.ERROR_MESSAGE);
    }

}
