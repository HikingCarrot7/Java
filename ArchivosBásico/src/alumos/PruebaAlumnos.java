package alumos;

import java.util.ArrayList;
import java.util.Scanner;

public class PruebaAlumnos
{

    protected static ArrayList<Alumnos> matematicas, historia;

    private static Scanner in;

    public static void main(String[] args)
    {
        declarar();

        LeerAlumnos.leerAlumnos();

        preguntar();
    }

    public static void declarar()
    {
        in = new Scanner(System.in);

        matematicas = new ArrayList<>();

        historia = new ArrayList<>();

    }

    public static void preguntar()
    {
        System.out.println("\n1.- A�adir alumno\n2.- Mostrar a los alumnos de matem�ticas\n3.- Mostrar a los alumnos de historia");
        int opcion = in.nextInt();

        do
        {
            in.nextLine();

            switch (opcion)
            {

                case 1:

                    System.out.println("Inserte su nombre...");
                    String nombre = in.nextLine();

                    System.out.println("Inserte su edad...");
                    int edad = in.nextInt();

                    System.out.println("Inserte 1. si es de matem�ticas o 2. si es de historia");
                    int asignatura = in.nextInt();

                    LeerAlumnos.escribirAlumnos(nombre, edad, asignatura);

                    System.out.println("Alumno a�adido con �xito\n");

                    break;

                case 2:

                    imprimirAlumnos(matematicas);

                    break;

                case 3:

                    imprimirAlumnos(historia);

                    break;

                default:

                    break;
            }

            System.out.println("\n1.- A�adir alumno\n2.-Mostrar a los alumnos de matem�ticas\n3.-Mostrar a los alumnos de historia");
            opcion = in.nextInt();

            in.nextLine();

        } while (in.hasNext());
    }

    public static void imprimirAlumnos(ArrayList<Alumnos> alumnos)
    {
        if (alumnos.size() > 0)
            for (Alumnos A : alumnos)
                System.out.println(A);
        else
            System.out.println("No hay alumnos registrados");
    }
}
