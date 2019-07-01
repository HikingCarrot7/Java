package excepcionbasica;

public class UsoExcepciones 
{

	public static void main(String[] args) 
	{	
		try 
		{
			metodo1();
			
		}catch(Exception e) 
		{
			e.printStackTrace();
			
			/*System.err.printf("%s", e.getMessage());
			
			StackTraceElement[] elementos = e.getStackTrace();
			
			System.out.println("\nRatreo de la pila de getStackTrace: ");
			
			System.out.printf("\nClase\t\t\t\tArchivo\t\t\tLínea\tMétodo\n");
			
			for(StackTraceElement E: elementos) 
				System.out.printf("%s\t%s\t%s\t%s\n", E.getClassName(), E.getFileName(), E.getLineNumber(), E.getMethodName());*/
		}
		
	}
	
	public static void metodo1() throws Exception
	{
		try 
		{
			metodo2();
			
		}catch(Exception e) 
		{
			throw new Exception("Causé otra excepción en el método 1", e);
		}
	}
	
	public static void metodo2() throws Exception
	{
		try 
		{
			metodo3();
			
		}catch(Exception e) 
		{
			throw new Exception("Causé otra excepción en el método 2", e);
		}
	}
	
	public static void metodo3() throws Exception
	{
		throw new Exception("Causé una excepción en el método 3");
	}

}
