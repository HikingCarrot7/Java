package directorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LibroLectura
{

    private static Formatter salida;

    private static Scanner entrada;

    private static final String[] nombres =
    {
        "Nicol�s", "Carlos", "Alejandro", "Luis", "Estrella", "Viviana"
    };

    public static void main(String[] args)
    {
        try
        {
            salida = new Formatter(new FileWriter("C:/Archivos/Prueba.txt", true));

            escribir();

            entrada = new Scanner(new File("C:/Archivos/Prueba.txt"));

            leer();

        } catch (SecurityException e)
        {
            System.out.println("No se puede acceder al archivo");

            System.exit(1);

        } catch (FileNotFoundException e)
        {
            System.out.println("No se encuentra el archivo");

            System.exit(1);

        } catch (IOException e)
        {

        }
    }

    public static void escribir()
    {
        for (String N : nombres)
            salida.format("%s%n%n", N);

        salida.flush();

        salida.close();

    }

    public static void leer()
    {

        try
        {
            while (entrada.hasNext())
                System.out.println(entrada.next());

        } catch (NoSuchElementException | IllegalStateException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
