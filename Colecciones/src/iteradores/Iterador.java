package iteradores;

import java.util.ArrayList;
import java.util.Iterator;

public class Iterador
{

    private static ArrayList<Integer> numeros;

    public static void main(String[] args)
    {
        numeros = new ArrayList<>();

        numeros.add(2);

        for (Iterator<Integer> next = numeros.iterator(); next.hasNext();)
        {
            System.out.println(next.next());
        }

    }

}
