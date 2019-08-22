package utilidades;

/**
 *
 * @author HikingCarrot7
 */
public class Clasess
{

    private interface Escribir
    {

        public void escribir();
    }

    private abstract class Persona
    {

        public abstract String getNombre();
    }

    public static void main(String[] args)
    {
        Clasess c = new Clasess();

        c.persona(c.new Persona()
        {

            @Override
            public String getNombre()
            {
                return "Nicolás";
            }

        });

        Escribir e = c::persona;

        e.escribir();

    }

    public void persona(Persona p)
    {
        System.out.println(p.getNombre());
    }

    public void persona()
    {
        System.out.println("Hola!");
    }
}
