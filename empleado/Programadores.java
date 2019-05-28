package empleado;

public class Programadores extends Empleado {
	
	private float bono;
	
	public Programadores(String nombre, String roll, float sueldo,int edad, int anio, int mes, int dia, float bono) 
	{
		super(nombre, roll, sueldo, edad, anio, mes, dia);
		this.bono = bono;
		
	}
	
	//Sobrecarga de constructores
	public Programadores(String nombre) 
	{
		super(nombre);
		
		bono = 0;
		
	}
	
	public float getSueldo() 
	{
		return (float) (bono + super.getSueldo() * 1.15);
		
	}
	
	public String toString() 
	{
		return super.toString() + "\nTu bono por ser programador es: $" + bono;
		
	}
	
}
