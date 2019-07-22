package pruebas;

public class Pruebas
{

    private interface Saludador
    {

        public void hola();

        public void despedida();

    }

    private static abstract class Sigueme
    {

        public abstract void facebook();

    }

    public static void main(String[] args)
    {

        Saludador saludar = new Saludador()
        {

            @Override
            public void hola()
            {

                System.out.println("Hola, esta es una prueba de las malas xdxdxd");

            }

            @Override
            public void despedida()
            {
                System.out.println("Adi�s es la palabra de las malas lenguas xd");

            }

        };

        Sigueme xd = new Sigueme()
        {

            @Override
            public void facebook()
            {
                System.out.println("S�gueme en facebook");

            }

        };

        saludar.hola();

        saludar.despedida();

        xd.facebook();
    }

}
