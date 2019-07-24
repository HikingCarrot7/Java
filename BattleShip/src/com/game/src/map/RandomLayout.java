package com.game.src.map;

import java.util.Random;

/**
 *
 * @author HikingCarrot7
 */
public class RandomLayout
{

    private final Random rand;
    private int[][] tempTablero;
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

        tempTablero = copiarAlTemp(tablero);

        do
        {

            do
            {
                filaValida = true;

                columna = rand.nextInt(19 - longitud) + 1;
                fila = rand.nextInt(9) + 1;

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

        tablero = copiarAlTablero(tempTablero, tablero);

        return tablero;
    }

    private int[][] copiarAlTemp(int[][] tablero)
    {
        for (int i = 0; i < tablero.length; i++)
        {
            for (int j = 0; j < tablero[i].length; j++)
            {
                tempTablero[i + 1][j + 1] = tablero[i][j];
            }
        }

        return tempTablero;
    }

    private int[][] copiarAlTablero(int[][] tempMatriz, int[][] tablero)
    {
        for (int i = 1; i < tempMatriz.length - 1; i++)
        {
            for (int j = 1; j < tempMatriz[i].length - 1; j++)
            {
                tablero[i - 1][j - 1] = tempMatriz[i][j];
            }
        }

        return tablero;
    }
}
