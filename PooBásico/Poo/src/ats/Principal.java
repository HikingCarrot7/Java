package ats;

public class Principal {

	public static void main(String[] args) {
		
		Vehiculo misVehiculos[] = new Vehiculo[5];
		
		misVehiculos[0] = new Vehiculo("GH67", "Ferrari", "A89"); 
		misVehiculos[1] = new VehiculoTurismo(4, "TY89", "Audi", "P14");
		misVehiculos[2] = new VehiculoTurismo(6, "TY21", "Aveo", "P187");
		misVehiculos[3] = new VehiculoDeportivo(500, "ZS43", "Toyota", "KJ8");
		misVehiculos[4] = new VehiculoFurgoneta(2000, "WA21", "Toyota", "HG3"); 
		
		for (Vehiculo vehiculos: misVehiculos) 
		{
			System.out.println(vehiculos.mostrarDatos());
			System.out.println(" ");
			
		}
		
		

	}

}
