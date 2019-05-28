package aleatorio;

public class Aleatorio {

	public static void main(String[] args) {
		
		int minimo = 5;
		int maximo = 10;
		
		int longitud = 3;
		
		if ((maximo - minimo) >= (longitud - 1)) {
			
			int numero_elementos = 0;
			int numeros[] = new int [longitud];
			boolean encontrado = false;
			int aleatorio;
			
	
			while (numero_elementos < longitud) {
				
				aleatorio = generaNumerosAleatorios(minimo - 1, maximo + 1);
				
				encontrado = false;
				
				for (int i = 0; i < numeros.length && !encontrado; i++) {
					
					if (aleatorio == numeros[i]) {
						encontrado = true;
					}
				}
				
				if (!encontrado) {
					
					numeros[numero_elementos++] = aleatorio;
					
					System.out.println("Se insert� un n�mero " + aleatorio);
					
				} else {
					
					System.out.println("El n�mero existe " + aleatorio);
				}
			}
			
			System.out.println("Mostrar arreglo");
			
			for (int i = 0; i < numeros.length; i++) {
				
				System.out.println(numeros[i]);			
			}
			
		} else {
			System.out.printf("No se puede proceder");
		}
		
		if (minimo > maximo) {
			
			int aux = minimo;
			maximo = minimo;
			maximo = aux;	
		}
	}
	
	public static int generaNumerosAleatorios(int minimo, int maximo) {
		
		int num = (int)Math.floor(Math.random()*(minimo - maximo + 1) + (maximo));
		
		return num;
	}
}
