package lectura;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Lectura
{

    public static void main(String[] args)
    {
        int[] imagen;

        try
        {
            FileInputStream file = new FileInputStream("C:\\Archivos\\lolis.jpg");

            imagen = new int[(int) file.getChannel().size()];

            for (int i = 0; i < file.getChannel().size(); i++)
                imagen[i] = file.read();

            file.close();

            crearFichero(imagen);

        } catch (IOException e)
        {
            System.out.println("No se encuentra el archivo");
        }

    }

    public static void crearFichero(int[] imagen)
    {
        try
        {
            FileOutputStream file = new FileOutputStream("C:\\Archivos\\lolis_copia.jpg");

            for (int i = 0; i < imagen.length; i++)
                file.write(imagen[i]);

            file.close();

        } catch (IOException e)
        {
            System.out.println("Error al crear el archivo");
        }
    }

}
