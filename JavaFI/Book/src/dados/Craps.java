package dados;

import java.security.SecureRandom;

public class Craps
{

    private static final SecureRandom numerosAleatorios = new SecureRandom();

    private enum Estado
    {

        CONTINUA, GANO, PIERDO
    };

    private static final int DOS_UNOS = 2;
    private static final int TRES = 3;
    private static final int SIETE = 7;
    private static final int ONCE = 11;
    private static final int DOCE = 12;

    public static void main(String[] args)
    {

        int miPunto = 0;
        Estado estadoJuego;

        int sumaDeDados = tirarDados();

        switch (sumaDeDados)
        {

            case SIETE:
            case ONCE:
                estadoJuego = Estado.GANO;
                break;

            case DOS_UNOS:
            case TRES:
            case DOCE:
                estadoJuego = Estado.PIERDO;
                break;

            default:
                estadoJuego = Estado.CONTINUA;
                miPunto = sumaDeDados;
                System.out.printf("Mi punto es: %d%n", miPunto);
                break;

        }

        while (estadoJuego == Estado.CONTINUA)
        {

            sumaDeDados = tirarDados();

            if (sumaDeDados == miPunto)
                estadoJuego = Estado.GANO;

            else if (sumaDeDados == SIETE)
                estadoJuego = Estado.PIERDO;
        }

        if (estadoJuego == Estado.GANO)
            System.out.printf("El jugador gana");

        else
            System.out.printf("El jugador pierde");
    }

    public static int tirarDados()
    {

        int dado1 = 1 + numerosAleatorios.nextInt(6);
        int dado2 = 1 + numerosAleatorios.nextInt(6);

        int suma = dado1 + dado2;

        System.out.printf("El jugador tir� %d + %d = %d%n", dado1, dado2, suma);

        return suma;
    }
}
