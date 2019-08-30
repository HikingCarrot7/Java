package textobasico;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Lamina extends JPanel
{

    private static final long serialVersionUID = 1L;

    private JPanel capInfo, datos;

    private JTextField nombre, email;

    private JButton capturar;

    private JLabel infoEmail, infoNombre, resultado;

    private AccionText validarEmail, validarNombre;

    private boolean fallido = false;

    public Lamina()
    {
        setLayout(new BorderLayout());

        resultado = new JLabel("", JLabel.CENTER);

        //Panel donde estan los datos a capturar
        datos = new JPanel();

        datos.setLayout(new FlowLayout(FlowLayout.LEFT));

        //Nombre
        infoNombre = new JLabel("Nombre: ");

        nombre = new JTextField("Escribe tu nombre...", 15);
        nombre.addFocusListener(validarNombre = new AccionText(nombre));

        datos.add(infoNombre);
        datos.add(nombre);

        //Email
        infoEmail = new JLabel("Email: ");

        email = new JTextField("Escribe tu email...", 15);
        email.addFocusListener(validarEmail = new AccionText(email));

        datos.add(infoEmail);
        datos.add(email);

        add(datos, BorderLayout.NORTH);

        add(resultado, BorderLayout.CENTER);

        //Panel del boton para capturar datos
        capInfo = new JPanel();

        capInfo.setLayout(new BorderLayout());

        capturar = new JButton("Capturar informaci�n");
        capturar.addActionListener(new AccionBoton());

        capInfo.add(capturar, BorderLayout.CENTER);

        add(capInfo, BorderLayout.SOUTH);

    }

    private class AccionText implements FocusListener
    {

        private String text = "";

        private JTextField campoTexto;

        public AccionText(JTextField campoTexto)
        {
            this.campoTexto = campoTexto;
        }

        @Override
        public void focusGained(FocusEvent e)
        {
            if (text.contentEquals(""))
                campoTexto.setText("");
        }

        @Override
        public void focusLost(FocusEvent e)
        {
            text = campoTexto.getText().trim();

            if (text.contentEquals(""))
                campoTexto.setText("Escribe lo que se te solicita...");

        }

        public String validarText()
        {
            return text;
        }

    }

    private class AccionBoton implements ActionListener
    {

        private String text = "";

        @Override
        public void actionPerformed(ActionEvent e)
        {

            int arrobas = 0;

            if (!(validarEmail.validarText().contentEquals("") || validarNombre.validarText().contentEquals("")))
            {
                text = email.getText().trim();

                //Validas el email
                for (int i = 0; i < text.length(); i++)
                    if (text.charAt(i) == '@' || text.substring(text.length() - 4, text.length()).contains("@") || text.charAt(0) == '@')
                        arrobas++;

                if (arrobas == 1)
                {
                    System.out.println("Tu email es: " + text);

                    fallido = false;

                } else
                {
                    resultado.setText("Tu email NO es v�lido");

                    fallido = true;
                }

                if (!fallido)
                {
                    System.out.println("Tu nombre es: " + nombre.getText());

                    resultado.setText("Datos capturados correctamente. Mostrando en consola...");
                }

            } else
                resultado.setText("No llenaste alguno de los campos!");
        }
    }
}
