package clasesabstractas;

public class Alumno extends Persona
{

    private String escuela;
    private String genero;
    private double promedioNotas;

    public Alumno(String nombre, String edad, String estadoCivil, boolean discapacidad, boolean usaLentes, String estadoAnimo, String genero, String escuela, double promedioNotas)
    {
        super(nombre, edad, estadoCivil, discapacidad, usaLentes, estadoAnimo);

        this.escuela = escuela;
        this.promedioNotas = promedioNotas;
        this.genero = genero;

    }

    @Override
    public String genero()
    {
        return "\nMi g�nero es: " + genero;

    }

    @Override
    public String toString()
    {

        return super.toString() + "\nSoy un alumno. \nEstudio en: " + escuela + "\nMi promedio de notas es: " + promedioNotas + genero();

    }

}
