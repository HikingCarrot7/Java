package strings;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PruebaStrings extends Object
{

	public static void main(String[] args)
	{
		Clientes cliente1 = new Clientes("Nicolas", (byte) 19), clientes2 = new Clientes("Nicolas", (byte) 19);

		// System.out.println(cliente1.mostrarDatos() == clientes2.mostrarDatos()); //
		// esto es una coñada, debe usarse el
		// equals de object
		
		Pattern p = Pattern.compile("o.*\\d");
		
		String c = "o 8";
		
		Matcher m = p.matcher(c);
		
		Locale l = new Locale("Spanish", "Mexico");
		
		int i = 20;
		
		System.out.println(i == 20);
		
		LinkedList<String> nombres = new LinkedList<>();
		
		nombres.add("Nicolas");
		nombres.add("Nicolas");
		nombres.add("Nicolas");
		nombres.add("Nicolas");
		nombres.add("Nicolas");
		nombres.add("Nicolas");
		nombres.add("Nicolas");
		nombres.add("Nicolas");
		nombres.add("Nicolas");
		
		
		long lastTime = System.currentTimeMillis();
		
		for (long j = 0; j < 1000000000; j++)
		{
			nombres.get(5);
		}
		
		System.out.println((System.currentTimeMillis() - lastTime) + " milliseconds");
		
		while(m.find()) 
			System.out.println(m.group());
		
		System.out.println(cliente1.mostrarDatos());

		String estado = "Nicolas";

		System.out.println(estado.matches("[a-zA-Z]+([ '-][a-zA-Z]+)*"));

	}

}

class Clientes
{

	private String nombre;
	private byte edad;

	public Clientes(String nombre, byte edad)
	{
		this.nombre = nombre;
		this.edad = edad;
	}

	public String mostrarDatos()
	{

		return String.format("Mi nombre es: %-20s Mi edad es: %s", nombre, edad);
		
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + edad;

		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Clientes other = (Clientes) obj;

		if (edad != other.edad)
			return false;

		return true;
	}

}
