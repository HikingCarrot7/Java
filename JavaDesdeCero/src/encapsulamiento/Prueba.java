package encapsulamiento;

public class Prueba
{

    private String nombre;

    private static int[][] numeros =
    {
        {
            1, 2, 3
        },
        {
            4, 5
        },
        {
            6, 7, 8, 9, 10
        }
    };

    private static Prueba[] pruebas =
    {
        new Prueba("Nicol�s")
    };

    private interface Saludar
    {

        public void saludar();

        public void despedida();
    }

    private static abstract class Abstracta
    {

        public abstract void sigueme();
    }

    public Prueba(String nombre)
    {
        this.nombre = nombre;
    }

    public static void main(String[] args)
    {

        Prueba[] misPruebas = new Prueba[2];

        misPruebas[0] = new Prueba("Prueba");
        misPruebas[1] = new PruebaEncapsulamiento("Prueba polimorfismo", "Esto es una prueba\n");

        for (Prueba P : misPruebas)
        {
            System.out.println(P);
        }

        for (int[] I : numeros)
        {
            for (int J : I)
            {
                System.out.print(J + " ");
            }

            System.out.println("");
        }

        System.out.println("\n");

        for (Prueba P : pruebas)
        {
            System.out.println(P);
        }

        saludar(new Saludar()
        {

            @Override
            public void saludar()
            {
                System.out.println("\nHola!");

            }

            @Override
            public void despedida()
            {
                System.out.println("\nAdi�s!");

            }

        });

        seguir(new Abstracta()
        {

            @Override
            public void sigueme()
            {
                System.out.println("S�gueme en facebook");

            }

        });

        PruebaEncapsulamiento.Hola h = new PruebaEncapsulamiento("", "").new Hola();

        h.ahorasi();

        PruebaEncapsulamiento.Hola.Lol xd = new PruebaEncapsulamiento("", "").new Hola().new Lol();

        xd.xd();

    }

    public static void saludar(Saludar S)
    {
        S.saludar();
        S.despedida();
    }

    public static void seguir(Abstracta A)
    {
        A.sigueme();
    }

    public String toString()
    {
        return nombre;
    }

}

class PruebaEncapsulamiento extends Prueba
{

    private String descripcion;

    public PruebaEncapsulamiento(String nombre, String descripcion)
    {
        super(nombre);

        this.descripcion = descripcion;

    }

    @Override
    public String toString()
    {

        return super.toString() + " - " + descripcion;

    }

    public class Hola
    {

        public class Lol
        {

            public void xd()
            {
                System.out.println("esto me est� confundiendo");
            }
        }

        public void ahorasi()
        {
            class xd
            {

                public void ahora()
                {
                    System.out.println("Ahora");
                }
            }

            xd h = new xd();

            h.ahora();
        }
    }

}
