package contructores;

public class PruebaEstudiante {

	public static void main(String[] args) {
		
		Promedio cuenta1 = new Promedio("Carlos Monroy", 76.25);
		Promedio cuenta2 = new Promedio("Luis Mena", 84.87);
		
		System.out.printf("La calificación en cálculo de %s es: %s%n", cuenta1.obtenerNombre(), cuenta1.obtenerCalificacionEstudiante());
		System.out.printf("La calificación en cálculo de %s es: %s%n", cuenta2.obtenerNombre(), cuenta2.obtenerCalificacionEstudiante());

	}

}
