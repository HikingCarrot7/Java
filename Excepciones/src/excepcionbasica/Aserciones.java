package excepcionbasica;

import java.util.Scanner;

public class Aserciones 
{
	
	private static int num;

	public static void main(String[] args) 
	{
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Escriba un n�mero entre 0 - 10");
		num = in.nextInt();
		
		assert (num >= 0 && num <= 10) : "N�mero incorrecto: " + num;
		
		System.out.println("Se escribi� el n�mero: " + num);
		
		in.close();
		
	}

}
