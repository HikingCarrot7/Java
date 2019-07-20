package clasesmetodosgenericos;

public class ClaseGenerica<T, U> 
{
	private T dato;
	private U dato2;
	
	public ClaseGenerica() 
	{
		dato = null;
	}
	
	public void anadirDatos(T dato, U dato2) 
	{
		this.dato = dato;
		this.dato2 = dato2;
	}
	
	public T obtenerDato() 
	{
		return dato;
	}
	
	public U obtenerDato2() 
	{
		return dato2;
	}
	
	public static <T extends Comparable<T>> T numElementos(T[] array) 
	{	
		if(array == null || array.length == 0)
			return null;
		
		T elementoMenor = array[0];
		
		for(int i = 1; i < array.length; i++) 
			if(elementoMenor.compareTo(array[i]) > 0) 
				elementoMenor = array[i];
		
		return elementoMenor;	
	}
}
