package notepadbasico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Lamina extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JMenuBar barramenu;
	
	private String[] todasMisFuentes = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	
	private JMenu[] opciones = {new JMenu("Fuente"), new JMenu("Estilo"), new JMenu("Tamaño"), new JMenu("Color fondo"), new JMenu("Color de texto")};

	private JMenuItem[] fuente;
	
	private JMenuItem[] tipo = {new JMenuItem(new Accion("Negritas", Font.BOLD)), new JMenuItem(new Accion("Cursivas", Font.ITALIC)), new JMenuItem(new Accion("Normal", Font.PLAIN))};
	
	private JMenuItem[] tamano = {new JMenuItem(new Accion("10")), new JMenuItem(new Accion("12")), new JMenuItem(new Accion("16")), new JMenuItem(new Accion("24")), new JMenuItem(new Accion("36"))};
	
	private JMenuItem[] colorfondo = {new JMenuItem(new Accion("Verde", Color.green.brighter())), new JMenuItem(new Accion("Rosado", Color.pink)), new JMenuItem(new Accion("Amarillo", Color.yellow.brighter())), new JMenuItem(new Accion("Blanco", Color.white))};
	
	private JScrollPane Laminatexto;
	
	private JTextArea texto;
	
	private String fuenteGuardada, tamanoGuardado;
	
	private int tipoGuardado;
	
	public Lamina()
	{
		
		setLayout(new BorderLayout());
		
		fuenteGuardada = "Arial";
		
		tamanoGuardado = "12";
		
		tipoGuardado = Font.PLAIN;
		
		iniciarElementos();
		
		
		add(barramenu, BorderLayout.NORTH);
		add(Laminatexto, BorderLayout.CENTER);
		
	}
	
	private class Accion extends AbstractAction
	{
		private static final long serialVersionUID = 1L;

		public Accion(String titulo) 
		{
			putValue(Action.NAME, titulo);
			
		}
		
		public Accion(String titulo, int tipo) 
		{
			putValue(Action.NAME, titulo);
			putValue("fuente", tipo);
		}
		
		public Accion(String titulo, Color color) 
		{
			putValue(Action.NAME, titulo);
			putValue("color", color);
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			for(int i = 0; i < fuente.length; i++) 
			{
				if(e.getSource() == fuente[i]) 
				{
					fuenteGuardada = (String) getValue(Action.NAME);
					
					actualizarTexto();
				}
			}
			
			for(int i = 0; i < tamano.length; i++) 
			{
				if(e.getSource() == tamano[i]) 
				{
					tamanoGuardado = (String) getValue(Action.NAME);
					
					//new StyledEditorKit.FontSizeAction("", 36);
		
					actualizarTexto();
				}
			}
			
			for(int i = 0; i < tipo.length; i++) 
			{
				if(e.getSource() == tipo[i]) 
				{
					if((int) getValue("fuente") == 0) 
					{
						tipoGuardado = 0;
						
					}else if(tipoGuardado < 3)
					{
						tipoGuardado += (int) getValue("fuente");
					}
					
					actualizarTexto();
				}
				
			}
			
			for(int i = 0; i < colorfondo.length; i++) 
			{
				if(e.getSource() == colorfondo[i]) 
				{
					texto.setBackground((Color) getValue("color"));
				}
			}
			
		}
		
	}
	
	public void iniciarElementos() 
	{
		 
		barramenu = new JMenuBar();
		
		texto = new JTextArea();
		
		Laminatexto = new JScrollPane(texto);
		
		fuente = new JMenuItem[todasMisFuentes.length - 240];
		
		JMenuItem[][] etiquetas = {fuente, tipo, tamano, colorfondo};
		
		for(int i = 0; i < todasMisFuentes.length - 240; i++) 
		{
			fuente[i] = todasMisFuentes(todasMisFuentes[i]);
			
		}
		
		for(int i = 0; i < etiquetas.length; i++) 
		{
			barramenu.add(opciones[i]);
			
			anadirItem(opciones[i], etiquetas[i]);
			
		}
		
	}
	
	public void anadirItem(JMenu menu, JMenuItem[] items) 
	{
		for(JMenuItem I: items) 
		{
			menu.add(I);
		}
		
	}
	
	public void actualizarTexto() 
	{
		texto.setFont(new Font(fuenteGuardada, tipoGuardado, Integer.parseInt(tamanoGuardado)));
	}
	
	public JMenuItem todasMisFuentes(String fuente) 
	{
		return new JMenuItem(new Accion(fuente));
	}
	
}
