package empleados;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MiFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private Lamina lamina;
	
	public MiFrame() 
	{
		
		lamina = new Lamina();
		
		setBounds(0, 0, 500, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		add(lamina);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	

	public static void main(String[] args) 
	{
		
		new MiFrame();
		
	}

}

class Lamina extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	public Lamina() 
	{
		ponerBotones( Color.blue, "Azul");
		ponerBotones(Color.green, "Verde");
		ponerBotones(Color.yellow, "Amarillo");
		
	}
	
	public void ponerBotones(Color color, String texto) 
	{
		
		JButton boton = new JButton(texto);
		
		boton.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				setBackground(color);
				
			}
			
		});
		
		add(boton);
	}

}
