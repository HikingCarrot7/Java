package innerclass;

public class Empleados
{
	
	private String nombre;
	private String roll;
	private String id;
	private String descripcion;
	private double salario;
	private int edad;
	
	public Empleados(String nombre, int edad, String roll, double salario, String id) 
	{
		this.nombre = nombre;
		this.edad = edad;
		this.roll = roll;
		this.salario = salario;
		this.id = id;
		this.descripcion = getDescripcion();
	}
	
	public Empleados(String nombre, Empleados P) 
	{
		this.nombre = nombre;
		this.descripcion = P.getDescripcion();
	}
	
	public String getDescripcion() 
	{
		return "\nTengo " + edad + " años" + "\nMi roll es: " + roll + "\nMi sueldo es: " + salario + "\nMi ID es: " + id;
	}
	
	@Override
	public String toString() 
	{
		return "Mi nombre es " + nombre + descripcion;
	}

	public static void main(String[] args) 
	{
		
		Empleados[] empleados = new Empleados[4];
		
		empleados[0] = new Empleados("Nicolás", 19, "Programadores", 5400, "ALOP");
		
		empleados[1] = new Empleados("Ivan", 23, "Diseñadores", 6700, "EJKG");
		
		empleados[2] = new Empleados("Carlos", empleados[0]);
		
		empleados[3] = new Empleados("Fernando", empleados[1]);
		
		for(Empleados E: empleados) 
		{
			System.out.println(E);
			
			System.out.println("---------------------------");
			
		}
		
	}

}
