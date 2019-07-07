package banco;

public class EjecucionTransferencia implements Runnable
{
	private Banco banco;
	private int origen;
	private double cantidadMax;
	
	public EjecucionTransferencia(Banco banco, int origen, double cantidadMax) 
	{
		this.banco = banco;
		this.origen = origen;
		this.cantidadMax = cantidadMax;
	}

	@Override
	public void run() 
	{
		while(true) 
			try 
			{
				//int origen = (int) (Math.random() * 100);
				
				int destino = (int) (Math.random() * 100);
				
				double cantidad = cantidadMax * Math.random();
				
				banco.transferencia(origen, destino, cantidad);
				
				Thread.sleep(1);
				
			} catch (InterruptedException e) 
			{
				
			}
	}

}
