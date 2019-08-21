package tiposenumerados;

public class PruebaCompaneros
{

    public static void main(String[] args)
    {
        System.out.printf("%-15s%-28s%s\n\n", "Compa�ero:", "Descripci�n:", "Edad:");

        for (Companeros compas : Companeros.values())
        {
            System.out.printf("%-15s%-28s%s%n", compas, compas.getDescripcion(), compas.getEdad());
        }

    }

}
