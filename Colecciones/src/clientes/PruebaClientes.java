package clientes;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PruebaClientes
{

    private static Set<Clientes> clientes, clientes1;

    public static void main(String[] args)
    {
        Clientes c1 = new Clientes("Nicol�s", "01", 5000);
        Clientes c2 = new Clientes("Fernando", "02", 6000);
        Clientes c3 = new Clientes("Luis", "03", 7000);
        Clientes c4 = new Clientes("Carlos", "04", 8000);
        Clientes c5 = new Clientes("Nicol�s", "01", 5000);

        clientes = new HashSet<>();
        clientes1 = new HashSet<>();

        clientes.add(c1);
        clientes.add(c2);
        clientes.add(c3);
        clientes.add(c4);
        clientes.add(c5);
        
        for (Iterator<Clientes> cliente = clientes.iterator(); cliente.hasNext();)
        {
            System.out.println(cliente.next().getNombre());

        }

    }

}
