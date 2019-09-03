package alumnos;

public class PruebaAlumnos
{

    public static void main(String[] args)
    {
        Alumnos[] misAlumnos = new Alumnos[6];

        misAlumnos[0] = new Alumnos("Nicolás Canul", 56.67);
        misAlumnos[1] = new Alumnos("Carlos Monroy", 67.54);
        misAlumnos[2] = new Alumnos("Iván Ojeda", 85.90);
        misAlumnos[3] = new Alumnos("Alexis Ek", 100);
        misAlumnos[4] = new Alumnos("Fernando Euan", 34.94);
        misAlumnos[5] = new Alumnos("Luis Mena", 38.45);

        System.out.printf("%-18s%s%n%n", "Nombre: ", "Calificación: ");

        for (Alumnos A : misAlumnos)
            A.mostrarDatos();

        Alumnos.mostrarGrafico();
    }

}
