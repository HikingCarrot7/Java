package scanner_prueba;

import java.util.Scanner;

public class PruebaScanner
{

    public static void main(String[] args)
    {

        try (Scanner in = new Scanner(System.in))
        {
            String personas;

            System.out.print("Personas: ");
            personas = in.nextLine();

            System.out.printf("Las personas son: %s", personas);
        }

    }

}
