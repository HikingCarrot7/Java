package alumos;

public class Alumnos 
{
	private String nombre;
	private int edad;
	
	public Alumnos(String nombre, int edad) 
	{
		this.nombre = nombre;
		this.edad = edad;
	}
	
	@Override
	public String toString() 
	{
		return String.format("%-20s %s", "Nombre: " + nombre, edad + " años.");
	}
	
}
