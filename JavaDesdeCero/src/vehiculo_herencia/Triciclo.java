package vehiculo_herencia;

public class Triciclo extends Vehiculo
{
	
	private int carga;
	
	public Triciclo(String dueno, String nombreVehiculo, String placa, double largo, double ancho, int carga)
	{
		
		super(dueno, nombreVehiculo, placa, largo, ancho);
		
		this.carga = carga;
		
	}
	
	
	public int getLlantas() 
	{
		
		return super.getLlantas() - 1;
		
	}
	
	public String toString() 
	{
		
		return super.toString() + "\nLlantas: " + getLlantas() + "\nCarga: " + carga + " Kg";
		
	}
	

}
