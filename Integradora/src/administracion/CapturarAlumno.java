package administracion;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class CapturarAlumno extends JFrame
{

    private static final long serialVersionUID = 1L;

    private DatosAlumno datosAlumno;

    private boolean datosCorrectos;

    public CapturarAlumno()
    {
        datosAlumno = new DatosAlumno();

        setBounds(0, 0, 400, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("A�adir a un alumno");
        add(datosAlumno);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private class DatosAlumno extends JPanel
    {

        private static final long serialVersionUID = 1L;

        private JTextField nombre, edad, calificacion, licenciatura;

        private JLabel infonombre, infoedad, infocalif, infolic, vacioNombre, vacioEdad, vacioCalif, vacioLic;

        private JButton confirmar;

        private JPanel infoPersona, boton;

        public DatosAlumno()
        {
            iniciarElementos();

            setLayout(new BorderLayout());

            infoPersona.setLayout(new GridLayout(4, 2));

            anadirElementos();

        }

        public void iniciarElementos()
        {
            infoPersona = new JPanel();
            boton = new JPanel();

            nombre = new JTextField();
            edad = new JTextField();
            calificacion = new JTextField();
            licenciatura = new JTextField();

            infonombre = new JLabel("Nombre: ");
            infoedad = new JLabel("Edad del alumno: ");
            infocalif = new JLabel("Calificaci�n: ");
            infolic = new JLabel("Licenciatura: ");

            vacioNombre = new JLabel(" ");
            vacioEdad = new JLabel(" ");
            vacioCalif = new JLabel(" ");
            vacioLic = new JLabel(" ");

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

            infoPersona.add(infocalif);
            infoPersona.add(calificacion);
            infoPersona.add(vacioCalif);

            infoPersona.add(infolic);
            infoPersona.add(licenciatura);
            infoPersona.add(vacioLic);

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
                putValue(Action.SHORT_DESCRIPTION, "Capturar datos del nuevo alumno");
            }

            @Override
            public void actionPerformed(ActionEvent e)
            {
                datosCorrectos = true;

                datosCorrectos = Generales.validarDatos(nombre, vacioNombre);
                datosCorrectos = Generales.validarDatos(licenciatura, vacioLic);

                datosCorrectos = Generales.intentarValidarEntero(edad, vacioEdad);
                datosCorrectos = Generales.intentarValidarDouble(calificacion, vacioCalif, 100);

                if (datosCorrectos)
                {

                    LaminaMaestro.listaAlumnos.addItem(new Alumno(nombre.getText()).toString());

                    Alumno nuevoAlumno = new Alumno(nombre.getText(), Integer.parseInt(edad.getText()), licenciatura.getText(), Double.parseDouble(calificacion.getText()));

                    LaminaMaestro.alumnosInstancias.get(Login.indiceMaestro).add(nuevoAlumno);

                    LeerDatos.anadirAlumnos(nuevoAlumno);

                    Generales.actualizarTextoAlumnos();

                    LaminaMaestro.borrarAlumno.setEnabled(true);

                    LaminaMaestro.califAlumno.setEnabled(true);

                    dispose();

                }

            }

        }

    }

}
