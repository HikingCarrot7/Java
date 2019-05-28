package aleatorio;

import java.util.Scanner;

public class MatrizAleatoria {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		int numSuerte[] = new int [5];
		int matrizAleatoria[][] = matriz();
		int diagonal = 0;
		int filas = 0;
		int columnas = 0;
		int cont = 0;
		
		for (int i = 0; i < 5; i++) {
			
			System.out.printf("Inserte sus n�meros de la suerte ");
			
			numSuerte[i] = in.nextInt();
		}
		
		for (int a = 0; a < 5; a++) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					
					if (numSuerte[a] == matrizAleatoria[i][j]) {
						
						cont++;
					} 
					
					if (i == j && numSuerte[a] == matrizAleatoria[i][j]) {
						
						diagonal++;
					}
				}
			}
		}
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				for (int a = 0; a < 5; a++) {
					
					if (numSuerte[a] == matrizAleatoria[i][j]) {
						
						filas++;
					}
				}
			}
			
			if (filas < 5) {
				
				filas = 0;
			}
		}
		
		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < 5; i++) {
				for (int a = 0; a < 5; a++) {
					
					if (numSuerte[a] == matrizAleatoria[i][j]) {
						
						columnas++;
					}
				}
			}
			
			if (columnas < 5) {
				
				columnas = 0;
			}
		}
		
		if (diagonal == 5) {
			
			System.out.printf("Insert� la diagonal principal, gan� $5");
			
		} else if (filas == 5){
			
			System.out.printf("Insert� una fila, gan� $5");
			
		} else if (columnas == 5) {
			
			System.out.printf("Insert� una columna, gan� $5");
			
		} else if (cont == 5) {
			
			System.out.printf("Recuperaste tus $2");
			
		} else {
			
			System.out.printf("Perdiste tus $2");
			
		}
	}
	
	public static int[][] matriz(){
		
		int matriz[][] = new int [5][5];
		
		int[] numeros = generarNumerosAleatoriosSinRepetir(1, 99, matriz.length*matriz[0].length); 
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				
				matriz[i][j] = numeros[(matriz.length * i) + j];
			}
		}
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				
				if (matriz[i][j] < 10) {
					
					System.out.print("[ 0" + matriz[i][j] + " ]");	
				} else {
					
					System.out.print("[ " + matriz[i][j] + " ]");
				}

				if (j == 4) {
					System.out.printf("\n");
				}		
			}
		}
		
		return matriz;
		
	}
	
	
	
	public static int[] generarNumerosAleatoriosSinRepetir(int minimo, int maximo, int longitud) {
		
		if (minimo > maximo) {
			
			int aux = minimo;
			maximo = minimo;
			maximo = aux;	
		}
		
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
				} 
			}
			
			return numeros;
			
		} else {
			
			System.out.printf("No se puede proceder");
			
			return null;
		}
		
	}
	
	public static int generaNumerosAleatorios(int minimo, int maximo) {
		
		int num = (int)Math.floor(Math.random()*(minimo - maximo + 1) + (maximo));
		
		return num;
	}
}

