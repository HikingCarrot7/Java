package tiposenumerados;

public enum Compañeros 
{
	
	//Declaramos las contantes de tipo enum
	
	Jimmy("Una persona amable", "23 años"),
	Pau("Un agradable sujeto", "20 años"),
	Fernando("Aún le falta por aprender", "19 años"),
	Mena("Muy vale madres xD", "33 años"),
	Carlos("El gringo prieto", "26 años");
	
	private String descripcion;
	private String edad;
	
	private Compañeros(String descripcion, String edad) 
	{
		this.descripcion = descripcion;
		this.edad = edad;
	}
	
	public String getDescripcion() 
	{
		return descripcion;
	}
	
	public String getEdad() 
	{
		return edad;
	}

}
