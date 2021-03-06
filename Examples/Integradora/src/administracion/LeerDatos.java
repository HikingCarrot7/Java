package administracion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class LeerDatos
{

    private static Formatter salida;

    private static Scanner entrada;

    public static void anadirMaestros(Maestro maestro)
    {
        try
        {
            salida = new Formatter(new FileWriter("Maestros.txt", true));

            escrituraDatosMaestros(maestro);

            salida.close();

        } catch (IOException e)
        {
            System.out.println("No se puede acceder al archivo");
        }
    }

    public static void LeerMaestros()
    {
        try
        {
            entrada = new Scanner(new File("Maestros.txt"));

            //LaminaMaestro.maestrosInstanciados.clear();
            while (entrada.hasNext())
            {
                entrada.nextLine();

                LaminaMaestro.maestrosInstanciados.add(new Maestro(entrada.nextLine(), Integer.parseInt(entrada.nextLine()), entrada.nextLine(), Double.parseDouble(entrada.nextLine()), entrada.nextLine(), entrada.nextLine(), entrada.nextLine()));

                LaminaMaestro.alumnosInstancias.add(new ArrayList<>());

                entrada.nextLine();
            }

            entrada.close();

        } catch (FileNotFoundException e)
        {
            System.out.println("No se encontr� en archivo");
        }

    }

    public static void anadirAlumnos(Alumno alumno)
    {
        try
        {
            salida = new Formatter(new FileWriter("Alumnos.txt", true));

            escrituraDatosAlumnos(alumno, Login.indiceMaestro);

            salida.close();

        } catch (IOException e)
        {
            System.out.println("No se puede acceder al archivo");
        }
    }

    public static void leerAlumnos()
    {
        try
        {
            entrada = new Scanner(new File("Alumnos.txt"));

            //LaminaMaestro.maestrosInstanciados.clear();
            while (entrada.hasNext())
            {
                entrada.nextLine();

                LaminaMaestro.alumnosInstancias.get(Integer.parseInt(entrada.nextLine())).add(new Alumno(entrada.nextLine(), Integer.parseInt(entrada.nextLine()), entrada.nextLine(), Double.parseDouble(entrada.nextLine())));

                entrada.nextLine();
            }

            entrada.close();

        } catch (FileNotFoundException e)
        {
            System.out.println("No se encontr� en archivo");
        }
    }

    public static void actualizarListaMaestros()
    {
        try
        {
            salida = new Formatter(new FileWriter("Maestros.txt"));

            LaminaMaestro.maestrosInstanciados.stream().forEach((M) ->
            {
                escrituraDatosMaestros(M);
            });

            salida.close();

        } catch (IOException e)
        {
            System.out.println("No se encuentra el archivo");
        }
    }

    public static void actualizarListaAlumnos()
    {
        try
        {
            salida = new Formatter(new FileWriter("Alumnos.txt"));

            for (int i = 0; i < LaminaMaestro.alumnosInstancias.size(); i++)
                for (int j = 0; j < LaminaMaestro.alumnosInstancias.get(i).size(); j++)
                    escrituraDatosAlumnos(LaminaMaestro.alumnosInstancias.get(i).get(j), i);

            salida.close();

        } catch (IOException e)
        {
            System.out.println("No se encuentra el archivo");
        }

    }

    public static void escrituraDatosMaestros(Maestro maestro)
    {
        salida.format("%n%s%n%d%n%s%n%.2f%n%s%n%s%n%s%n%n", maestro.getNombre(), maestro.getEdad(), maestro.getAsignatura(), maestro.getSueldo(), maestro.getUsuario(), maestro.getContrasena(), maestro.getFecha());
    }

    public static void escrituraDatosAlumnos(Alumno alumno, int index)
    {
        salida.format("%n%d%n%s%n%d%n%s%n%.2f%n%n", index, alumno.getNombre(), alumno.getEdad(), alumno.obtenerLic(), alumno.obtenerCalif());
    }

}
