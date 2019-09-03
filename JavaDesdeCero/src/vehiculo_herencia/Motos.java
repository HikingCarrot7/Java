package vehiculo_herencia;

public class Motos extends Vehiculo
{

    private int personasSimultaneas;

    Motos(String dueno, String nombreVehiculo, String placa, double largo, double ancho, int personasSimultaneas)
    {

        super(dueno, nombreVehiculo, placa, largo, ancho);

        this.personasSimultaneas = personasSimultaneas;

    }

    @Override
    public int getLlantas()
    {

        return super.getLlantas() - 2;

    }

    @Override
    public int getPesoPlataforma()
    {

        return super.getPesoPlataforma() - 350;

    }

    @Override
    public int getPesoMotor()
    {

        return super.getPesoMotor() - 100;

    }

    //Getter obtener datos
    @Override
    public String toString()
    {

        return super.toString() + "\nLlantas: " + getLlantas() + "\nPeso motor: " + getPesoMotor() + " Kg\nPeso plataforma: " + getPesoPlataforma() + " Kg\nPersonas simult�neas: " + personasSimultaneas + "\n";

    }

}
