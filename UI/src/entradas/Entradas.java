package entradas;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Entradas 
{
	
	private static String[] palabras = {"Nicol�s", "Carlos"}; 

	public static void main(String[] args) 
	{
		Object xd = JOptionPane.showInputDialog(null, "Estrellas", "Pruebas", JOptionPane.ERROR_MESSAGE, new ImageIcon(""), palabras, null);
		
		System.out.println(xd);
	}

}
