package empleados;

public class Empleados 
{
	
	private String nombre;
	private double sueldo;
	
	public Empleados(String nombre, double sueldo) 
	{
		this.nombre = nombre;
		this.sueldo = sueldo;
	}
	
	@Override
	public String toString() 
	{
		return "Tu nombre es: " + nombre + " y tu sueldo es: $" + sueldo;
	}
	
}
