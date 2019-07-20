package administracion;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LaminaAdministrador extends JFrame
{
	private static final long serialVersionUID = 1L;

	private Administrador administrador;
	
	protected static JLabel infoImport;
	
	protected static JComboBox<String> listaMaestros;
	
	protected static JCheckBox mostrar;
	
	protected static JTextArea mostrarMaestros;
	
	protected static JButton anadirMaestro, eliminarMaestro, salarioMaestro, salir;
	
	public LaminaAdministrador() 
	{
		
		administrador = new Administrador();
		
		setBounds(0, 0, 800, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Administrador");
		add(administrador);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
	
	private class Administrador extends JPanel
	{	
		private static final long serialVersionUID = 1L;
		
		private JScrollPane scrollMaestros;
		
		public Administrador() 
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
				if(e.getSource() == anadirMaestro) 
				{
					new CapturarMaestro();
					
				}else if(e.getSource() == eliminarMaestro) 
				{
					int opcion = Generales.alerta( "La lista de alumnos relacionada con este maestro se borrará. ¿Está seguro?", "¡Se borrarán todos los datos!"); 
					
					if(opcion == 0) 
					{
						LaminaMaestro.maestrosInstanciados.remove(listaMaestros.getSelectedIndex());
						
						LaminaMaestro.alumnosInstancias.remove(listaMaestros.getSelectedIndex());
						
						LeerDatos.actualizarListaMaestros();
						
						LeerDatos.actualizarListaAlumnos();
						
						listaMaestros.removeItemAt(listaMaestros.getSelectedIndex());
						
						Generales.actualizarTextoMaestros();
						
						if(listaMaestros.getItemCount() < 1) 
						{
							eliminarMaestro.setEnabled(false);
							mostrar.setEnabled(false);
							salarioMaestro.setEnabled(false);
						}
						
						mostrar.setSelected(false);
						infoImport.setText("");
						
					}
					
				}else if(e.getSource() == salarioMaestro)
				{
					new CambiarSalario();
						
				}else if(e.getSource() == mostrar) 
				{
					
					if(mostrar.isSelected()) 
					{
						infoImport.setText(LaminaMaestro.maestrosInstanciados.get(listaMaestros.getSelectedIndex()).infoPrivada());
						
					}else 
					{
						infoImport.setText("");
					}
					
				}else 
				{
					
					new Login();
					
					LaminaMaestro.maestro.setEnabled(LaminaMaestro.maestrosInstanciados.size() > 0 ? true : false);
					
					Login.maestro.setEnabled(LaminaMaestro.maestrosInstanciados.size() > 0 ? true : false);
					
					dispose();
				}
				
			}
			
		}
		
		public void iniciarElementos() 
		{
			
			setLayout(null);
			
			mostrarMaestros = new JTextArea();
			
			scrollMaestros = new JScrollPane(mostrarMaestros);
			
			anadirMaestro = new JButton(new AccionBotones("Añadir a un maestro", "Añadirás a un maestro"));
			
			eliminarMaestro = new JButton(new AccionBotones("Eliminar a un maestro", "Se borrará al maestro que está seleccionado actualmente"));
			eliminarMaestro.setEnabled(false);
			
			salarioMaestro = new JButton(new AccionBotones("Cambiar salario", "Cambiar el salario del maestro que está seleccionado actualmente"));
			salarioMaestro.setEnabled(false);
			
			salir = new JButton(new AccionBotones("Salir", "Se cerrará esta sesión"));
			
			listaMaestros = new  JComboBox<>();
			
			mostrar = new JCheckBox(new AccionBotones("Mostrar usuario y contraseña", "Se mostrará el usuario y contraseña del maestro seleccionado actualmente"));
			mostrar.setEnabled(false);
			
			infoImport = new JLabel();
			
			
		}
		
		public void ponerElementos() 
		{
			
			listaMaestros.setBounds(100, 5, 600, 30);
			
			scrollMaestros.setBounds(5, 50, 400, 600);
			
			anadirMaestro.setBounds(450, 100, 300, 25);
			
			eliminarMaestro.setBounds(450, 150, 300, 25);
			
			salarioMaestro.setBounds(450, 200, 300, 25);
			
			salir.setBounds(655, 740, 130, 25);
			
			mostrar.setBounds(445, 250, 200, 20);
			
			infoImport.setBounds(445, 270, 400, 100);
			
		}
		
		public void anadirElementos() 
		{
			
			add(scrollMaestros);
			add(listaMaestros);
			add(anadirMaestro);
			add(eliminarMaestro);
			add(salarioMaestro);
			add(salir);
			add(mostrar);
			add(infoImport);
			
		}
		
	}

}
