package excepcionbasica;

public class UsoDeExcepciones 
{

	public static void main(String[] args)
	{
		try 
		{
			LanzaExcepciones();
			
		}catch(IndexOutOfBoundsException e) 
		{
			System.out.println("Atrapé la excepción en método main");
			
			
		}
		
		noLanzaExcepcion();
		
	}
	
	public static void LanzaExcepciones() throws IndexOutOfBoundsException 
	{
		
		try 
		{
			System.out.println("Método lanza excepción");
			
			throw new IndexOutOfBoundsException();
			
		}catch(IndexOutOfBoundsException a) 
		{
			
			System.out.println("La Excepcion ha sido manejada");
			
			throw a;
			
		}finally 
		{
			System.out.println("Se ejecutó el finally xd");
		}
		
	}
	
	public static void noLanzaExcepcion()
	{
		try 
		{
			System.out.println("No lanzaré una excepción esta vez");
			
		}catch(Exception e)
		{
			System.out.println("No entraré aquí");
			
		}finally 
		{
			System.out.println("Adiós!");
		}
		
	}
	
}
