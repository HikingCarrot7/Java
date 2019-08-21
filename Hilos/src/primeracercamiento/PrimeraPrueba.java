package primeracercamiento;

public class PrimeraPrueba
{

    public static void main(String[] args)
    {
        Thread hilo1 = new Thread(new imprimirMensaje("Hilo 1", 5000));

        new Thread(new imprimirMensaje("Hilo 2", 10000)).start();

        new Thread(new imprimirMensaje("Hilo 3", 1000)).start();

        hilo1.start();

        hilo1.interrupt();

    }

}

class imprimirMensaje implements Runnable
{

    private String nombre;
    private int tiempo;

    public imprimirMensaje(String nombre, int tiempo)
    {
        this.nombre = nombre;
        this.tiempo = tiempo;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Thread.sleep(tiempo);

            } catch (InterruptedException e)
            {
            }

            System.out.println("Esta es una prueba con el " + nombre);
        }

    }

}
