package productorconsumidor;

public class BufferBloqueo implements Buffer
{
    //private final ArrayBlockingQueue<Integer>buffer;

    private int buffer = -1;
    private boolean ocupado = false;

    public BufferBloqueo()
    {
        //buffer = new ArrayBlockingQueue<>(1);
    }

    @Override
    public synchronized void establecer(int valor) throws InterruptedException
    {
        while (ocupado)
            wait();

        buffer = valor;
        ocupado = true;
        notifyAll();

        //System.out.printf("Productor escribe\t%2d", valor);
        //buffer.put(valor);
    }

    @Override
    public synchronized int obtener() throws InterruptedException
    {
        while (!ocupado)
            wait();

        ocupado = false;
        notifyAll();

        //valorLeido = buffer.take();
        //System.out.printf("\nConsumidor lee\t\t%2d", valorLeido);
        return buffer;
    }

}
