package entrada_jpanel;

import javax.swing.*;

public class EntradaJoptionPanel {
	
	public static void main(String[] args) 
	{
		
		String nombre = JOptionPane.showInputDialog("Inserta tu nombre: ");
		
		//Integer.parseInt(nombre);
		
		System.out.printf("Hola %s, muy b�sico", nombre);
		
		System.out.println();
		
	}
	
	

}
