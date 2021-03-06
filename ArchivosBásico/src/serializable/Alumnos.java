package serializable;

import java.io.Serializable;

public class Alumnos implements Serializable
{

    private static final long serialVersionUID = 1L;

    private final String nombre;
    private final int edad;

    public Alumnos(String nombre, int edad)
    {
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public String toString()
    {
        return "Mi nombre es: " + nombre + " y mi edad es: " + edad;
    }

}
