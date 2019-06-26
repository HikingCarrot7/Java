package exameninterface;

public class PruebaPersonas {

	public static void main(String[] args) 
	{
		
		Director director1 = new Director("Fernando Uicad", 56, 45600);
		
		Maestros maestro1 = new Maestros("Oscar Carballo", 29, "Cálculo", 15000);
		Maestros maestro2 = new Maestros("Richard Ojeda", 34, "Cultura maya", 10000);
		
		Tutor tutor1 = new Tutor("Edgar Cambranes", 36, "A1", 60000);
		
		Alumno alumno1 = new Alumno("Alexis Castillo", 24, "LIS", "B", 2018, 8, 9);
		Alumno alumno2 = new Alumno("Eyder Concha", 19, "LIS", "A", 2018, 8, 9);
		
		System.out.println(alumno1);
		
		System.out.println(tutor1);
		
		System.out.println(maestro2.levantarReporte(alumno1));
		
		System.out.println(director1.bono(tutor1));
		
		System.out.println(director1.expulsar(tutor1));
		
		System.out.println(maestro1.setCalificaciones(alumno2, 94.3));
		

	}

}
