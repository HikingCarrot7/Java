package administracion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CapturarMaestro extends JFrame
{

    private static final long serialVersionUID = 1L;

    private final DatosAlumno datosAlumno;
    private boolean datosCorrectos = true;

    public CapturarMaestro()
    {
        datosAlumno = new DatosAlumno();

        setBounds(0, 0, 400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("A�adir a un alumno");
        add(datosAlumno);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private final class DatosAlumno extends JPanel
    {

        private static final long serialVersionUID = 1L;

        private JTextField nombre, edad, asignatura, salario, user;
        private JPasswordField password, password2;
        private JLabel infonombre, infoedad, infoasig, infosalario, infouser, infopass, infopass2, vacioNombre, vacioEdad, vacioasig, vaciosalario, vaciouser, vaciopass, vaciopass2;
        private JButton confirmar;
        private JPanel infoPersona, boton;

        public DatosAlumno()
        {
            iniciarElementos();

            setLayout(new BorderLayout());

            infoPersona.setLayout(new GridLayout(7, 2));

            anadirElementos();

        }

        public void iniciarElementos()
        {
            infoPersona = new JPanel();
            boton = new JPanel();

            nombre = new JTextField();
            edad = new JTextField();
            asignatura = new JTextField();
            salario = new JTextField();
            user = new JTextField();
            password = new JPasswordField();

            password.getDocument().addDocumentListener(new DocumentListener()
            {

                @Override
                public void changedUpdate(DocumentEvent e)
                {
                }

                @Override
                public void insertUpdate(DocumentEvent e)
                {
                    update(password, password2);
                }

                @Override
                public void removeUpdate(DocumentEvent e)
                {
                    update(password, password2);
                }

            });

            password2 = new JPasswordField();
            password2.getDocument().addDocumentListener(new DocumentListener()
            {

                @Override
                public void changedUpdate(DocumentEvent e)
                {
                }

                @Override
                public void insertUpdate(DocumentEvent e)
                {
                    update(password, password2);
                }

                @Override
                public void removeUpdate(DocumentEvent e)
                {
                    update(password, password2);
                }

            });

            infonombre = new JLabel("Nombre: ");
            infoedad = new JLabel("Edad del maestro: ");
            infoasig = new JLabel("Asignatura: ");
            infosalario = new JLabel("Salario / mes: ");
            infouser = new JLabel("Nuevo usuario: ");
            infopass = new JLabel("Nueva contrase�a: ");
            infopass2 = new JLabel("Confirmar contrase�a: ");

            vacioNombre = new JLabel(" ");
            vacioEdad = new JLabel(" ");
            vacioasig = new JLabel(" ");
            vaciosalario = new JLabel(" ");
            vaciouser = new JLabel(" ");
            vaciopass = new JLabel(" ");
            vaciopass2 = new JLabel(" ");

            confirmar = new JButton(new AccionBoton());

        }

        public void anadirElementos()
        {
            infoPersona.add(infonombre);
            infoPersona.add(nombre);
            infoPersona.add(vacioNombre);

            infoPersona.add(infoedad);
            infoPersona.add(edad);
            infoPersona.add(vacioEdad);

            infoPersona.add(infoasig);
            infoPersona.add(asignatura);
            infoPersona.add(vacioasig);

            infoPersona.add(infosalario);
            infoPersona.add(salario);
            infoPersona.add(vaciosalario);

            infoPersona.add(infouser);
            infoPersona.add(user);
            infoPersona.add(vaciouser);

            infoPersona.add(infopass);
            infoPersona.add(password);
            infoPersona.add(vaciopass);

            infoPersona.add(infopass2);
            infoPersona.add(password2);
            infoPersona.add(vaciopass2);

            boton.add(confirmar);

            add(infoPersona, BorderLayout.NORTH);
            add(boton, BorderLayout.SOUTH);

        }

        private class AccionBoton extends AbstractAction
        {

            private static final long serialVersionUID = 1L;

            public AccionBoton()
            {
                putValue(Action.NAME, "Capturar datos");
                putValue(Action.SHORT_DESCRIPTION, "Capturar datos del nuevo maestro");
            }

            @Override
            public void actionPerformed(ActionEvent e)
            {
                datosCorrectos = true;

                datosCorrectos = Generales.validarDatos(nombre, vacioNombre);
                datosCorrectos = Generales.validarDatos(asignatura, vacioasig);
                datosCorrectos = Generales.validarDatos(user, vaciouser);
                datosCorrectos = Generales.validarDatos(password, vaciopass);
                datosCorrectos = Generales.validarDatos(password2, vaciopass2);

                datosCorrectos = Generales.intentarValidarEntero(edad, vacioEdad);
                datosCorrectos = Generales.intentarValidarDouble(salario, vaciosalario, 50000);

                confirmar();

                tamano();

                if (datosCorrectos)
                {
                    LaminaAdministrador.listaMaestros.addItem(new Maestro(nombre.getText()).toString());

                    Maestro nuevoMaestro = new Maestro(nombre.getText(), Integer.parseInt(edad.getText()), asignatura.getText(), Double.parseDouble(salario.getText()), user.getText(), Generales.pasarAString(password.getPassword()), new Date().toString());

                    LaminaMaestro.maestrosInstanciados.add(nuevoMaestro);
                    LeerDatos.anadirMaestros(nuevoMaestro);

                    Generales.actualizarTextoMaestros();

                    LaminaMaestro.alumnosInstancias.add(new ArrayList<>());

                    LaminaAdministrador.mostrar.setEnabled(true);

                    LaminaAdministrador.eliminarMaestro.setEnabled(true);

                    LaminaAdministrador.salarioMaestro.setEnabled(true);

                    dispose();

                }

            }

        }

        public void update(JPasswordField pass1, JPasswordField pass2)
        {
            pass1.setBackground(Generales.pasarAString(password.getPassword()).contentEquals(Generales.pasarAString(password2.getPassword())) && password.getPassword().length > 5 ? Color.green : Color.white);
            pass2.setBackground(Generales.pasarAString(password.getPassword()).contentEquals(Generales.pasarAString(password2.getPassword())) && password.getPassword().length > 5 ? Color.green : Color.white);
        }

        public void confirmar()
        {
            if (!(Generales.pasarAString(password.getPassword()).contentEquals(Generales.pasarAString(password2.getPassword()))))
            {
                text("*No hay similitud");

                datosCorrectos = false;

            }
        }

        public void tamano()
        {
            if (!(password.getPassword().length > 5 && password2.getPassword().length > 5))
            {
                text("*Contrase�a peque�a");

                datosCorrectos = false;

            }
        }

        public void text(String text)
        {
            vaciopass.setText(text);

            vaciopass2.setText(text);
        }

    }

}
