package administracion;

public abstract class Persona
{

    private String nombre;
    private int edad;

    public Persona(String nombre, int edad)
    {
        this.nombre = nombre;
        this.edad = edad;

    }

    public Persona(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombre()
    {
        return nombre;
    }

    public int getEdad()
    {
        return edad;
    }

    @Override
    public String toString()
    {
        return "\nNombre: " + nombre + "\nEdad: " + edad + " a�os";
    }

    public abstract String mostrarDatos();

}
