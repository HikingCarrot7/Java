package exameninterface;

public class Maestros extends Persona implements Reporte
{

    private final String asignatura;
    private final double sueldo;
    private final String nombreReporte;

    public Maestros(String nombre, int edad, String asignatura, double sueldo)
    {
        super(nombre, edad);

        this.asignatura = asignatura;
        this.sueldo = sueldo;
        nombreReporte = nombre;

    }

    public String setCalificaciones(Alumno aux, double calificacion)
    {

        return "\nLa calificaci�n de " + aux.getNombre() + " en " + asignatura + " es " + calificacion + "\n";

    }

    @Override
    public String levantarReporte(Alumno aux)
    {

        return "\nEl alumno " + aux.getNombre() + " tiene un reporte del maestro " + nombreReporte + "\n";

    }

    @Override
    public String toString()
    {

        return super.toString() + "\nAsignatura: " + asignatura + "\nSueldo: " + sueldo + "\n";

    }

    public double getSueldo()
    {
        return sueldo;
    }

}
