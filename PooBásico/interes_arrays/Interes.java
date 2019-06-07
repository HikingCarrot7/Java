package interes_arrays;

public class Interes {

	public static void main(String[] args) {
	
		
		double acumulado;
		double interes = (float) 0.10;
		
		double saldo [][] = new double[5][6];
		
		
		for(int i = 0; i < 6; i++) 
		{
			
			saldo[0][i] = 10000;
			acumulado = 10000;
			
			for(int j = 1; j < 5; j++) 
			{
				acumulado += (acumulado * interes);
				
				System.out.printf("%1.2f  ", saldo[j][i] = acumulado);
				
			}
			
			System.out.println(" ");
			
			interes += 0.01; 
			
		}
	}

}
