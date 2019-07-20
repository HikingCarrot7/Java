package examenlisteners;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MiFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private Lamina lamina;
	private JPanel botones;
	private AccionVentanas ventanas;

	public MiFrame()
	{
		lamina = new Lamina();
		ventanas = new AccionVentanas();
		
		setResizable(true);
		setSize(1000, 1000);
		setLocationRelativeTo(null);
		add(lamina,BorderLayout.CENTER);
		addMouseMotionListener(lamina);
		addMouseListener(lamina);
		addWindowListener(ventanas);
		addWindowStateListener(ventanas);
		addWindowFocusListener(ventanas);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		botones = new JPanel();
		anadirBotones(botones);
		add(botones, BorderLayout.SOUTH);
		
		setVisible(true);
		
		/*ponerBotones(botones, "Sample test", new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});*/
	}
	
	public static void main(String[] args) 
	{
		new MiFrame();
	}
	
	public void anadirBotones(JPanel boton) 
	{
		AccionBotones accionClose = new AccionBotones("Apagar", "Apagar", "Desactiva las animaciones");
		AccionBotones accionCircular = new AccionBotones("Circular", "Circular", "Movimiento circular");
		AccionBotones accionCirculos = new AccionBotones("C�rculos", "Circulos", "Mover c�rculos");
		AccionBotones accionZoom = new AccionBotones("Zoom", "Zoom", "Zoom");
		
		boton.add(new JButton(accionClose));
		boton.add(new JButton(accionCircular));
		boton.add(new JButton(accionCirculos));
		boton.add(new JButton(accionZoom));
		
	}
	
	/*public void ponerBotones(Container c, String titulo, ActionListener oyente) 
	{
		JButton boton = new JButton(titulo);
		
		c.add(boton);
		
		boton.addActionListener(oyente);
	}*/
	
	private class AccionBotones extends AbstractAction
	{
		
		private static final long serialVersionUID = 1L;

		public AccionBotones(String titulo, String accion, String descripcion)
		{
			putValue(Action.NAME, titulo);
			putValue(Action.SHORT_DESCRIPTION, descripcion);
			putValue("Accion", accion);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String accion = "";
			
			accion = (String) getValue("Accion");
			
			if(accion.contentEquals("Apagar")) 
			{
				lamina.apagar();
				
			}else if(accion.contentEquals("Circular")) 
			{
				lamina.generarCircular();
				
			}else if(accion.contentEquals("Circulos"))
			{
				lamina.generarCirculos();
				
			}else 
				lamina.generarZoom();
		}
	}
	
	private class AccionVentanas extends WindowAdapter
	{
		@Override
		public void windowStateChanged(WindowEvent e) 
		{
			if(e.getNewState() == Frame.MAXIMIZED_BOTH) 
				lamina.generarCircular();
				
			else if(e.getOldState() == Frame.MAXIMIZED_BOTH) 
				lamina.generarCirculos();
		}

		@Override
		public void windowDeiconified(WindowEvent e) 
		{
			lamina.generarZoom();
		}

		@Override
		public void windowGainedFocus(WindowEvent e) 
		{
			setTitle("");
			
		}

		@Override
		public void windowLostFocus(WindowEvent e) 
		{
			setTitle("I have many thing to show you");
			lamina.generarCirculos();
		}
	}
}
