package empleado;

import java.util.Arrays;
import java.util.Comparator;

public class UsoEmpleado
{

    public static void main(String[] args)
    {

        /*Programadores[] programadores = new Programadores[3];
         Administrador[] administradores = new Administrador[3];*/
        Empleado[] misEmpleados = new Empleado[5];

        //Poliformismo en acci�n. Principio de sustituci�n
        misEmpleados[0] = new Programadores("Carlos Monroy", "Programador", 2450, 23, 2019, 05, 24);
        misEmpleados[1] = new Programadores("Nicol�s Canul", "Programador");
        misEmpleados[2] = new Administrador("Angel Hern�ndez", "Administrador", 5000, 23, 2019, 05, 25);
        misEmpleados[3] = new Administrador("Iv�n Ojeda", "Administrador");
        misEmpleados[4] = new Empleado("Jorge Puerto", "Empleado", 1200, 19, 2019, 05, 27);

        Administrador tomoDecision = (Administrador) misEmpleados[2];

        System.out.println(tomoDecision.tomarDecisiones("bajar el sueldo un 5%\n\n"));

        /*programadores[0] = new Programadores("Carlos Monroy", 2450, 23, 2019, 05, 24, 600);
         programadores[1] = new Programadores("Fernando Uicad", 3200, 45, 2019, 05, 24, 600);
         programadores[2] = new Programadores("Luis Mena", 600);
		
         administradores[0] = new Administrador("�ngel Hern�ndez", 5000, 23, 2019, 05, 25, 500);
         administradores[1] = new Administrador("David Sansones", 6000, 28, 2019, 05, 25, 500);
         administradores[2] = new Administrador("Nicol�s Canul", 2000, 56, 2019, 05, 25, 500);*/
        Arrays.sort(misEmpleados, new Comparator<Empleado>()
        {
            @Override
            public int compare(Empleado p1, Empleado p2)
            {
                return p1.getNombre().compareTo(p2.getNombre());
            }

        });

        System.out.println("-->Empleados<--");
        for (Empleado i : misEmpleados)
        {
            System.out.println(i); //<-- El enlazado din�mico se da porque la maquina virtual de java JVM
        }									//Sabe a cu�l de estos m�todos llamar(sabe cuando llamar al de programadores 
        //y cuando al de administradores)

        /*System.out.println("\n-->Administradores<--");
         for(Administrador i: administradores) 
         {
			
         System.out.printf(i + i.getBono() + "\nSueldo: " + i.getSueldo() + "\n");
			
         }*/
    }

}
