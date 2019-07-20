package iteradores;

import java.util.ArrayList;
import java.util.Iterator;

public class Iterador 
{
	private static ArrayList<Integer> numeros;

	public static void main(String[] args)
	{
		numeros = new ArrayList<Integer>();
		
		numeros.add(new Integer(2));
		
		Iterator<Integer> num = numeros.iterator();
		
		while(num.hasNext())
			System.out.println(num.next());
		
	}

}
