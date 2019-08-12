package carreradecaballos;

import java.util.Observable;
import java.util.Random;

public final class Caballos extends Observable implements Runnable
{

    private final Random rand;
    private final String nombre;

    public Caballos(String nombre)
    {
        rand = new Random();

        this.nombre = nombre;
    }

    @Override
    public void run()
    {
        int porcentaje = 0;

        try
        {
            while (porcentaje < 100)
            {
                Thread.sleep(rand.nextInt(200) + 800);

                porcentaje += rand.nextInt(15) + 1;

                setChanged();
                notifyObservers(porcentaje);
                clearChanged();

            }

        } catch (InterruptedException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public String getNombre()
    {
        return nombre;
    }

}
