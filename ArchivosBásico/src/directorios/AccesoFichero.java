package directorios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AccesoFichero
{

    private static Fichero fichero;

    public static void main(String[] args)
    {
        fichero = new Fichero();

        fichero.leerArchivo();

        fichero.escribirArchivo();
    }

}

class Fichero
{

    private BufferedReader mibuffer;
    private BufferedWriter mibufferEscritura;

    public void leerArchivo()
    {
        try
        {
            mibuffer = new BufferedReader(new FileReader("C:/Archivos/Prueba.txt"));

            /*do
             {
             c = lectura.read();

             System.out.print((char) c);

             }while(c != -1);

             lectura.close();*/
            do
                System.out.println(mibuffer.readLine());
            while (mibuffer.readLine() != null);

            mibuffer.close();

        } catch (IOException e)
        {
            System.out.println("No est� el archivo");
        }
    }

    public void escribirArchivo()
    {
        String s = "Esto es una prueba de escritura";

        try
        {
            mibufferEscritura = new BufferedWriter(new FileWriter("C:/Archivos/Prueba.txt", true));

            mibufferEscritura.write(s);

            mibufferEscritura.close();

        } catch (IOException e)
        {
            System.out.println("No se encuentra el archivo");
        }
    }
}
