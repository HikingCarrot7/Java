package productorconsumidor;

import static java.lang.System.out;
import static java.lang.Thread.sleep;
import java.util.Random;

public class Productor implements Runnable
{

    private final Buffer ubicacionCompartida;
    private final Random rand;

    public Productor(Buffer ubicacionCompartida)
    {
        this.ubicacionCompartida = ubicacionCompartida;
        rand = new Random();
    }

    @Override
    public void run()
    {
        int suma = 0;

        for (int i = 0; i < 10; i++)
            try
            {
                sleep(rand.nextInt(3000));
                ubicacionCompartida.establecer(i);
                suma += i;
                out.printf("\t%2d\n", suma);

            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

    }

}
