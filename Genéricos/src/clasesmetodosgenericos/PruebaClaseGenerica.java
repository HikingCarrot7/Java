package clasesmetodosgenericos;

import java.util.GregorianCalendar;

public class PruebaClaseGenerica
{
//	private static ClaseGenerica<String, Integer> nombres;
//	private static ClaseGenerica<Integer, String> numeros;

    public static void main(String[] args)
    {
//		nombres = new ClaseGenerica<String, Integer>();
//		numeros = new ClaseGenerica<Integer, String>();

        /*nombres.anadirDatos("Nicol�s", 19);
		
         numeros.anadirDatos(12, "Javier");
		
         System.out.println(nombres.obtenerDato() + " " + nombres.obtenerDato2());
		
         System.out.println(numeros.obtenerDato() + " " + numeros.obtenerDato2());*/
        System.out.println(ClaseGenerica.numElementos(new String[]
        {
            "Nicol�s", "Estrella"
        }));

        System.out.println(ClaseGenerica.numElementos(new Integer[]
        {
            12, 45, 56
        }));

        System.out.println(ClaseGenerica.numElementos(new Personas[]
        {
            new Personas(23), new Personas(78), new Personas(45)
        }));

        System.out.println(ClaseGenerica.numElementos(new GregorianCalendar[]
        {
            new GregorianCalendar(2015, 11, 11), new GregorianCalendar(2014, 9, 10), new GregorianCalendar(2014, 10, 11)
        }).getTime());

    }

}

class Personas implements Comparable<Personas>
{

    private int edad;

    public Personas(int edad)
    {
        this.edad = edad;
    }

    @Override
    public int compareTo(Personas o)
    {
        return edad - o.edad;
    }

    @Override
    public String toString()
    {
        return "" + edad;
    }

}
