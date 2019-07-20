package excepcionbasica;

import java.util.Scanner;

public class Aserciones 
{
	
	private static int num;

	public static void main(String[] args) 
	{
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Escriba un número entre 0 - 10");
		num = in.nextInt();
		
		assert (num >= 0 && num <= 10) : "Número incorrecto: " + num;
		
		System.out.println("Se escribió el número: " + num);
		
		in.close();
		
	}

}
