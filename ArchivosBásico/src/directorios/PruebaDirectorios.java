package directorios;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

public class PruebaDirectorios
{

    private static File carpetaDireccion, archivoDireccion;
    private static final String carpeta = "C:\\Archivos\\Estrella", archivo = "C:\\Archivos\\Estrella\\Nicol�s.txt";

    public static void main(String[] args)
    {
        analizarRuta(carpeta, archivo);
    }

    public static void analizarRuta(String carpeta, String archivo)
    {
        carpetaDireccion = new File(carpeta);
        archivoDireccion = new File(archivo);

        //System.out.println(nombre.getAbsolutePath());
        if (carpetaDireccion.exists())
        {
            System.out.printf("%s%s%n%s%n%n", "La ruta existe", carpetaDireccion.isFile() ? " y es un archivo" : " y no es un archivo", carpetaDireccion.length());

            if (carpetaDireccion.isDirectory())
            {
                String[] directorio = carpetaDireccion.list();

                for (String direc : directorio)
                {
                    System.out.printf("%s%n", direc);

                    File f = new File(carpetaDireccion.getAbsolutePath(), direc);

                    if (f.isDirectory())
                    {
                        String[] subcarpeta = f.list();

                        for (String S : subcarpeta)
                            System.out.println(S);

                    }

                }

            }

        } else
        {
            carpetaDireccion.mkdir();

            try
            {
                archivoDireccion.createNewFile();

            } catch (IOException e)
            {

            }
        }

        escribirArchivo();

        //archivoDireccion.delete();
    }

    public static void escribirArchivo()
    {
        try
        {
            Formatter salida = new Formatter(new FileWriter(archivoDireccion.getAbsolutePath()));

            salida.format("%s%n", disenarMatriz());

            salida.close();

        } catch (IOException e)
        {

        }
    }

    public static String disenarMatriz()
    {
        String matriz = "";

        for (int i = 0; i < 20; i++)
        {
            for (int j = 0; j < 20; j++)
                if (i < 10 && j < 10)
                    matriz += "[0" + i + "][0" + j + "]";
                else if (i < 10)
                    matriz += "[0" + i + "][" + j + "]";
                else if (j < 10)
                    matriz += "[" + i + "][0" + j + "]";
                else
                    matriz += "[" + i + "][" + j + "]";

            matriz += System.getProperty("line.separator");

        }

        return matriz;
    }

}
