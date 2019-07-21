package administracion;

public class Alumno extends Persona
{

    private String licenciatura;
    private double calificacion;

    public Alumno(String nombre, int edad, String licenciatura, double calificacion)
    {
        super(nombre, edad);

        this.licenciatura = licenciatura;
        this.calificacion = calificacion;

    }

    public Alumno(String nombre)
    {
        super(nombre);
    }

    public void cambiarCalif(double calificacion)
    {
        this.calificacion = calificacion;

    }

    public String obtenerLic()
    {
        return licenciatura;
    }

    public double obtenerCalif()
    {
        return calificacion;
    }

    @Override
    public String mostrarDatos()
    {
        return "--> Alumno: " + super.toString() + "\nLicenciatura: " + licenciatura + "\nCalificaci�n: " + calificacion + "\n\n";
    }

    @Override
    public String toString()
    {
        return getNombre();
    }

}
