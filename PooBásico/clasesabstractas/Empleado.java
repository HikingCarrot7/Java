package clasesabstractas;

public class Empleado extends Persona
{
	private String genero;
	private String id;
	private double sueldo;
	private String lugarTrabajo;
	
	Empleado(String nombre, String edad, String estadoCivil, boolean discapacidad, boolean usaLentes, String estadoAnimo, String genero, String id, double sueldo, String lugarTrabajo)
	{
		
		super(nombre, edad, estadoCivil, discapacidad, usaLentes, estadoAnimo);
		
		this.genero = genero;
		this.id = id;
		this.sueldo = sueldo;
		this.lugarTrabajo = lugarTrabajo;
		
	}
	
	public String genero() 
	{
		return "\nMi género es: " + genero;
		
		
	}
	
	public String toString() 
	{
		
		return super.toString() + "\nSoy un empleado. \nTrabajo en: " + lugarTrabajo + "\nMi id es: " + id +"\nMi sueldo es: $" + sueldo + genero();
		
	}

}
