package empleados;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Test 
{
	private String[] nombres = {"Fernando", "Nicol�s", "Carlos", "Luis"};

	private static Scanner in;
	
	public static void main(String[] args) 
	{
		new Test().main();
	}
	
	public void main() 
	{
		System.out.println("Java-is-cool");
		
		for(int i = 0; i < nombres.length; i++) 
		{
			if(i % 2 == 0) 
				continue;
			
			System.out.println(nombres[i]);
			
		}

		leerElementos();
	}
	
	public void leerElementos() 
	{
		in = new Scanner(System.in);
		
		try 
		{
			System.out.println("Inserte un n�mero");
			int num = in.nextInt();
			
			System.out.println("El n�mero que insertaste es: " + num);
			
		}catch(InputMismatchException e)
		{
			System.out.println("No insertaste un n�mero");
			
		}
		
	}
	
}