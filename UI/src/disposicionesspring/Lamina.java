package disposicionesspring;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;

public class Lamina extends JPanel
{

	private static final long serialVersionUID = 1L;
	
	private JButton boton1, boton2, boton3;
	
	private SpringLayout miLayout;
	
	public Lamina() 
	{
		miLayout = new SpringLayout();
		
		setLayout(miLayout);
		
		iniciarElementos();
		
		Spring miMuelle = Spring.constant(10, 10, 100);
		
		Spring rigido = Spring.constant(10);
		
		miLayout.putConstraint(SpringLayout.WEST, boton1, miMuelle, SpringLayout.WEST, this);
		
		miLayout.putConstraint(SpringLayout.WEST, boton2, rigido, SpringLayout.EAST, boton1);
		
		miLayout.putConstraint(SpringLayout.WEST, boton3, rigido, SpringLayout.EAST, boton2);
		
		miLayout.putConstraint(SpringLayout.EAST, this, miMuelle, SpringLayout.EAST, boton3);
		
	}
	
	public void iniciarElementos() 
	{
		boton1 = new JButton("Botón 1");
		
		boton2 = new JButton("Botón 2");
		
		boton3 = new JButton("Botón 3");
		
		add(boton1);
		add(boton2);
		add(boton3);
		
	}

}
