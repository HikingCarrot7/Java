package vehiculo_herencia;

public class Vehiculo 
{
	/*Campos de la clase*/
	private static int vehiculosRegistrados = 0;
	private double largo;
	private double ancho;
	private int pesoMotor;
	private int llantas;
	private int pesoPlataforma;
	private String placa;
	private String dueno;
	private String nombreVehiculo;
	
	/*Este constructor inicializa aquellos atributos que se comparten en todas las clases*/
	public Vehiculo(String dueno, String nombreVehiculo, String placa, double largo, double ancho)
	{
		/*El "this." hace distinción entre los campos de la clase y los parámetros que recibe el constructor*/
		this.dueno = dueno;
		this.nombreVehiculo = nombreVehiculo;
		this.placa = placa;
		this.largo = largo;
		this.ancho = ancho;
		
		/*Atributos por default*/
		vehiculosRegistrados++;
		pesoMotor = 120;
		llantas = 4;
		pesoPlataforma = 400; 
		
	}
	
	//Getter datos
	/*Getter para retornar los datos que se comparten entre todos los objetos (cada objeto tiene su propia copia de estos datos)*/
	public String toString() 
	{
		
		return "\nDueño: " + dueno + "\nNonmbre del vehículo: " + nombreVehiculo + "\nPlaca: " + placa + "\nLargo: " + largo + " M Ancho: " + ancho + " M";
		
	}
	
	//Setter llantas
	/*No todas los vehículos tienen las mismas llantas. Este método será sobrescrito por las demás clases*/
	public int getLlantas() 
	{
		return llantas;
		
	}

	//Setter pesoPlataforma
	public int getPesoPlataforma() 
	{
		
		return pesoPlataforma;
		
	}
	
	//Setter motor
	public int getPesoMotor() 
	{
		
		return pesoMotor; 
		
	}
	
	/*Los métodos static no le pertenecen a ningún objeto (son de clase). Accedemos a este método con Vehiculo.getVehiculosRegistrados en algún otro método*/
	public static String getVehiculosRegistrados() 
	{
		
		return "\nVehículos registrados: " + vehiculosRegistrados;
		
	}

}
