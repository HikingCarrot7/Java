package empleado;

public class Administrador extends Empleado {
	
	private float bono;
	
	//Herencia
	public Administrador(String nombre, String roll, float sueldo, int edad, int anio, int mes, int dia, float bono)
	{
		super(nombre, roll, sueldo, edad, anio, mes, dia); //Llamar al constructor de la clase padre (Empleado)
		
		this.bono = bono;
	}
	
	public Administrador(String nombre) 
	{
		super(nombre);
		
		bono = 0;
	}
	
	public float getSueldo() 
	{
		return (float) (bono + super.getSueldo() * 1.1);
		
	}
	
	public String toString() 
	{
		return super.toString() + "\nTu bono por ser administrador es: $" + bono;
		
	}
	
}
