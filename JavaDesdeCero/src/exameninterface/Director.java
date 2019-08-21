package exameninterface;

public class Director extends Persona implements Reporte, ExpulsarYBonos
{

    private double sueldo;

    public Director(String nombre, int edad, double sueldo)
    {

        super(nombre, edad);

        this.sueldo = sueldo;

    }

    @Override
    public String expulsar(Alumno aux)
    {

        return "\nEl director ha expulsado al alumno: " + aux.getNombre() + "\n";

    }

    @Override
    public String expulsar(Maestros aux)
    {

        return "\nEl director ha expulsado al maestro: " + aux.getNombre() + "\n";

    }

    @Override
    public String expulsar(Tutor aux)
    {

        return "\nEl director ha expulsado al tutor: " + aux.getNombre() + "\n";

    }

    @Override
    public String bono(Maestros aux)
    {

        return "\nEl director le ha dado un bono del 10% al maestro: " + aux.getNombre() + "\nSueldo actual: " + (aux.getSueldo() * 1.1);

    }

    @Override
    public String bono(Tutor aux)
    {

        return "\nEl director le ha dado un bono del 10% al tutor: " + aux.getNombre() + "\nSueldo actual: " + (aux.getSueldo() * 1.1);

    }

    @Override
    public String levantarReporte(Alumno aux)
    {

        return "\nEl alumno: " + aux.getNombre() + " tiene un reporte del director\n";
    }

    @Override
    public String toString()
    {

        return super.toString() + "\nSueldo: " + sueldo + "\n";

    }

}
