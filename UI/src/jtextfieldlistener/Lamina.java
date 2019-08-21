package jtextfieldlistener;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class Lamina extends JPanel
{

    private static final long serialVersionUID = 1L;

    private String username = "Nicol�s", password = "minecraft2014";

    private JTextField usuario;

    private JPasswordField contrasena;

    private JLabel infoUsuario, infoContrasena, respuesta;

    private JPanel infoPersona;

    private JButton proceder;

    private boolean userCorrecto = false, passCorrecto = false;

    public Lamina()
    {
        setLayout(new BorderLayout());

        respuesta = new JLabel("", JLabel.CENTER);

        infoPersona = new JPanel();

        infoPersona.setLayout(new FlowLayout(FlowLayout.CENTER));

        usuario = new JTextField(15);
        infoUsuario = new JLabel("Usuario: ");

        contrasena = new JPasswordField(15);
        infoContrasena = new JLabel("Contrase�a: ");

        usuario.getDocument().addDocumentListener(new EscuchaTexto(usuario));
        usuario.addFocusListener(new AccionText(usuario));

        contrasena.getDocument().addDocumentListener(new EscuchaTexto(contrasena));

        infoPersona.add(infoUsuario);
        infoPersona.add(usuario);
        infoPersona.add(infoContrasena);
        infoPersona.add(contrasena);

        add(infoPersona, BorderLayout.NORTH);

        proceder = new JButton("Proceder :v");

        proceder.addActionListener(new AccionProceder());

        add(proceder, BorderLayout.SOUTH);

        add(respuesta, BorderLayout.CENTER);

    }

    private class EscuchaTexto implements DocumentListener
    {

        boolean control = false;
        JTextField usuario;
        JPasswordField contrasena;

        public EscuchaTexto(JTextField usuario)
        {
            this.usuario = usuario;
            control = true;
        }

        public EscuchaTexto(JPasswordField contrasena)
        {
            this.contrasena = contrasena;
        }

        @Override
        public void changedUpdate(DocumentEvent e)
        {
        }

        @Override
        public void insertUpdate(DocumentEvent e)
        {
            if (!control)
            {
                actualizarContra(contrasena);

            } else
            {
                actualizarUsuario(usuario);
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e)
        {
            if (!control)
            {
                actualizarContra(contrasena);

            } else
            {
                actualizarUsuario(usuario);
            }
        }
    }

    private class AccionProceder implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (userCorrecto && passCorrecto)
            {
                respuesta.setText("Datos correctos");

            } else
            {
                respuesta.setText("Alguno de los datos es incorrecto");
            }
        }
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
            {
                campoTexto.setText("");

                campoTexto.setBackground(Color.white);

            }
        }

        @Override
        public void focusLost(FocusEvent e)
        {
            text = campoTexto.getText().trim();

            if (text.contentEquals(""))
            {
                campoTexto.setText("Escribe lo que se te solicita...");

                campoTexto.setBackground(Color.white);

            }
        }
    }

    public void actualizarContra(JPasswordField contrasena)
    {
        char[] contra = password.toCharArray();
        char[] palabra;

        palabra = contrasena.getPassword();

        if (palabra.length != 0)
        {
            if (!(palabra.length < contra.length || palabra.length > contra.length))
            {
                for (int i = 0; i < password.length(); i++)
                {
                    if (palabra[i] == contra[i])
                    {
                        contrasena.setBackground(Color.white);

                        passCorrecto = true;

                    } else
                    {
                        contrasena.setBackground(Color.red.brighter().brighter());

                        passCorrecto = false;

                    }
                }

            } else
            {
                contrasena.setBackground(Color.red.brighter().brighter());

                passCorrecto = false;
            }

        } else
        {
            contrasena.setBackground(Color.white);

            passCorrecto = false;
        }
    }

    public void actualizarUsuario(JTextField usuario)
    {
        if (!(usuario.getText().contentEquals(username)) && usuario.getText().length() != 0)
        {
            usuario.setBackground(Color.red.brighter().brighter());

            userCorrecto = false;

        } else
        {
            usuario.setBackground(Color.white);

            userCorrecto = true;
        }
    }
}
