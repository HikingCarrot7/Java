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
	
	private LaminaLogin laminaLogin;
	
	private boolean encontrado = false;
	
	protected static boolean administrador = true;
	
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
		setTitle("Iniciar sesión como administrador");
		add(laminaLogin);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
	
	private class LaminaLogin extends JPanel
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
				boolean incorrecto = false;
				
				if(e.getSource() == maestro) 
				{
					infoUser.setText("Maestro: ");
					
					setTitle("Iniciar sesión como maestro");
					
					maestro.setEnabled(false);
					
					admin.setEnabled(true);
					
					administrador = false;
					
					update();

				}else if(e.getSource() == admin)
				{
					infoUser.setText("Admin: ");
					
					setTitle("Iniciar sesión como administrador");
					
					maestro.setEnabled(true);
					
					admin.setEnabled(false);
					
					administrador = true;
					
					update();
					
				}else 
				{
					indiceMaestro = 0;
					
					if(administrador) 
					{
						for(Administradores A: administradores) 
						{
							if(!(A.getUsuario().contentEquals(usuario.getText().trim()) && A.getContrasena().contentEquals(Generales.pasarAString(password.getPassword()))))
							{
								incorrecto = true;
								
							}else 
							{
								incorrecto = false;
								
								break;
							}
						}
						
						if(!incorrecto) 
						{
							Principal.principal.setVisible(false);
							
							new LaminaAdministrador();
							
							Generales.actualizarTextoMaestros();
							
							LaminaAdministrador.eliminarMaestro.setEnabled(LaminaMaestro.maestrosInstanciados.size() > 0 ? true : false);
							
							LaminaAdministrador.salarioMaestro.setEnabled(LaminaMaestro.maestrosInstanciados.size() > 0 ? true : false);
							
							LaminaAdministrador.mostrar.setEnabled(LaminaMaestro.maestrosInstanciados.size() > 0 ? true : false);
							
							LaminaAdministrador.infoImport.setText("");
							
							LaminaAdministrador.mostrar.setSelected(false);
							
							dispose();
							
						}else 
						{
							mostrarError();
						}
						
					}else 
					{
						
						for(Maestro M: LaminaMaestro.maestrosInstanciados) 
						{
							if(!(M.getUsuario().contentEquals(usuario.getText().trim()) && M.getContrasena().contentEquals(Generales.pasarAString(password.getPassword()))))
							{
								incorrecto = true;
								
							}else 
							{
								incorrecto = false;
								
								break;
							}
							
							indiceMaestro++;
							
						}
						
						if(!incorrecto) 
						{
							
							Principal.principal.setVisible(true);
							
							LaminaMaestro.infoMaestro.setText(LaminaMaestro.maestrosInstanciados.get(indiceMaestro).datosImportantes());
							
							LaminaMaestro.anadirAlumno.setEnabled(true);
							
							LaminaMaestro.borrarAlumno.setEnabled(LaminaMaestro.alumnosInstancias.get(indiceMaestro).size() > 0 ? true : false);
							
							LaminaMaestro.califAlumno.setEnabled(LaminaMaestro.alumnosInstancias.get(indiceMaestro).size() > 0 ? true : false);
							
							LaminaMaestro.maestro.setEnabled(LaminaMaestro.maestrosInstanciados.size() > 1 ? true : false);
							
							actualizarDatosMaestros();
								
							dispose();
							
						}else 
						{
							mostrarError();
							
						}
						
					}
					
				}
				
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
			maestro.setEnabled(LaminaMaestro.maestrosInstanciados.size() > 0 ? true : false);
			
			login = new JButton(new AccionBotones("Iniciar sesión", "Inicia sesión según tu roll"));
			
			infoUser = new JLabel("Admin: ");
			infoPassword = new JLabel("Contraseña: ");
			
			usuario = new JTextField(10);
			usuario.getDocument().addDocumentListener(new DocumentListener() 
			{

				@Override
				public void changedUpdate(DocumentEvent e) {}

				@Override
				public void insertUpdate(DocumentEvent e) {update();}

				@Override
				public void removeUpdate(DocumentEvent e) {update();}
				
			});
			
			password = new JPasswordField(10);
			password.getDocument().addDocumentListener(new DocumentListener() 
			{

				@Override
				public void changedUpdate(DocumentEvent e) {}

				@Override
				public void insertUpdate(DocumentEvent e) {update();}

				@Override
				public void removeUpdate(DocumentEvent e) {update();}
				
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
		
		
		public void updateMaestro(ArrayList <Maestro> maestros) 
		{
			
			for(int i = 0; i < maestros.size(); i++) 
			{
				if(maestros.get(i).getUsuario().contentEquals(usuario.getText().trim())) 
				{
					indice = i;
					
					encontrado = true;
					
					break;
					
				}else
				{
					encontrado = false;
				}
			}
			
			if(encontrado) 
			{
				if(maestros.get(indice).getContrasena().contentEquals(Generales.pasarAString(password.getPassword()))) 
				{
					
					usuario.setBackground(Color.green);
					password.setBackground(Color.green);
					
				}else
				{
					usuario.setBackground(Color.white);
					password.setBackground(Color.white);
					
					encontrado = false;
				}
				
			}else 
			{
				usuario.setBackground(Color.white);
				password.setBackground(Color.white);
			}
			
		}
		
		public void updateAdmin(ArrayList <Administradores> administradores) 
		{
			
			for(int i = 0; i < administradores.size(); i++) 
			{
				if(administradores.get(i).getUsuario().contentEquals(usuario.getText().trim())) 
				{
					indice = i;
					
					encontrado = true;
					
					break;
					
				}else
				{
					encontrado = false;
				}
			}
			
			if(encontrado) 
			{
				if(administradores.get(indice).getContrasena().contentEquals(Generales.pasarAString(password.getPassword()))) 
				{
					
					usuario.setBackground(Color.green);
					password.setBackground(Color.green);
					
				}else
				{
					usuario.setBackground(Color.white);
					password.setBackground(Color.white);
					
					encontrado = false;
				}
				
			}else 
			{
				usuario.setBackground(Color.white);
				password.setBackground(Color.white);
			}
			
		}
		
		public void update() 
		{
			if(administrador) 
			{
				updateAdmin(administradores);
				
			}else 
			{
				updateMaestro(LaminaMaestro.maestrosInstanciados);
			}
		}
		
		public void mostrarError() 
		{
			Toolkit.getDefaultToolkit().beep();
			
			JOptionPane.showMessageDialog(null, "Alguno de los campos está vacío o son incorrectos!", "Datos no válidos!", JOptionPane.ERROR_MESSAGE);
		}
		
		public void actualizarDatosMaestros() 
		{
			if(LaminaMaestro.alumnosInstancias.get(indiceMaestro).size() > 0) 
			{
				LaminaMaestro.mostrarAlumnos.setText("");
				LaminaMaestro.listaAlumnos.removeAllItems();
				
				for(int x = 0; x < LaminaMaestro.alumnosInstancias.get(indiceMaestro).size(); x++) 
				{
					LaminaMaestro.mostrarAlumnos.append(LaminaMaestro.alumnosInstancias.get(indiceMaestro).get(x).mostrarDatos());
					
					LaminaMaestro.listaAlumnos.addItem(LaminaMaestro.alumnosInstancias.get(indiceMaestro).get(x).toString());
				}
				
			}else 
			{
				LaminaMaestro.mostrarAlumnos.setText("");
				LaminaMaestro.listaAlumnos.removeAllItems();
				
				LaminaMaestro.borrarAlumno.setEnabled(false);
				LaminaMaestro.califAlumno.setEnabled(false);
			}
			
		}
		
	}

}
