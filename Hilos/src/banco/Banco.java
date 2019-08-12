package banco;

import java.util.concurrent.locks.Condition;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banco
{

    private final double[] cuentas;

    private Condition saldoSuficiente;

    private Lock cierre;

    public Banco()
    {
        cierre = new ReentrantLock();

        cuentas = new double[100];

        for (int i = 0; i < cuentas.length; i++)
        {
            cuentas[i] = 2000;
        }

        saldoSuficiente = cierre.newCondition();
    }

    public /*synchronized*/ void transferencia(int origen, int destino, double cantidad) throws InterruptedException
    {
        cierre.lock();

        try
        {
            while (cuentas[origen] < cantidad)
            {
                saldoSuficiente.await();
            }

            if (cuentas[origen] >= cantidad)
            {
                System.out.print(Thread.currentThread());

                cuentas[origen] -= cantidad;

                cuentas[destino] += cantidad;

                System.out.printf(" Se ha transferido %10.2f de la cuenta %d a la cuenta %d ", cantidad, origen, destino);

                System.out.printf("El saldo total es: $%10.2f %n", obtenerSaldo());

                saldoSuficiente.signalAll();

            }

        } finally
        {
            cierre.unlock();
        }

    }

    public double obtenerSaldo()
    {
        double total = 0;

        for (double A : cuentas)
        {
            total += A;
        }

        return total;
    }

}
