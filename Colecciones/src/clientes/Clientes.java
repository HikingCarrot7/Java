package clientes;

public class Clientes
{
	
	private String nombre, numCuenta;
	private double sueldo;
	
	public Clientes(String nombre, String numCuenta, double sueldo) 
	{
		this.nombre = nombre;
		this.numCuenta = numCuenta;
		this.sueldo = sueldo;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getNumCuenta()
	{
		return numCuenta;
	}

	public void setNumCuenta(String numCuenta)
	{
		this.numCuenta = numCuenta;
	}

	public double getSueldo() 
	{
		return sueldo;
	}

	public void setSueldo(double sueldo) 
	{
		this.sueldo = sueldo;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numCuenta == null) ? 0 : numCuenta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Clientes other = (Clientes) obj;
		
		if (numCuenta == null)
		{
			if (other.numCuenta != null)
				return false;
			
		} else if (!numCuenta.equals(other.numCuenta))
			return false;
		
		return true;
	}
	
//	@Override
//	public boolean equals(Object obj) 
//	{
//		if(obj instanceof Clientes) 
//			if(this.nombre == ((Clientes) obj).nombre)
//				return true;
//			else
//				return false;
//		
//		return false;
//	}
	
}
