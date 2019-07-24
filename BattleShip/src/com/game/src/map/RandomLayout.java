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

        rellenarTablero(true, 5, 2);
        System.out.println("Hola");
        rellenarTablero(false, 3, 3);

        //imprimirTablero();
        return copiarTableros(tablero, tempTablero, 1, -1);
    }

    private void rellenarTablero(boolean horizontal, int MAXBARCOS, int LONGBARCOACTUAL)
    {
        int barcos = 0, columna, fila, posicionJ, posicionI;
        boolean posicionValida;

        do
        {

            do
            {
                posicionValida = true;

                columna = horizontal ? rand.nextInt(19 - LONGBARCOACTUAL) + 1 : rand.nextInt(10 - LONGBARCOACTUAL) + 1;
                fila = horizontal ? rand.nextInt(10) + 1 : rand.nextInt(19) + 1;

                posicionJ = horizontal ? columna : fila;
                posicionI = horizontal ? fila : columna;

                for (int i = posicionI, j = posicionJ; (horizontal ? j : i) < (horizontal ? posicionJ : posicionI) + LONGBARCOACTUAL;)
                {
                    if (tempTablero[i][j] == 1 || tempTablero[i][j - 1] == 1 || tempTablero[i - 1][j] == 1 || tempTablero[i][j + 1] == 1 || tempTablero[i + 1][j] == 1)
                    {
                        posicionValida = false;

                        break;
                    }

                    if (horizontal)
                    {
                        j++;

                    } else
                    {
                        i++;
                    }

                }

                if (posicionValida)
                {
                    for (int i = posicionI, j = posicionJ; (horizontal ? j : i) < (horizontal ? posicionJ : posicionI) + LONGBARCOACTUAL;)
                    {
                        tempTablero[i][j] = 1;

                        if (horizontal)
                        {
                            j++;
                            
                        } else
                        {
                            i++;
                        }

                    }

                }

            } while (!posicionValida);

            LONGBARCOACTUAL++;
            barcos++;

        } while (barcos < MAXBARCOS);
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
