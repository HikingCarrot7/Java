package ciclos;

import java.util.Scanner;

public class CalificacionesLetra 
{
	public static void main(String[] args) 
	{
		int total = 0;
		int contadorCalif = 0;
		int aCuenta = 0;
		int bCuenta = 0;
		int cCuenta = 0;
		int dCuenta = 0;
		int fCuenta = 0;
		
		Scanner in = new Scanner(System.in);
		
		System.out.printf("%s%n%s%n  %s%n", "Introduzca las calificaciones en el rango 0 - 100.", 
			"Escriba el indicador de fin de archivo para terminar la entrada: ", 
			"En windows escriba <Ctrl> z y oprima intro");

		while (in.hasNext())
		{
			int calificacion = in.nextInt();
			total += calificacion;
			++contadorCalif;
			
			switch (calificacion/10) 
			{
				
			case 9:
			case 10:
				++aCuenta;
				break;
				
			case 8:
				++bCuenta;
				break;
				
			case 7:
				++cCuenta;
				break;
				
			case 6:
				++dCuenta;
				break;
				
			default:
				++fCuenta;
				break;
			}
		}
		
		in.close();
		
		System.out.printf("%nReporte de calificaciones:%n");
		
		if (contadorCalif != 0) 
		{
		
		double promedio = (double) total/contadorCalif;
		
		System.out.printf("El total de las %d calificaciones es %d%n", contadorCalif, total);
		
		System.out.printf("El promedio de las clases es %.2f%n", promedio);
		
		System.out.printf("%n%s%n%s%d%n%s%d%n%s%d%n%s%d%n%s%d%n", "Número de estudiantes que recibieron cada calificación:",
				"A: ", aCuenta,
				"B: ", bCuenta,
				"C: ", cCuenta,
				"D: ", dCuenta,
				"F: ", fCuenta);
		}else 
			System.out.println("No se introdujeron Calificaciones");
	}
}
