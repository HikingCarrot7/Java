package barradeherramientas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class Lamina extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private JMenu menu;
	
	private JToolBar misHerramientas; 
	
	private JMenuBar mibarra;
	
	private Accion[] acciones = {new Accion("Azul", Color.blue), new Accion("Verde", Color.green), new Accion("Amarillo", Color.yellow)};

	public Lamina() 
	{
		setLayout(new BorderLayout());
		
		anadirElementos();
		
		add(mibarra, BorderLayout.NORTH);
		add(misHerramientas, BorderLayout.SOUTH);
		
		
	}
	
	private class Accion extends AbstractAction
	{

		private static final long serialVersionUID = 1L;
		
		private Color color;
		
		public Accion(String titulo, Color color) 
		{
			putValue(Action.NAME, titulo);
			
			this.color = color;
			
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			setBackground(color);
			
		}
		
	}
	
	public void anadirElementos() 
	{
		misHerramientas = new JToolBar();
		
		mibarra = new JMenuBar();
		
		menu = new JMenu("Colores");
		
		for(Accion A: acciones) 
		{
			menu.add(A);
			misHerramientas.add(A);
		}
		
		misHerramientas.addSeparator();
		
		misHerramientas.add(new AbstractAction("Salir") 
		{

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
				
			}
			
		});
		
		mibarra.add(menu);
		
	}
	
}
