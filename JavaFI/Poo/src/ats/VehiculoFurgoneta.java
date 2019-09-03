package ats;

public class VehiculoFurgoneta extends Vehiculo
{

    private final int carga;

    public VehiculoFurgoneta(int carga, String matricula, String marca, String modelo)
    {
        super(matricula, marca, modelo);
        this.carga = carga;
    }

    public int getCarga()
    {
        return carga;
    }

    @Override
    public String mostrarDatos()
    {
        return String.format("Matrícula: %s\nMarca: %s\nModelo: %s\nCarga: %s\n", matricula, marca, modelo, carga);
    }

}
