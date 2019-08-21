package constantes;

import java.util.Scanner;

public class Constantes
{

    public static final int EMPLEADOS = 3;

    public static void main(String[] args)
    {

        Scanner in = new Scanner(System.in);

        double anadirSaldo;

        Empleado[] misEmpleados = new Empleado[EMPLEADOS];

        misEmpleados[0] = new Empleado("Juan Carlos");

        misEmpleados[1] = new Empleado("Fernando Uicab");

        misEmpleados[2] = new Empleado("Francisco Herrera");

        do
        {
            for (Empleado i : misEmpleados)
            {
                System.out.println("Empleados:\n" + i.getDatos());

            }

            System.out.println(Empleado.getIdSiguiente());

            System.out.println("\nA�adir saldo:\n");
            anadirSaldo = in.nextDouble();

            misEmpleados[0].setSaldo(anadirSaldo);

        } while (true);

    }

}

class Empleado
{

    /*Estas variables son llamadas campos de clase*/
    //Cada objeto tiene su propia id
    private int Id;

    //IdSiguiente �nicamente lo tiene la clase (se comparte entre todos los objetos instanciados de esta clase)
    private static int IdSiguiente = 1;

    //No puede ser modificada el objeto nombre (solo el constructor puede)
    private final String nombre;
    private double saldo;

    public Empleado(String nombre)
    {
        this.nombre = nombre;
        saldo = 0;
        Id = IdSiguiente++;

    }

    //Getter
    public String getDatos()
    {

        return "\nTu nombre es: " + this.nombre + "\nSaldo: $" + this.saldo + "\nID: " + this.Id;

    }

    //Setter
    public void setSaldo(double saldo)
    {
        if (saldo > 0)
        {
            this.saldo += saldo;

        }
    }

    //Los m�todos est�ticos no actuan sobre un objeto
    public static String getIdSiguiente()
    {
        //Este m�todo s�lo puede accerder a campos de clase est�ticos
        return "\nEl ID siguiente es: " + IdSiguiente;

    }

    //Getter nombre
	/*public String getNombre() 
     {
		
     return "Tu nombre es: " + this.nombre;
		
     }*/
}
