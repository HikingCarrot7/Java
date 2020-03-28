package banco;

public class BancoSincronizado
{

    public static void main(String[] args)
    {
        Banco b = new Banco();

        for (int i = 0; i < 100; i++)
        {
            Thread hilo = new Thread(new Transferencia(b, i, 2000));
            hilo.start();
        }

    }

}
