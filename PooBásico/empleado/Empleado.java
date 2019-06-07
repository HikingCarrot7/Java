package empleado;

import java.util.Date;
import java.util.GregorianCalendar;

public class Empleado {
	
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
	public Empleado(String nombre) 
	{
		//Este this(); está llamando al otro constructor y le está mandando los siguientes parámetros
		this(nombre, "Indefinido", 0, 0, 2000, 1, 1);
		
	}
	
	public String toString() 
	{
		
		return "\nRoll: " + roll + "\nNombre: " + this.nombre + "\nFecha de alta: " + this.fechaAlta + "\nTicket: EA" + ticket + "\nEdad: " + edad + "\nSueldo: $" + getSueldo();
		
	}
	

	//Getter sueldo
	public float getSueldo() 
	{
		
		return sueldo;
		
	}
	
}
