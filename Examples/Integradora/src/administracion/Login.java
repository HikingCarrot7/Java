package administracion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Login extends JFrame
{

    private static final long serialVersionUID = 1L;

    private final LaminaLogin laminaLogin;
    private boolean encontrado = false;
    protected static boolean administrador = true, incorrecto = false;
    protected static int indiceMaestro = 0;
    protected static JButton admin, maestro;
    protected static JLabel infoUser;
    private int indice = 0;
    protected static ArrayList<Administradores> administradores;

    public Login()
    {
        laminaLogin = new LaminaLogin();

        setBounds(0, 0, 400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Iniciar sesi�n como administrador");
        add(laminaLogin);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private final class LaminaLogin extends JPanel
    {

        private static final long serialVersionUID = 1L;

        private JPanel info, botones;
        private JButton login;
        private JLabel infoPassword;
        private JPasswordField password;
        private JTextField usuario;

        public LaminaLogin()
        {

            LeerDatos.LeerMaestros();

            LeerDatos.leerAlumnos();

            iniciarElementos();

            colocarElementos();
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

                incorrecto = false;

                if (e.getSource() == maestro)
                {
                    infoUser.setText("Maestro: ");

                    setTitle("Iniciar sesi�n como maestro");

                    maestro.setEnabled(false);

                    admin.setEnabled(true);

                    administrador = false;

                    update();

                } else if (e.getSource() == admin)
                {
                    infoUser.setText("Admin: ");

                    setTitle("Iniciar sesi�n como administrador");

                    maestro.setEnabled(true);

                    admin.setEnabled(false);

                    administrador = true;

                    update();

                } else
                {
                    indiceMaestro = 0;

                    if (administrador)
                    {
                        checarContrasena(administradores);

                        if (!incorrecto)
                        {
                            Principal.principal.setVisible(false);

                            new LaminaAdministrador();

                            Generales.actualizarTextoMaestros();

                            LaminaAdministrador.eliminarMaestro.setEnabled(LaminaMaestro.maestrosInstanciados.size() > 0);

                            LaminaAdministrador.salarioMaestro.setEnabled(LaminaMaestro.maestrosInstanciados.size() > 0);

                            LaminaAdministrador.mostrar.setEnabled(LaminaMaestro.maestrosInstanciados.size() > 0);

                            LaminaAdministrador.infoImport.setText("");

                            LaminaAdministrador.mostrar.setSelected(false);

                            dispose();

                        } else
                            mostrarError();

                    } else
                    {

                        checarContrasena(LaminaMaestro.maestrosInstanciados);

                        if (!incorrecto)
                        {

                            Principal.principal.setVisible(true);

                            LaminaMaestro.infoMaestro.setText(LaminaMaestro.maestrosInstanciados.get(indiceMaestro).datosImportantes());

                            LaminaMaestro.anadirAlumno.setEnabled(true);

                            LaminaMaestro.borrarAlumno.setEnabled(LaminaMaestro.alumnosInstancias.get(indiceMaestro).size() > 0);

                            LaminaMaestro.califAlumno.setEnabled(LaminaMaestro.alumnosInstancias.get(indiceMaestro).size() > 0);

                            LaminaMaestro.maestro.setEnabled(LaminaMaestro.maestrosInstanciados.size() > 1);

                            actualizarDatosMaestros();

                            dispose();

                        } else
                            mostrarError();

                    }

                }

            }

        }

        public <T extends InicioSesion> void checarContrasena(ArrayList<T> contrasenas)
        {
            for (T P : contrasenas)
            {
                if (!(P.getUsuario().contentEquals(usuario.getText().trim()) && P.getContrasena().contentEquals(Generales.pasarAString(password.getPassword()))))
                    incorrecto = true;
                else
                {
                    incorrecto = false;

                    break;
                }

                indiceMaestro++;
            }
        }

        public void iniciarElementos()
        {
            setLayout(new BorderLayout());

            administradores = new ArrayList<>();

            administradores.add(new Administradores("carlos.fca", "haloinfinity"));

            info = new JPanel();
            botones = new JPanel();

            admin = new JButton(new AccionBotones("Administrador", "Acceder como administrador"));
            admin.setEnabled(false);

            maestro = new JButton(new AccionBotones("Maestro", "Acceder como maestro"));
            maestro.setEnabled(LaminaMaestro.maestrosInstanciados.size() > 0);

            login = new JButton(new AccionBotones("Iniciar sesi�n", "Inicia sesi�n seg�n tu roll"));

            infoUser = new JLabel("Admin: ");
            infoPassword = new JLabel("Contrase�a: ");

            usuario = new JTextField(10);
            usuario.getDocument().addDocumentListener(new DocumentListener()
            {

                @Override
                public void changedUpdate(DocumentEvent e)
                {
                }

                @Override
                public void insertUpdate(DocumentEvent e)
                {
                    update();
                }

                @Override
                public void removeUpdate(DocumentEvent e)
                {
                    update();
                }

            });

            password = new JPasswordField(10);
            password.getDocument().addDocumentListener(new DocumentListener()
            {

                @Override
                public void changedUpdate(DocumentEvent e)
                {
                }

                @Override
                public void insertUpdate(DocumentEvent e)
                {
                    update();
                }

                @Override
                public void removeUpdate(DocumentEvent e)
                {
                    update();
                }

            });

        }

        public void colocarElementos()
        {
            info.add(infoUser);
            info.add(usuario);

            info.add(infoPassword);
            info.add(password);

            botones.add(maestro);
            botones.add(admin);
            botones.add(login);

            add(info, BorderLayout.NORTH);
            add(botones, BorderLayout.SOUTH);

        }

        public <T extends InicioSesion> void updateCampos(ArrayList<T> persona)
        {

            for (int i = 0; i < persona.size(); i++)
                if (persona.get(i).getUsuario().contentEquals(usuario.getText().trim()))
                {
                    indice = i;

                    encontrado = true;

                    break;

                } else
                    encontrado = false;

            if (encontrado)
                if (persona.get(indice).getContrasena().contentEquals(Generales.pasarAString(password.getPassword())))
                {
                    usuario.setBackground(Color.green);
                    password.setBackground(Color.green);

                } else
                {
                    usuario.setBackground(Color.white);
                    password.setBackground(Color.white);

                    encontrado = false;
                }
            else
            {
                usuario.setBackground(Color.white);
                password.setBackground(Color.white);
            }

        }

        public void update()
        {
            if (administrador)
                updateCampos(administradores);
            else
                updateCampos(LaminaMaestro.maestrosInstanciados);
        }

        public void mostrarError()
        {
            Toolkit.getDefaultToolkit().beep();

            JOptionPane.showMessageDialog(null, "Alguno de los campos est� vac�o o son incorrectos!", "Datos no v�lidos!", JOptionPane.ERROR_MESSAGE);
        }

        public void actualizarDatosMaestros()
        {
            if (LaminaMaestro.alumnosInstancias.get(indiceMaestro).size() > 0)
            {
                LaminaMaestro.mostrarAlumnos.setText("");
                LaminaMaestro.listaAlumnos.removeAllItems();

                for (int x = 0; x < LaminaMaestro.alumnosInstancias.get(indiceMaestro).size(); x++)
                {
                    LaminaMaestro.mostrarAlumnos.append(LaminaMaestro.alumnosInstancias.get(indiceMaestro).get(x).mostrarDatos());

                    LaminaMaestro.listaAlumnos.addItem(LaminaMaestro.alumnosInstancias.get(indiceMaestro).get(x).toString());
                }

            } else
            {
                LaminaMaestro.mostrarAlumnos.setText("");
                LaminaMaestro.listaAlumnos.removeAllItems();

                LaminaMaestro.borrarAlumno.setEnabled(false);
                LaminaMaestro.califAlumno.setEnabled(false);
            }

        }

    }

}
