package exameninterface;

public class Tutor extends Persona implements Reporte
{

    private String cubiculo;
    private double sueldo;
    private String nombreReporte;

    public Tutor(String nombre, int edad, String cubiculo, double sueldo)
    {
        super(nombre, edad);

        this.cubiculo = cubiculo;
        this.sueldo = sueldo;
        nombreReporte = nombre;

    }

    @Override
    public String levantarReporte(Alumno aux)
    {

        return "\nEl alumno: " + aux.getNombre() + " tiene un reporte del tutor " + nombreReporte + "\n";

    }

    public String toString()
    {

        return super.toString() + "\nCub�culo: " + cubiculo + "\nSueldo: " + sueldo + "\n";

    }

    public double getSueldo()
    {
        return sueldo;
    }

}
