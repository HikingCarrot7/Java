package primeracercamiento;

import static java.lang.System.out;

public class SicronizandoHilos
{

    public static void main(String[] args)
    {

        HilosVarios hilo1 = new HilosVarios();

        HilosVarios hilo2 = new HilosVarios(hilo1);

        hilo2.start();

        hilo1.start();

        out.println("Se han terminado las tareas");

    }

    private static class HilosVarios extends Thread
    {

        private Thread hilo;

        public HilosVarios()
        {
        }

        public HilosVarios(Thread hilo)
        {
            this.hilo = hilo;
        }

        @Override
        public void run()
        {
            try
            {
                if (hilo != null)
                    hilo.join();

                for (int i = 0; i < 5; i++)
                {
                    sleep(500);

                    out.println("Se est� ejecutando el hilo " + getName());

                }

            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }

}
