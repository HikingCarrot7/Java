package directorios;

import java.io.File;
import java.util.Scanner;

public class PruebaDirectorios 
{
	private static Scanner in;
	
	private File nombre;

	public PruebaDirectorios() 
	{

		in = new Scanner(System.in);
		
	}
	
	public static void main(String[] args) 
	{
		
		System.out.println("Ruta: ");
		
		new PruebaDirectorios().analizarRuta(in.nextLine());
		

	}
	
	public void analizarRuta(String ruta) 
	{
		
		nombre = new File(ruta);
		
		if(nombre.exists()) 
		{
			System.out.printf("%s%s%n%s%n%n", "La ruta existe", nombre.isFile() ? " y es un archivo" : " y no es un archivo", nombre.length());
			
			if(nombre.isDirectory()) 
			{
				String[] directorio = nombre.list();
				
				for(String D: directorio)
					System.out.printf("%s%n", D);
			}
			
		}
	}

}
