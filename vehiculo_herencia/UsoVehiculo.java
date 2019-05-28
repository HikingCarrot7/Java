package vehiculo_herencia;

public class UsoVehiculo 
{

	public static void main(String[] args) 
	{
		
		//Array de objetos de tipo vehiculo
		Vehiculo[] misVehiculos = new Vehiculo[3];
		
		misVehiculos[0] = new Camioneta("Marco Gonzalez", "Camioneta", "YZY-584-A", 2.5, 1.5, 250);
		misVehiculos[1] = new Motos("Luis Mena", "Moto", "YWZ-569-C", 1.2, 0.8, 2);
		misVehiculos[2] = new Triciclo("Iván Ojeda", "Triciclo", "PDA-458-B", 1.8, 1, 40);
		
		System.out.println("-->Vehículos<--");
		
		for(Vehiculo i: misVehiculos) 
		{
			/*Polimorfismo en acción. La variable i de tipo objeto llama al método toString de cada clase. Nótese que cada método toString de cada clase es diferente*/
			System.out.println(i);
		}
		
		/*Imprimimos el número de elementos de vehículos que se registraron (3). Se llama al método estático de la clase vehículo (este médodo le pertenece a la clase, no a 
		 * los objetos)*/
		System.out.println(Vehiculo.getVehiculosRegistrados());
		
	}

}
