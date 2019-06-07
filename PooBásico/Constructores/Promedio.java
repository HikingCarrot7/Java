package contructores;

public class Promedio {

	private String nombre;
	private double promedio;
	
	public Promedio(String nombre, double promedio) {
		
		
		this.nombre = nombre;
		
		if (promedio > 0.0) {
			
			this.promedio = promedio;
		}
	
	}
	
	public void establecerNombre(String nombre) {
		
		this.nombre = nombre;
		
	}
	
	public String obtenerNombre() {
		
		return nombre;
	}
	
	public void establecerPromedio(double promedio) {
		
		if(promedio > 0.0) {
			if(promedio <= 100) {
				this.promedio = promedio;
			}
		}
	}
	
	public String obtenerCalificacionEstudiante() {
		
		String calificacionEstudiante = "";
		
		if(promedio >= 90) {
			calificacionEstudiante = "A";
		} else if (promedio >= 80) {
			calificacionEstudiante = "B";
		} else if (promedio >= 70) {
			calificacionEstudiante = "C";
		} else if (promedio >= 60) {
			calificacionEstudiante = "D";
		} else {
			calificacionEstudiante = "F";
		}
		
		return calificacionEstudiante;
	}
}
