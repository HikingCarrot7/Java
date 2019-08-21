package empleado;

import java.util.Date;
import java.util.GregorianCalendar;

public class Empleado implements Comparable<Empleado>
{

    private int ticket;
    private int edad;
    private String nombre;
    private String roll;
    private float sueldo;
    private Date fechaAlta;

    public Empleado(String nombre, String roll, float sueldo, int edad, int anio, int mes, int dia)
    {
        this.nombre = nombre;
        this.roll = roll;
        this.sueldo = sueldo;
        this.edad = edad;
        this.ticket = (int) Math.round(Math.random() * 100);

        GregorianCalendar fecha = new GregorianCalendar(anio, mes - 1, dia);

        fechaAlta = fecha.getTime();

    }

    //Sobre carga de constructores
    public Empleado(String nombre, String roll)
    {
        //Este this(); est� llamando al otro constructor y le est� mandando los siguientes par�metros
        this(nombre, roll, 0, 0, 2000, 1, 1);

    }

    public String getNombre()
    {
        return nombre;
    }

    @Override
    public String toString()
    {
        return "\nRoll: " + roll + "\nNombre: " + nombre + "\nFecha de alta: " + fechaAlta + "\nTicket: EA" + ticket + "\nEdad: " + edad + "\nSueldo: $" + getSueldo();
    }

    //Getter sueldo
    public float getSueldo()
    {
        return sueldo;
    }

    @Override
    public int compareTo(Empleado miObjeto)
    {
        if (this.sueldo < miObjeto.sueldo)
        {
            return -1;
        } else if (this.sueldo > miObjeto.sueldo)
        {
            return 1;
        } else
        {
            return 0;
        }
    }
}
