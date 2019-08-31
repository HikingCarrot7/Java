package examen_setterngetters;

public class Datos
{

    private String nombre;
    private float saldo;

    public Datos(String nombre, float saldo)
    {
        this.nombre = nombre;
        this.saldo = saldo;
    }

    //M�todo setter para el nombre
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    //M�todo getter para el nombre
    public String getNombre()
    {
        return nombre;
    }

    //M�todo setter para el saldo
    public void setSaldo(float saldo)
    {
        if ((saldo < 0 && this.saldo + saldo >= 0) || saldo > 0)
            this.saldo += saldo;
    }

    //M�todo getter para el saldo
    public float getSaldo()
    {
        return saldo;
    }

}
