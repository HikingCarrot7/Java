package javabook;
import java.util.Scanner;

public class PruebaCliente {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		
		Cliente miCuenta = new Cliente();
		
		System.out.printf("El nombre inicial es: %s%n%n", miCuenta.obtenerNombre());	
		
		System.out.println("Introduzca el nombre");
		String elNombre = in.nextLine();
		miCuenta.establecerNombre(elNombre);
		System.out.println();
		
		System.out.printf("El nombre en el objeto miCuenta es:%n%s%n:", miCuenta.obtenerNombre());

	}

}
