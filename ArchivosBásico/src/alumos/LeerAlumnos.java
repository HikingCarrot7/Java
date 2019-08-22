package alumos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

public class LeerAlumnos
{

    private static Formatter salida;
    private static Scanner entrada;

    public static void escribirAlumnos(String nombre, int edad, int opcion)
    {
        try
        {
            salida = new Formatter(new FileWriter("C:\\Archivos\\Alumnos.txt", true));

            salida.format("%s%n%s%n%d%n%n", opcion == 1 ? "Matem�ticas" : "Historia", nombre, edad);

            salida.close();

            leerAlumnos();

        } catch (IOException e)
        {
            System.out.println("No se encuentra el archivo");
        }
    }

    public static void leerAlumnos()
    {
        try
        {
            entrada = new Scanner(new File("C:\\Archivos\\Alumnos.txt"));

            PruebaAlumnos.matematicas.clear();
            PruebaAlumnos.historia.clear();

            while (entrada.hasNext())
            {
                if (entrada.next().contentEquals("Matemáticas"))
                {
                    PruebaAlumnos.matematicas.add(new Alumnos(entrada.next(), entrada.nextInt()));
                    
                } else
                {
                    PruebaAlumnos.historia.add(new Alumnos(entrada.next(), entrada.nextInt()));
                }
            }

            entrada.close();

        } catch (FileNotFoundException e)
        {
            System.out.println("No se encuentra el archivo");
        }
    }
}
