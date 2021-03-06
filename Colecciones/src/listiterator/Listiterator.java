package listiterator;

import java.util.LinkedList;
import java.util.ListIterator;

public class Listiterator
{

    private static LinkedList<String> paises, capitales;

    public static void main(String[] args)
    {
        paises = new LinkedList<>();
        capitales = new LinkedList<>();

        paises.add("M�xico");
        paises.add("Espa�a");
        paises.add("Argentina");
        paises.add("Per�");

        capitales.add("D.F.");
        capitales.add("Madrid");
        capitales.add("Buenos Aires");
        capitales.add("Lima");

        ListIterator<String> pais = paises.listIterator();
        ListIterator<String> capital = capitales.listIterator();

        while (capital.hasNext())
        {
            if (pais.hasNext())
            {
                pais.next();
            }

            pais.add(capital.next());
        }

        System.out.println(paises);

        capital = capitales.listIterator();

        while (capital.hasNext())
        {
            capital.next();

            if (capital.hasNext())
            {
                capital.next();
                capital.remove();
            }

        }

        System.out.println(capitales);

        paises.removeAll(capitales);

        System.out.println(paises);

    }

}
