package tiposenumerados;

public enum Companeros 
{
	
	//Declaramos las contantes de tipo enum
	
	Jimmy("Una persona amable", "23 a�os"),
	Pau("Un agradable sujeto", "20 a�os"),
	Fernando("A�n le falta por aprender", "19 a�os"),
	Mena("Muy vale madres xD", "33 a�os"),
	Carlos("El gringo prieto", "26 a�os");
	
	private String descripcion;
	private String edad;
	
	private Companeros(String descripcion, String edad) 
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
