package banco;

public class BancoSinSincronizar
{

    public static void main(String[] args)
    {
        Banco b = new Banco();

        for (int i = 0; i < 100; i++)
        {
            Thread hilo = new Thread(new EjecucionTransferencia(b, i, 2000));

            hilo.start();

        }

    }

}
