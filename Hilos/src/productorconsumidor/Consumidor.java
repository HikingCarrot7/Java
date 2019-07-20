package productorconsumidor;

import java.util.Random;

public class Consumidor implements Runnable
{
	private Buffer ubicacionCompartida;
	private Random rand;
	
	public Consumidor(Buffer ubicacionCompartida) 
	{
		this.ubicacionCompartida = ubicacionCompartida;
		
		rand = new Random();
		
	}

	@Override
	public void run()
	{
		int suma = 0;
		
		for (int i = 0; i < 10; i++)
		{
			
			try 
			{
				Thread.sleep(rand.nextInt(3000));
				
				suma += ubicacionCompartida.obtener();
				
				System.out.printf("\t\t\t%2d\n", suma);
				
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
		}
		
		System.out.printf("\nEl valor es: %2d\n", suma);
		
	}
	
}
