package contructores;

public class Promedio
{

    private final String nombre;
    private final double promedio;

    public Promedio(String nombre, double promedio)
    {
        this.nombre = nombre;
        this.promedio = promedio > 0 ? promedio : 0;

    }

    public String obtenerNombre()
    {
        return nombre;
    }

    public String obtenerCalificacionEstudiante()
    {

        String calificacionEstudiante;

        if (promedio >= 90)
            calificacionEstudiante = "A";

        else if (promedio >= 80)
            calificacionEstudiante = "B";

        else if (promedio >= 70)
            calificacionEstudiante = "C";

        else if (promedio >= 60)
            calificacionEstudiante = "D";

        else
            calificacionEstudiante = "F";

        return calificacionEstudiante;
    }

    @Override
    public String toString()
    {
        return "La calificaci�n de " + obtenerNombre() + " es: " + obtenerCalificacionEstudiante();
    }

}
