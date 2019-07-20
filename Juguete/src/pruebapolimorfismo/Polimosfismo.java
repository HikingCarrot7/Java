package pruebapolimorfismo;

public class Polimosfismo 
{

	public static void main(String[] args) 
	{
		
		Maestro maestro1 = new Maestro("Nicolás", 19);
		
		Persona persona1 = new Maestro("Javier", 23);
		
		maestro1.obtenerDatos();
		
		persona1.obtenerDatos();

	}

}

class Persona
{
	
	private String nombre;
	private int edad;
	
	public Persona(String nombre, int edad)
	{
		this.nombre = nombre;
		this.edad = edad;
	}
	
	public void obtenerDatos() 
	{
		System.out.println("Estoy dentro de la clase Persona");
	}
	
}

class Maestro extends Persona
{
	public Maestro(String nombre, int edad) 
	{
		super(nombre, edad);
	}
	
	@Override
	public void obtenerDatos() 
	{
		System.out.println("Estoy dentro de la clase Maestro");
	}
}
