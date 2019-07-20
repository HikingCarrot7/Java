package cartas;

public class PruebaCartas 
{

	public static void main(String[] args) 
	{
		
		PaqueteCartas miPaqueteCartas = new PaqueteCartas();
		
		miPaqueteCartas.barajar();
		
		for(int i = 1; i <= 52; i++) 
		{
			
			System.out.printf("%-20s", miPaqueteCartas.repartirCartas());
			
			if(i % 4 == 0) 
			{
				System.out.println("");
			}
			
		}
		
	}

}
