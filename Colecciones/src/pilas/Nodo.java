package pilas;

public class Nodo<T>
{

    private final T elemento;
    private final Nodo<T> siguiente;

    public Nodo(T elemento, Nodo<T> siguiente)
    {
        this.elemento = elemento;
        this.siguiente = siguiente;
    }

    public T getElemento()
    {
        return elemento;
    }

    public Nodo<T> getSiguiente()
    {
        return siguiente;
    }

    @Override
    public String toString()
    {
        return elemento + "\n";
    }

}
