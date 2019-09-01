package directorios;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EscribirArchivo
{

    private static Archivo archivo;

    public static void main(String[] args)
    {
        archivo = new Archivo();

        archivo.abrirArchivo();

        archivo.EscribirDatos();

        archivo.cerrarArchivo();

    }

}

class Archivo
{

    private Formatter salida;

    private Scanner in;

    public Archivo()
    {
        in = new Scanner(System.in);
    }

    public void abrirArchivo()
    {
        try
        {
            salida = new Formatter("src\\directorios\\PruebaArchivo.txt");

        } catch (SecurityException e)
        {

            System.out.println("No tiene acceso a la escritura de este archivo");

            System.exit(1);

        } catch (FileNotFoundException e)
        {

            System.out.println("No se encontr� el archivo");

            System.exit(1);

        }
    }

    public void EscribirDatos()
    {
        System.out.printf("%s\n%s\n%s\n%s\n\n",
                "Para terminar la entrada, escriba el indicador de fin de archivo ",
                "cuando se le pida que escriba los datos de entrada.",
                "En UNIX/Linux/Mac OS X escriba <ctrl> d y oprima Intro",
                "En Windows escriba <ctrl> z y oprima Intro");

        System.out.println("Escriba una palabra...");

        while (in.hasNext())
        {
            try
            {
                salida.format("%s%n%n", in.nextLine());

            } catch (FormatterClosedException e)
            {
                System.out.println("Error al escribir en el archivo.");
                in.nextLine();

            } catch (NoSuchElementException e)
            {
                System.out.println("Estrada no v�lida");
                in.nextLine();
            }

            System.out.println("Escriba una palabra...");

        }

    }

    public void cerrarArchivo()
    {
        if (salida != null)
            salida.close();
    }

}
