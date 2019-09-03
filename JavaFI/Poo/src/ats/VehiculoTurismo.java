package ats;

public class VehiculoTurismo extends Vehiculo
{

    private int nPuertas;

    public VehiculoTurismo(int nPuertas, String matricula, String marca, String modelo)
    {
        super(matricula, marca, modelo);
        this.nPuertas = nPuertas;
    }

    public int getnPuertas()
    {
        return nPuertas;
    }

    @Override
    public String mostrarDatos()
    {
        return String.format("Matrícula: %s\nMarca: %s\nModelo: %s\nNúmero de puertas: %s\n", matricula, marca, modelo, nPuertas);
    }

}
