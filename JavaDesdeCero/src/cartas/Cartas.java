package cartas;

public class Cartas
{

    private final String cara;
    private final String palo;

    public Cartas(String cara, String palo)
    {

        this.cara = cara;
        this.palo = palo;

    }

    public String toString()
    {

        return cara + " de " + palo;

    }
}
