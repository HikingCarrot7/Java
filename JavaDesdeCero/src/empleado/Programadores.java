package empleado;

public class Programadores extends Empleado implements Bonus
{

    public Programadores(String nombre, String roll, float sueldo, int edad, int anio, int mes, int dia)
    {
        super(nombre, roll, sueldo, edad, anio, mes, dia);
    }

    //Sobrecarga de constructores
    public Programadores(String nombre, String roll)
    {
        super(nombre, roll);
    }

    @Override
    public float getSueldo()
    {
        return (float) (bonus + super.getSueldo() * 1.15);
    }

    @Override
    public String toString()
    {
        return super.toString() + "\nTu bono por ser programador es: $" + bonus;
    }

}
