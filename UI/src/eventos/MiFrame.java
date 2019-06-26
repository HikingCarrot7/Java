package eventos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

public class MiFrame extends JFrame implements WindowFocusListener, WindowStateListener
{
	private static final long serialVersionUID = 1L;
	
	miLamina lamina;
	
	public MiFrame() 
	{
		lamina = new miLamina();
		
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension TamanoPantalla = miPantalla.getScreenSize();
		
		setSize(TamanoPantalla.width/2, TamanoPantalla.height/2);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(true);
		add(lamina);
		addWindowStateListener(this);
		addWindowFocusListener(this);
		addMouseListener(new EventoMouse());
		addMouseMotionListener(new EventoMouse());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public static void main(String[] args) 
	{
		
		new MiFrame();
		new MiFrame();

	}

	@Override
	public void windowGainedFocus(WindowEvent e) 
	{
		setTitle("Esta ventana es el foco");
	}

	@Override
	public void windowLostFocus(WindowEvent e) 
	{
		setTitle("Esta ventana perdió el foco");
	}
	
	@Override
	public void windowStateChanged(WindowEvent e) 
	{	
		if(e.getNewState() == Frame.MAXIMIZED_BOTH) 
		{
			lamina.setBackground(Color.green);
			
		}else if(e.getOldState() == Frame.MAXIMIZED_BOTH) 
		{
			lamina.setBackground(Color.black);
		}
		
	}

}
