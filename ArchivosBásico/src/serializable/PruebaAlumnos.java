package serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PruebaAlumnos
{

    private static Alumnos[] misAlumnos, alumnos;

    public static void main(String[] args)
    {
        misAlumnos = new Alumnos[2];

        alumnos = new Alumnos[2];

        misAlumnos[0] = new Alumnos("Nicol�s", 19);
        misAlumnos[1] = new Alumnos("Fernando", 20);

        alumnos[0] = new Alumnos("Luis", 23);
        alumnos[1] = new Alumnos("Javier", 26);

        escribirAlumnos();

        leerAlumnos();

    }

    public static void escribirAlumnos()
    {
        try
        {
            try (ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream("C:\\Archivos\\Prueba.txt")))
            {
                file.writeObject(misAlumnos);
                file.writeObject(alumnos);
            }

        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void leerAlumnos()
    {
        try
        {
            try (ObjectInputStream file = new ObjectInputStream(new FileInputStream("C:\\Archivos\\Prueba.txt")))
            {
                for (Alumnos A : (Alumnos[]) file.readObject())
                    System.out.println(A);

                for (Alumnos A : (Alumnos[]) file.readObject())
                    System.out.println(A);
            }

        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
