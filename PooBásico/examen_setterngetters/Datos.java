package examen_setterngetters;

public class Datos {
	
	private String nombre;
	
	private float saldo;
	
	public Datos(String nombre, float saldo) 
	{
		this.nombre = nombre;
		this.saldo = saldo;
		
	}
	
	
	//Método setter para el nombre
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
		
	}
	
	//Método getter para el nombre
	
	public String getNombre() 
	{
		
		return nombre;
		
	}
	
	//Método setter para el saldo
	public void setSaldo(float saldo) 
	{
		if((saldo < 0 && this.saldo + saldo >= 0) || saldo > 0) 
		{
			this.saldo += saldo;
		
		}
		
	}
	
	//Método getter para el saldo
	public float getSaldo() 
	{
		
		return saldo;
		
	}
	
}
