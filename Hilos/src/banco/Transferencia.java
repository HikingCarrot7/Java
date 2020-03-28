package banco;

import static java.lang.Math.random;
import static java.lang.System.out;
import static java.lang.Thread.sleep;

public class Transferencia implements Runnable
{

    private final Banco banco;
    private final int origen;
    private final double cantidadMax;

    public Transferencia(Banco banco, int origen, double cantidadMax)
    {
        this.banco = banco;
        this.origen = origen;
        this.cantidadMax = cantidadMax;
    }

    @Override
    public void run()
    {
        while (true)
            try
            {
                //int origen = (int) (Math.random() * 100);
                int destino = (int) (random() * 100);
                double cantidad = cantidadMax * random();
                banco.transfererir(origen, destino, cantidad);
                sleep(1);

            } catch (InterruptedException e)
            {
                out.println(e.getMessage());
            }

    }

}
