package examenlisteners;

import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

public class MiFrame extends JFrame implements WindowStateListener, WindowListener, WindowFocusListener
{
	private static final long serialVersionUID = 1L;
	
	Lamina lamina;

	public MiFrame()
	{
		lamina = new Lamina();
		
		setResizable(true);
		setBounds(0, 0, 1000, 1000);
		setLocationRelativeTo(null);
		setVisible(true);
		add(lamina);
		addMouseMotionListener(lamina);
		addMouseListener(lamina);
		addWindowListener(this);
		addWindowStateListener(this);
		addWindowFocusListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) 
	{
		new MiFrame();

	}

	@Override
	public void windowStateChanged(WindowEvent e) 
	{
		if(e.getNewState() == Frame.MAXIMIZED_BOTH) 
		{
			lamina.generarCircular();
		}
		
		if(e.getOldState() == Frame.MAXIMIZED_BOTH) 
		{
			lamina.generarCircular();
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) 
	{
		lamina.generarZoom();
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
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
