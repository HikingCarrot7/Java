package fuentes;

import java.awt.GraphicsEnvironment;

import javax.swing.JOptionPane;

public class MisFuentes {

	public static void main(String[] args) 
	{
		String fuente;
		boolean encontrado = false;
		
		String[] misFuentes = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		for(String misF: misFuentes) 
		{
			System.out.println(misF);
		}
		
		
		fuente = JOptionPane.showInputDialog("Inserta la fuente que quieres buscar");
		
		for(String misF: misFuentes) 
		{
			if(fuente.equals(misF)) 
			{
				encontrado = true;
				
				break;
				
			}
		}
		
		System.out.println(encontrado);

	}

}
