package unpocodetodo;

public class PruebaPersonas implements Enumeraciones
{

    enum Companeros
    {

        Carlos("Es una de las personas m�s extra�as"),
        Jimmy("xd");

        private String descripcion;

        private Companeros(String descripcion)
        {
            this.descripcion = descripcion;
        }

        public String getDesc()
        {
            return descripcion;
        }
    }

    public static void main(String[] args)
    {
        for (Companeros C : Companeros.values())
        {
            System.out.println(C.getDesc());
        }

        System.out.println(Enum.valueOf(Companeros.class, "Carlos").getDesc());

        System.out.println(enumeracion.APROBADO);

        System.out.println(constante);

        Prueba xd = new XD();

    }

}

class XD implements Prueba
{

    @Override
    public void obtener()
    {
		// TODO Auto-generated method stub

    }

    public void testeo()
    {

    }

}
