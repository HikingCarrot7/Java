package ciclos;

public class Interes 
{

	public static void main(String[] args) 
	{
		double monto;
		double principal = 1000.0;
		double tasa = 0.05;
		
		System.out.printf("%s%20s%n", "A�o", "Monto en deposito");
		
		for (int i = 1; i <= 10; ++i) 
		{
			monto = principal * Math.pow(1.0 + tasa,  i);
			
			System.out.printf("%4d%,20.2f%n", i, monto);
		}


	}

}
