package examen_setterngetters;

import java.util.Scanner;
import javax.swing.JOptionPane;

;

public class PruebaDatos
{

    public static void main(String[] args)
    {
        @SuppressWarnings("resource")
        Scanner in = new Scanner(System.in);

        int opcion;
        String nombre;
        String indice;
        float nuevoSaldo;

        Datos[] personas = new Datos[3];

        for (int i = 0; i < personas.length; i++)
        {
            nombre = JOptionPane.showInputDialog("Inserte el nombre de la " + (i + 1) + " persona");
            //System.out.printf("Inserte el nombre de la %d persona ", i + 1);
            //nombre = in.nextLine();

            personas[i] = new Datos(nombre, 0);

        }

        System.out.println("\nPersonas registradas:\n");

        do
        {
            int j = 1;

            for (Datos i : personas)
                System.out.println((j++) + ".- " + i.getNombre() + " --> Saldo: " + i.getSaldo());

            /*for(int i = 0; i < personas.length; i++) 
             {
             System.out.println((i+1) + ".- " + personas[i].getNombre() + " --> Saldo: " + personas[i].getSaldo());
             }*/
            System.out.println("\nPresione 1.- Cambiar nombre o 2.- A�adir saldo ");
            opcion = in.nextInt();

            switch (opcion)
            {

                case 1:

                    indice = JOptionPane.showInputDialog("�A qui�n le quieres cambiar el nombre?");
                    opcion = Integer.parseInt(indice);
                    //System.out.println("�A qui�n le quieres cambiar el nombre?");
                    //opcion = in.nextInt();

                    if (--opcion >= 0 && opcion < personas.length)
                    {

                        in.nextLine();
                        System.out.println("Escriba su nuevo nombre ");
                        nombre = in.nextLine();

                        personas[opcion].setNombre(nombre);

                    } else
                        System.out.println("Posici�n NO v�lida");

                    break;

                case 2:

                    System.out.println("�A qui�n le quieres a�adir saldo? ");
                    opcion = in.nextInt();

                    if (--opcion >= 0 && opcion < personas.length)
                    {

                        System.out.println("Agregar saldo: ");
                        nuevoSaldo = in.nextFloat();

                        personas[opcion].setSaldo(nuevoSaldo);
                    }

                    break;

                default:
                    break;
            }

        } while (true);
    }
}
