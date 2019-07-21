package pilas;

public class PilaDinamica<T>
{

    private Nodo<T> top;

    private int tamano;

    public PilaDinamica()
    {
        top = null;
    }

    public boolean isEmpty()
    {
        return top == null;
    }

    public int size()
    {
        return tamano;
    }

    public T top()
    {
        if (isEmpty())
        {
            return null;
        }

        return top.getElemento();
    }

    public T pop()
    {
        if (isEmpty())
        {
            return null;
        }

        T elemento = top.getElemento();

        Nodo<T> aux = top.getSiguiente();

        top = null;

        top = aux;

        tamano--;

        return elemento;

    }

    public void push(T elemento)
    {
        Nodo<T> aux = new Nodo<>(elemento, top);

        top = aux;

        tamano++;
    }

    @Override
    public String toString()
    {
        if (isEmpty())
        {
            return "No hay elementos en la pila";
        }

        String resultado = "";

        Nodo<T> aux = top;

        while (aux != null)
        {
            resultado += aux.toString();

            aux = aux.getSiguiente();
        }

        return resultado;

    }

}
