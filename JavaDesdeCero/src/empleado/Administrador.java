package empleado;

public class Administrador extends Empleado implements Jefes
{

    //Herencia
    public Administrador(String nombre, String roll, float sueldo, int edad, int anio, int mes, int dia)
    {
        super(nombre, roll, sueldo, edad, anio, mes, dia); //Llamar al constructor de la clase padre (Empleado)
    }

    public Administrador(String nombre, String roll)
    {
        super(nombre, roll);

    }

    public float getSueldo()
    {
        return (float) ((bonus + 100) + super.getSueldo() * 1.1);

    }

    public String toString()
    {
        return super.toString() + "\nTu bono por ser administrador es: $" + (bonus + 100);

    }

    public String tomarDecisiones(String decision)
    {
        return "Un administrador tom� la decisi�n de " + decision;
    }

}
