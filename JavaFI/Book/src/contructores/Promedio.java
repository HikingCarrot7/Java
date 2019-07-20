package contructores;

public class Promedio {

	private String nombre;
	private double promedio;
	
	public Promedio(String nombre, double promedio) 
	{
		this.nombre = nombre;
		
		if (promedio > 0.0) 
		{
			this.promedio = promedio;
		}
	
	}
	
	public String obtenerNombre() 
	{
		return nombre;
	}
	
	public String obtenerCalificacionEstudiante() 
	{
		
		String calificacionEstudiante = "";
		
		if(promedio >= 90) 
		{
			calificacionEstudiante = "A";
			
		} else if (promedio >= 80) 
		{
			calificacionEstudiante = "B";
			
		} else if (promedio >= 70) 
		{
			calificacionEstudiante = "C";
			
		} else if (promedio >= 60) 
		{
			calificacionEstudiante = "D";
			
		} else 
		{
			calificacionEstudiante = "F";
		}
		
		return calificacionEstudiante;
	}
	
	@Override
	public String toString() 
	{
		return "La calificación de " + obtenerNombre() + " es: " + obtenerCalificacionEstudiante();
	}
	
}
