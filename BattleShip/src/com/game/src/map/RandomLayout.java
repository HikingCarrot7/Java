package com.game.src.map;

import java.util.Random;

/**
 *
 * @author HikingCarrot7
 */
public class RandomLayout
{

    private final Random rand;
    private final int[][] tempTablero;
    private int[][] tablero;

    public RandomLayout()
    {
        rand = new Random();
        tempTablero = new int[12][22];
        tablero = new int[10][20];

    }

    public int[][] generarTablero()
    {
        int longitud = 2, barcos = 0, fila, columna;
        boolean filaValida;

        do
        {

            do
            {
                filaValida = true;

                columna = rand.nextInt(19 - longitud) + 1;
                fila = rand.nextInt(10) + 1;

                for (int j = columna; j < columna + longitud; j++)
                {

                    if (tempTablero[fila][j] == 1 || tempTablero[fila][j - 1] == 1 || tempTablero[fila - 1][j] == 1 || tempTablero[fila][j + 1] == 1 || tempTablero[fila + 1][j] == 1)
                    {
                        filaValida = false;

                        break;
                    }

                }

                if (filaValida)
                {
                    for (int j = columna; j < columna + longitud; j++)
                    {
                        tempTablero[fila][j] = 1;

                    }

                }

            } while (!filaValida);

            longitud++;
            barcos++;

        } while (barcos < 5);
        
        //imprimirTablero();

        return copiarTableros(tablero, tempTablero, 1, -1);
    }

    private int[][] copiarTableros(int[][] tablero, int[][] tablero2, int inicio, int desface)
    {
        for (int i = inicio; i < tablero2.length + desface; i++)
        {
            for (int j = inicio; j < tablero2[i].length + desface; j++)
            {
                tablero[i + desface][j + desface] = tablero2[i][j];
            }
        }

        return tablero;
    }
    
    private void imprimirTablero()
    {
        for (int i = 0; i < tempTablero.length; i++)
        {
            for (int j = 0; j < tempTablero[i].length; j++)
            {
                System.out.print(tempTablero[i][j]);
            }
            
            System.out.println("");
        }
        
        System.exit(1);
    }
}

