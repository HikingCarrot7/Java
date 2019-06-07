package ciclos;

public class Interes {

	public static void main(String[] args) {
		
		double monto;
		double principal = 1000.0;
		double tasa = 0.05;
		
		System.out.printf("%s%20s%n", "Año", "Monto en deposito");
		
		for (int año = 1; año <= 10; ++año) {
			
			monto = principal * Math.pow(1.0 + tasa,  año);
			
			System.out.printf("%4d%,20.2f%n", año, monto);
		}


	}

}
