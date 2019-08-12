package alumnos;

import java.util.ArrayList;

public class Alumnos
{

    private String nombre;
    private double calif;
    private static ArrayList<Double> calificaciones = new ArrayList<>();

    public Alumnos(String nombre, double calif)
    {
        this.nombre = nombre;
        this.calif = calif;
        calificaciones.add(calif);

    }

    public void mostrarDatos()
    {
        System.out.printf("%-18s%.2f%n", nombre, calif);
    }

    public static void mostrarGrafico()
    {
        System.out.println("Relaci�n de calificaciones: ");

        //Frecuencias
        int[] frecuencias = new int[11];

        for (int i = 0; i < calificaciones.size(); i++)
        {
            ++frecuencias[(int) Math.floor(calificaciones.get(i) / 10)];
        }

        for (int i = 0; i < frecuencias.length; i++)
        {

            System.out.printf(i == 10 ? "  100: " : "%02d-%02d: ", i * 10, i * 10 + 9);

            for (int j = 0; j < frecuencias[i]; j++)
            {
                System.out.print("*");
            }

            System.out.println("");

        }

    }

}
