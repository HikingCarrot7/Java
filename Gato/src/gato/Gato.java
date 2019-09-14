package gato;

import java.util.Scanner;

public class Gato
{

    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args)
    {
        jugar();
    }

    public static void jugar()
    {

        char tablero[][] = new char[3][3];
        char J1 = 'X', J2 = 'O', vacio = '-';
        boolean turno = true, validar, correcto;
        int filas, columnas;

        rellenarMatriz(tablero, vacio);

        while (!finPartida(tablero, vacio))
        {

            do
            {

                mostrarTurnoActual(turno);
                mostrarMatriz(tablero);

                correcto = false;

                filas = pedirInteger("Dame la fila");
                columnas = pedirInteger("Dame la columna");

                validar = validarPosicion(tablero, filas, columnas);

                if (validar)
                    if (!hayValorPosicion(tablero, filas, columnas, vacio))
                        correcto = true;
                    else
                        System.out.println("Esa posición ya está ocupada");
                else
                    System.out.println("Esa posición no existe");

            } while (!correcto);

            insertarEn(tablero, filas, columnas, turno ? J1 : J2);

            turno = !turno;

        }

        mostrarMatriz(tablero);
        mostrarGanador(tablero, J1, J2, vacio);

    }

    public static void mostrarGanador(char[][] matriz, char J1, char J2, char simboloDef)
    {

        char simbolo = coincidenciaLinea(matriz, simboloDef);

        if (simbolo != simboloDef)
            if (simbolo == J1)
                System.out.println("Ganó el jugador 1 por línea");
            else
                System.out.println("Ganó el jugador 2 por línea");

        simbolo = coincidenciaColumna(matriz, simboloDef);

        if (simbolo != simboloDef)
            if (simbolo == J1)
                System.out.println("Ganó el jugador 1 por columna");
            else
                System.out.println("Ganó el jugador 2 por columna");

        simbolo = coincidenciaDiagonal(matriz, simboloDef);

        if (simbolo != simboloDef)
            if (simbolo == J1)
                System.out.println("Ganó el jugador 1 por diagonal");
            else
                System.out.println("Ganó el jugador 2 por diagonal");

    }

    public static void insertarEn(char[][] matriz, int fila, int columna, char simboloJugador)
    {

        matriz[fila][columna] = simboloJugador;

    }

    public static void mostrarTurnoActual(boolean turno)
    {

        if (turno)
            System.out.println("Es el turno del jugador 1");
        else
            System.out.println("Es el turno del jugador 2");

    }

    public static int pedirInteger(String mensaje)
    {

        System.out.println(mensaje);
        int numero = teclado.nextInt();

        return numero;

    }

    public static boolean hayValorPosicion(char[][] matriz, int fila, int columna, char simboloDef)
    {
        return matriz[fila][columna] != simboloDef;
    }

    public static boolean validarPosicion(char[][] tablero, int fila, int columna)
    {
        return fila >= 0 && fila < tablero.length && columna >= 0 && columna < tablero.length;
    }

    public static void rellenarMatriz(char[][] matriz, char simbolo)
    {

        for (int i = 0; i < matriz.length; i++)
            for (int j = 0; j < matriz.length; j++)
                matriz[i][j] = simbolo;

    }

    public static void mostrarMatriz(char[][] matriz)
    {

        for (int i = 0; i < matriz.length; i++)
        {
            for (int j = 0; j < matriz[0].length; j++)
                System.out.print(matriz[i][j] + " ");

            System.out.println("");

        }

    }

    public static boolean matrizLlena(char[][] matriz, char simboloDef)
    {

        for (int i = 0; i < matriz.length; i++)
            for (int j = 0; j < matriz[0].length; i++)
                if (matriz[i][j] == simboloDef)
                    return false;

        return true;

    }

    public static boolean finPartida(char[][] matriz, char simboloDef)
    {

        return matrizLlena(matriz, simboloDef)
                || coincidenciaLinea(matriz, simboloDef) != simboloDef
                || coincidenciaColumna(matriz, simboloDef) != simboloDef
                || coincidenciaDiagonal(matriz, simboloDef) != simboloDef;

    }

    public static char coincidenciaLinea(char[][] matriz, char simboloDef)
    {

        char simbolo;
        boolean coincidencia;

        for (char[] linea : matriz)
        {
            simbolo = linea[0];
            coincidencia = true;

            if (simbolo != simboloDef)
                for (int i = 1; i < linea.length; i++)
                    if (simbolo != linea[i])
                        coincidencia = false;

            if (coincidencia)
                return simbolo;

        }

        return simboloDef;

    }

    public static char coincidenciaColumna(char[][] matriz, char simboloDef)
    {

        char simbolo;
        boolean coincidencia;

        for (int j = 0; j < matriz.length; j++)
        {

            simbolo = matriz[0][j];
            coincidencia = true;

            if (simbolo != simboloDef)
                for (int i = 1; i < matriz[0].length; i++)
                    if (simbolo != matriz[i][j])
                        coincidencia = false;

            if (coincidencia)
                return simbolo;

        }

        return simboloDef;

    }

    public static char coincidenciaDiagonal(char[][] matriz, char simboloDef)
    {

        char simbolo;
        boolean coincidencia = true;

        //Principal
        simbolo = matriz[0][0];

        if (simbolo != simboloDef)
            for (int i = 0; i < matriz.length; i++)
                if (simbolo != matriz[i][i])
                    coincidencia = false;

        if (coincidencia)
            return simbolo;

        coincidencia = true;

        //Diagonal inversa
        simbolo = matriz[0][2];

        if (simbolo != simboloDef)
            for (int i = 1, j = 1; i < matriz.length; i++, j--)
                if (simbolo != matriz[i][j])
                    coincidencia = false;

        if (coincidencia)
            return simbolo;

        return simboloDef;

    }

}
