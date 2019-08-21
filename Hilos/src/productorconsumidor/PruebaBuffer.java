package productorconsumidor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PruebaBuffer
{

    public static void main(String[] args)
    {
        ExecutorService aplication = Executors.newCachedThreadPool();

        Buffer ubicacionCompartida = new BufferBloqueo();

        System.out.println("Accion\t\t\tValor\tSuma producidos\tSuma consumidos");

        System.out.println("------\t\t\t-----\t---------------\t---------------\n");

        aplication.execute(new Productor(ubicacionCompartida));

        aplication.execute(new Consumidor(ubicacionCompartida));

        aplication.shutdown();

    }
}
