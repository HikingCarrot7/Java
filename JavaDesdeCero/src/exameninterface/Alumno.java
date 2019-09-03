package exameninterface;

public class Alumno extends Persona
{

    private String licenciatura;
    private String grupo;
    private int anio;
    private int mes;
    private int dia;

    private double calificacion;

    public Alumno(String nombre, int edad, String licenciatura, String grupo, int anio, int mes, int dia)
    {

        super(nombre, edad);

        this.licenciatura = licenciatura;
        this.grupo = grupo;
        this.anio = anio;
        this.mes = mes;
        this.dia = dia;

    }

    @Override
    public String toString()
    {

        return super.toString() + "\nLicenciatura: " + licenciatura + "\nGrupo: " + grupo + "\nRegistro: " + anio + "/" + mes + "/" + dia + "\n";

    }

    public void setCalificacion(double calificacion)
    {

        this.calificacion = calificacion;

    }

    public String getCalificacion()
    {

        return "\nLa calificaci�n de este alumno es: " + calificacion;

    }

}
