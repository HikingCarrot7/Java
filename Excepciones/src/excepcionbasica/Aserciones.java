package excepcionbasica;

import java.util.Scanner;

public class Aserciones
{

    private static int num;

    public static void main(String[] args)
    {

        try (Scanner in = new Scanner(System.in))
        {
            System.out.println("Escriba un numero entre 0 - 10");
            num = in.nextInt();
            
            assert (num >= 0 && num <= 10) : "Numero incorrecto: " + num;

            System.out.println("Se escribio el numero: " + num);
        }

    }

}
