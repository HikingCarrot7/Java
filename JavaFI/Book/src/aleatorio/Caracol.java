package aleatorio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Caracol
{

    public static void main(String[] args) throws IOException
    {

        System.out.println("Introduce algo...");

        try
        {
            System.out.println(new BufferedReader(new InputStreamReader(System.in)).readLine());

        } catch (IOException e)
        {
            System.out.println("Hubo un error...");
        }

        int matriz[][] = new int[5][5];
        int n = 5, inicio = 0, nlimite = n - 1, c = 1;

        while (c <= (n * n))
        {
            for (int i = inicio; i <= nlimite; i++)
                matriz[inicio][i] = c++;

            for (int i = inicio + 1; i <= nlimite; i++)
                matriz[i][nlimite] = c++;

            for (int i = nlimite - 1; i >= inicio; i--)
                matriz[nlimite][i] = c++;

            for (int i = nlimite - 1; i >= inicio + 1; i--)
                matriz[i][inicio] = c++;

            inicio++;
            nlimite--;
        }

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
                System.out.print(matriz[i][j] + "\t");

            System.out.println(" ");
        }
    }
}
