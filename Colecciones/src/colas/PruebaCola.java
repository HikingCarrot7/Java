package colas;

import java.util.Iterator;
import java.util.LinkedList;

public class PruebaCola 
{

	private static ColaDinamica<Integer> cola;
	
	public static void main(String[] args) 
	{
		cola = new ColaDinamica<Integer>();
		
		cola.enqueue(45);
		cola.enqueue(55);
		cola.enqueue(65);
		cola.enqueue(75);
		cola.enqueue(85);
		
		System.out.println(cola);
		
		LinkedList<Integer> lista = new LinkedList<>();
		
		lista.add(5);
		lista.add(15);
		lista.add(25);
		lista.add(35);
		
		lista.offer(45);
		
		lista.poll();
		
		Iterator<Integer> I = lista.iterator();
		
		while(I.hasNext())
			System.out.println(I.next());
		
	}

}
