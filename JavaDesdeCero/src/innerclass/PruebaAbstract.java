package innerclass;

public class PruebaAbstract
{

    private static abstract class Saludo
    {

        public abstract String saludar();

    }

    public void Saludar(Saludo S)
    {
        System.out.println(S.saludar());
    }

    public static void main(String[] args)
    {

        new PruebaAbstract().Saludar(new Saludo()
        {
            @Override
            public String saludar()
            {
                return "Esto est� muy kreizy xdxd";
            }

        });

    }

}

class Prueba
{

    public static class MiPrueba
    {

        public void hola()
        {
            System.out.println("Hola");
        }

    }

}
