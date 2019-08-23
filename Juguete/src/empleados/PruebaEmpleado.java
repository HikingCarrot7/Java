package empleados;

public class PruebaEmpleado
{

    private static int i = 50;

    public static void main(String[] args)
    {
        Empleados[] misEmpleados = new Empleados[2];

        System.out.println(cambiarI(200));

        System.out.println(i);

        misEmpleados[0] = new Empleados("Nicolás", 45634);
        misEmpleados[1] = new Empleados("Alexis", 54323);

        for (Empleados E : misEmpleados)
        {
            System.out.println(E);
        }

    }

    public static int cambiarI(int nuevoValor)
    {
        i = nuevoValor;

        return i;
    }

}
