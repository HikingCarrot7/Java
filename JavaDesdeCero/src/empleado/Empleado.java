package empleado;

import java.util.Date;
import java.util.GregorianCalendar;

public class Empleado implements Comparable
{
	
	private int ticket;
	private int edad;
	private String nombre;
	private String roll;
	private float sueldo;
	private Date fechaAlta;
	
	public Empleado(String nombre, String roll, float sueldo, int edad, int anio, int mes, int dia) 
	{
		this.nombre = nombre;
		this.roll = roll;
		this.sueldo = sueldo;
		this.edad = edad;
		this.ticket = (int)Math.round(Math.random()*100);
		
		GregorianCalendar fecha = new GregorianCalendar(anio, mes - 1, dia);
		
		fechaAlta = fecha.getTime();
		
	}
	
	//Sobre carga de constructores
	public Empleado(String nombre, String roll) 
	{
		//Este this(); está llamando al otro constructor y le está mandando los siguientes parámetros
		this(nombre, roll, 0, 0, 2000, 1, 1);
		
	}
	
	@Override
	public String toString() 
	{
		
		return "\nRoll: " + roll + "\nNombre: " + nombre + "\nFecha de alta: " + fechaAlta + "\nTicket: EA" + ticket + "\nEdad: " + edad + "\nSueldo: $" + getSueldo();
		
	}
	

	//Getter sueldo
	public float getSueldo() 
	{
		
		return sueldo;
		
	}
	
	public int compareTo(Object miObjeto) 
	{
		if(this.sueldo < ((Empleado) miObjeto).sueldo) 
		{
			return -1;
			
		}else if(this.sueldo > ((Empleado) miObjeto).sueldo) 
		{
			return 1;
			
		}else 
		{
			return 0;
		}
	}
}
