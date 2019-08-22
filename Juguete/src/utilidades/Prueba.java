package utilidades;

import java.util.LinkedList;

public class Prueba
{

    private String nombre;
    private int edad;

    public static void main(String[] args)
    {
        nombre("Estrella");
    }

    private static void nombre(String estrella)
    {
        System.out.println(Math.E);

        Prueba obj = new Prueba("Nicol�s");

        LinkedList<Integer> lista = new LinkedList<>();

        lista.add(6);

        System.out.println(obj);

    }

    public Prueba(String nombre, int edad)
    {
        this.nombre = nombre;
        this.edad = edad;
    }

    public Prueba(String nombre)
    {
        this.nombre = nombre;

    }

    public String getnombre()
    {
        return nombre;
    }

    public void setnombre(String nombre)
    {
        this.nombre = nombre;
    }

    public int getEdad()
    {

        return edad;
    }

    public void setEdad(int edad)
    {
        this.edad = edad;
    }

    @Override
    public String toString()
    {
        return "nombre " + nombre + ", edad=" + edad;
    }

}
