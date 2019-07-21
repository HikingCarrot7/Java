package excepcionbasica;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExcepcionBasica
{

    private Scanner in;
    private boolean correcto = false;

    ExcepcionBasica()
    {
        in = new Scanner(System.in);
    }

    public static void main(String[] args)
    {
        new ExcepcionBasica().pedirDatos();

    }

    public void pedirDatos()
    {

        do
        {

            try
            {

                System.out.println("\nInserte un numerador");
                int num = in.nextInt();

                System.out.println("\nInserte un denominador");
                int denom = in.nextInt();

                double resultado = cociente(num, denom);

                System.out.printf("%nLa divisi�n es: %.5f", resultado);

                correcto = true;

            } catch (InputMismatchException e)
            {

                System.err.println("\nNo insertaste un n�mero!");

                in.nextLine();

            } catch (ArithmeticException e)
            {
                System.err.println("\nDivisi�n entre cero!");

                in.nextLine();
            }

        } while (!correcto);

    }

    public static double cociente(int num, int denom) throws ArithmeticException
    {
        return (double) num / denom;
    }

}
