package exameninterface;

public interface ExpulsarYBonos
{

    public abstract String expulsar(Alumno aux);

    public abstract String expulsar(Maestros aux);

    public abstract String expulsar(Tutor aux);

    public abstract String bono(Maestros aux);

    public abstract String bono(Tutor aux);

}
