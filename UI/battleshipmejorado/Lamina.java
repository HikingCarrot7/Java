package battleshipmejorado;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Lamina extends JPanel implements ActionListener, MouseListener
{
	private static final long serialVersionUID = 1L;
	
	private Tableros jugador1;
	private Tableros jugador2;
	
	private String contrasena1 = "";
	private String contrasena2 = "";
	
	private Timer timer;
	
	private boolean turno = false;
	
	public Lamina()
	{
		jugador1 = new Tableros();
		
		pedirDatos(1, contrasena1);
		
		turno = !turno;
		
		jugador2 = new Tableros();
		
		pedirDatos(2, contrasena2);
		
		turno = !turno;
		
		timer = new Timer(500, this);
		
		timer.start();
		
	}
	
	public void paint(Graphics g) 
	{
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		if(!turno) 
		{
			jugador1.dibujar((Graphics2D) g);
			
			g.setColor(Color.black);
			g.drawString("Es el turno del jugador 1", 50, 50);
			
		}else 
		{
			jugador2.dibujar((Graphics2D) g);
			g.setColor(Color.black);
			g.drawString("Es el turno del jugador 2", 50, 50);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		int y = (int) Math.floor((e.getX() - 210)/30);
		int x = (int) Math.floor((e.getY() - 535)/30);
		
		System.out.println(x); //Fila
		System.out.println(y); //Columna
		
		if(!turno) 
		{
			jugador2.modificarTableroEnemigo(1, x, y);
			
		}else 
		{
			jugador1.modificarTableroEnemigo(1, x, y);
			
		}
		
		turno = !turno;
		
	}
	
	public void pedirDatos(int x, String contrasena) 
	{
		
		new MiFrame(x);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		
		
	}
	
	private class MiFrame extends JFrame
	{
		private static final long serialVersionUID = 1L;
		
		private GuardarContrasena lamina;
		
		public MiFrame(int x) 
		{
			lamina = new GuardarContrasena();
			
			setBounds(0, 0, 500, 500);
			setLocationRelativeTo(null);
			setResizable(false);
			setTitle("Jugador " + x + ", necesitamos los siguientes datos");
			add(lamina);
			setVisible(true);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
	}
	
	private class GuardarContrasena extends JPanel
	{
		private static final long serialVersionUID = 1L;

		private JPasswordField contrasena;
		
		private JLabel info, respuesta;
		
		private JButton proceder;
		
		private JPanel infoPersona, comprobar;
		
		public GuardarContrasena()
		{
			setLayout(new BorderLayout());
			
			
			infoPersona = new JPanel();
			
			infoPersona.setLayout(new FlowLayout(FlowLayout.CENTER));
			
			contrasena = new JPasswordField(15);
			info = new JLabel("Contraseña: ");
			
			infoPersona.add(info);
			infoPersona.add(contrasena);
			
			
			comprobar = new JPanel();
			
			comprobar.setLayout(new BorderLayout());
			
			proceder = new JButton("Proceder :v");
			proceder.addActionListener(new AccionBoton());
			
			comprobar.add(proceder, BorderLayout.CENTER);
			
			
			respuesta = new JLabel("", JLabel.CENTER);
			
			add(infoPersona, BorderLayout.NORTH);
			add(respuesta, BorderLayout.CENTER);
			add(proceder, BorderLayout.SOUTH);
		}
		
		private class AccionBoton implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				char[] text;
				
				text = contrasena.getPassword();
				
				if(text.length < 8) 
				{
					respuesta.setText("Contraseña no válida");
					
				}else 
				{
					respuesta.setText("Contraseña capturada");
					
					if(!turno) 
					{
						copiarContrasena(text, contrasena1);
						
					}else 
					{
						copiarContrasena(text, contrasena2);
					}
					
				}
				
			}
			
			public void copiarContrasena(char[] text, String contrasena) 
			{
				for(int i = 0; i < text.length; i++) 
				{
					contrasena += text[i];
				}
				
			}
			
		}
		
	}
	
}
