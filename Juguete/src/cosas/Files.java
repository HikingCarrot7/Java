package cosas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author HikingCarrot7
 */
public class Files
{

    private final File file;
    private final Formatter formatter;
    private final String[] nombres =
    {
        "Nicolás", "Iván", "Fernando", "Javier", "Luis", "Alexis", "Viviana", "Juan", "Carlos"
    };
    private final String ruta = "res//com//src//alumnos//Nombres.txt";
    private final Scanner in;

    public Files() throws FileNotFoundException, IOException
    {
        file = new File(ruta);

        if (!file.exists())
        {
            file.createNewFile();
        }

        formatter = new Formatter(new FileWriter(ruta, true));
        in = new Scanner(new FileReader(file));

    }

    public void writeFile()
    {
        Random rand = new Random();
        String saltoLinea = System.clearProperty("line.separator");

        if (!in.hasNext())
        {
            formatter.format("%-20s%-20s%s%4$s%4$s", "Nombres:", "Edades:", "Ahorros:", saltoLinea);
            
            for (int i = 0; i < 50; i++)
            {
                formatter.format("%-20s%-20s%s%s", nombres[rand.nextInt(nombres.length)], "Edad: " + rand.nextInt(50), String.format("Ahorros: $%.2f", Math.random() * 10000), saltoLinea);
            }

            formatter.close();
        }

    }

    public void readFile()
    {
        while (in.hasNext())
        {
            String currentLine = in.nextLine();

            System.out.println(currentLine);

            //String[] valores = currentLine.split("\\s+");

           // System.out.println(Arrays.toString(valores));

        }

    }

    public static void main(String[] args)
    {
        try
        {
            Files files = new Files();

            files.writeFile();

            files.readFile();

        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
