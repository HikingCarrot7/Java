package cartas;

import java.security.SecureRandom;

public class PaqueteCartas
{

    private static final int NUMERO_DE_CARTAS = 52;
    private Cartas[] paquete;
    private int cartaActual;

    private static final SecureRandom numerosAleatorios = new SecureRandom();

    public PaqueteCartas()
    {
        String caras[] =
        {
            "As", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "Ocho", "Nueve", "Diez", "Joker", "Reina", "Rey"
        };

        String palos[] =
        {
            "Corazones", "Diamantes", "Tréboles", "Espadas"
        };

        cartaActual = 0;

        paquete = new Cartas[NUMERO_DE_CARTAS];

        for (int cuenta = 0; cuenta < paquete.length; cuenta++)
            paquete[cuenta] = new Cartas(caras[cuenta % 13], palos[cuenta / 13]);

    }

    public void barajar()
    {
        cartaActual = 0;

        for (int primera = 0; primera < paquete.length; primera++)
        {
            int segunda = numerosAleatorios.nextInt(NUMERO_DE_CARTAS);

            Cartas temp = paquete[primera];

            paquete[primera] = paquete[segunda];

            paquete[segunda] = temp;

        }

    }

    public Cartas repartirCartas()
    {
        if (cartaActual < paquete.length)
            return paquete[cartaActual++];

        else
            return null;

    }
}
