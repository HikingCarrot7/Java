package treeset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Predicate;

public class Trees 
{
	public static void main(String[] args) 
	{
		TreeSet<Persona> personas = new TreeSet<>();
		
		personas.add(new Persona("Fernando", 24));
		personas.add(new Persona("Nicolas", 19));
		personas.add(new Persona("Luis", 17));
		personas.add(new Persona("Carlos", 18));
		
		System.out.println("Ordenados por edad...");
		
		for (Persona S : personas) 
			System.out.println(S);
		
		System.out.println("\n\nOrdenados por nombre...");
		
		ArrayList<Persona> ordenaNombres = new ArrayList<>();
		
		ordenaNombres.add(new Persona("Fernando", 24));
		ordenaNombres.add(new Persona("Nicolas", 19));
		ordenaNombres.add(new Persona("Luis", 17));
		ordenaNombres.add(new Persona("Carlos", 18));
		
		ordenaNombres.sort(new Comparator<Persona>() 
		{
			@Override
			public int compare(Persona p1, Persona p2) 
			{
				return p1.getNombre().compareTo(p2.getNombre());
			}
			
		});
		
		ordenaNombres.removeIf(new Predicate<Persona>() 
		{

			@Override
			public boolean test(Persona p) 
			{
				if(p.getNombre().equals("Fernando"))
					return true;
				else 
					return false;
			}
			
		});

		for (Persona S : ordenaNombres) 
			System.out.println(S);
	}

}

class Persona implements Comparable<Persona>
{
	private String nombre;
	private int edad;
	
	public Persona(String nombre, int edad) 
	{
		this.nombre = nombre;
		this.edad = edad;
	}

	@Override
	public int compareTo(Persona p) 
	{
		return edad - p.edad;
	}
	
	public String getNombre() 
	{
		return nombre;
	}
	
	@Override
	public String toString() 
	{
		return nombre;
	}
	
}
