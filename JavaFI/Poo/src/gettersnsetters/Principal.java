package gettersnsetters;

import java.util.Scanner;

public class Principal
{

    public static void main(String[] args)
    {

        String nombre;

        Scanner in = new Scanner(System.in);

        System.out.println("Inserte su nombre");
        nombre = in.nextLine();

        Nombre nombre1 = new Nombre(nombre);

        System.out.println("Su nombre es: " + nombre1.obtenerNombre());

    }

}
