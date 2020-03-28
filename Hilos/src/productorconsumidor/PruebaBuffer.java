package productorconsumidor;

import static java.lang.System.out;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.util.concurrent.Executors.newCachedThreadPool;

public class PruebaBuffer
{

    public static void main(String[] args)
    {
        ExecutorService aplication = newCachedThreadPool();
        Buffer ubicacionCompartida = new BufferBloqueo();

        out.println("Accion\t\t\tValor\tSuma producidos\tSuma consumidos");
        out.println("------\t\t\t-----\t---------------\t---------------\n");

        aplication.execute(new Productor(ubicacionCompartida));
        aplication.execute(new Consumidor(ubicacionCompartida));
        aplication.shutdown();
    }
}
