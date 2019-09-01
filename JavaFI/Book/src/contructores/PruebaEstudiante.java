package contructores;

public class PruebaEstudiante
{

    public static void main(String[] args)
    {
        Promedio[] estudiantes = new Promedio[4];

        estudiantes[0] = new Promedio("Carlos Monroy", 76.25);
        estudiantes[1] = new Promedio("Luis Mena", 84.87);
        estudiantes[2] = new Promedio("Nicol�s Canul", 34.89);
        estudiantes[3] = new Promedio("Fernando Euan", 99.67);

        for (Promedio P : estudiantes)
            System.out.println(P);

    }

}
