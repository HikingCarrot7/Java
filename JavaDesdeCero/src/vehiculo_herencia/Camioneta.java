package vehiculo_herencia;

public class Camioneta extends Vehiculo
{

    private int carga;

    public Camioneta(String dueno, String nombreVehiculo, String placa, double largo, double ancho, int carga)
    {
        super(dueno, nombreVehiculo, placa, largo, ancho);

        this.carga = carga;

    }

    public int getLlantas()
    {

        return super.getLlantas();

    }

    public int getPesoPlataforma()
    {

        return super.getPesoPlataforma() + 100;

    }

    public int getPesoMotor()
    {

        return super.getPesoMotor() + 30;

    }

    //Getter obtener datos
    public String toString()
    {

        return super.toString() + "\nLlantas: " + getLlantas() + "\nPeso motor: " + getPesoMotor() + " Kg\nPeso plataforma: " + getPesoPlataforma() + " Kg\nCarga: " + carga + " Kg";

    }

}
