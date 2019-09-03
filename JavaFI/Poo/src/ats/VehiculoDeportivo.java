package ats;

public class VehiculoDeportivo extends Vehiculo
{

    private final int cilindrada;

    public VehiculoDeportivo(int cilindrada, String matricula, String marca, String modelo)
    {
        super(matricula, marca, modelo);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada()
    {
        return cilindrada;
    }

    @Override
    public String mostrarDatos()
    {
        return String.format("Matrícula: %s\nMarca: %s\nModelo: %s\nCilidrada: %s\n", matricula, marca, modelo, cilindrada);
    }

}
