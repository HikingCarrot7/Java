package productorconsumidor;

public interface Buffer
{

    public void establecer(int valor) throws InterruptedException;

    public int obtener() throws InterruptedException;

}
