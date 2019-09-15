package gato;

import java.util.Scanner;

public class CalculadoraDias
{

    public static void main(String[] args)
    {

        Scanner in = new Scanner(System.in);

        int year, diasFebrero, diaInicio, mesInicio, diaFinal, mesFinal;
        int total;

        System.out.println("Inserte el año");
        year = in.nextInt();

        System.out.println("Inserte el día");
        diaInicio = in.nextInt();

        System.out.println("Inserte el mes");
        mesInicio = in.nextInt();

        System.out.println("Inserte el día final");
        diaFinal = in.nextInt();

        System.out.println("Inserte el mes final");
        mesFinal = in.nextInt();

        if (bisiesto(year))
        {

            diasFebrero = 29;
            total = dias(diasFebrero, diaInicio, mesInicio, diaFinal, mesFinal);

        } else
        {

            diasFebrero = 28;
            total = dias(diasFebrero, diaInicio, mesInicio, diaFinal, mesFinal);
        }

        System.out.println("Los días restantes son: " + total);

    }

    public static boolean bisiesto(int year)
    {

        if (year % 100 == 0)
            return year % 400 == 0;

        else
            return year % 4 == 0;
    }

    public static int dias(int diasFebrero, int diaInicio, int mesInicio, int diaFinal, int mesFinal)
    {

        int totalDias;
        int sobra;
        int dif = mesFinal - mesInicio;
        int diaMes = 0;
        int vector[] = new int[12];

        vector[0] = 31;
        vector[1] = diasFebrero;
        vector[2] = 31;
        vector[3] = 30;
        vector[4] = 31;
        vector[5] = 30;
        vector[6] = 31;
        vector[7] = 31;
        vector[8] = 30;
        vector[9] = 31;
        vector[10] = 30;
        vector[11] = 31;

        if (mesInicio != mesFinal && dif != 1)
        {

            for (int i = mesInicio - 1; i < mesFinal - 2; i++)
                diaMes += vector[i + 1];

            sobra = vector[mesInicio - 1] - diaInicio;
            totalDias = sobra + diaMes + diaFinal;

            return totalDias;

        } else if (mesInicio == mesFinal)
        {

            totalDias = diaFinal - diaInicio;

            return totalDias;

        } else
        {

            sobra = vector[mesInicio - 1] - diaInicio;
            totalDias = sobra + diaFinal;

            return totalDias;

        }

    }

}
