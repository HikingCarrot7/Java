package clasesabstractas;

public abstract class Persona
{

    private String nombre;
    private String edad;
    private String estadoCivil;
    private boolean discapacidad;
    private boolean usaLentes;
    private String estadoAnimo;

    public Persona(String nombre, String edad, String estadoCivil, boolean discapacidad, boolean usaLentes, String estadoAnimo)
    {
        this.nombre = nombre;
        this.edad = edad;
        this.estadoCivil = estadoCivil;
        this.discapacidad = discapacidad;
        this.usaLentes = usaLentes;
        this.estadoAnimo = estadoAnimo;

    }

    public abstract String genero();

    @Override
    public String toString()
    {

        return "\nMi nombre es: " + nombre + "\nTengo " + edad + " a�os\nMi estado civil es: " + estadoCivil + "\n�Tengo discapacidad? " + tengoDiscapacidad()
                + "\n�Uso lentes? " + tengoLentes() + "\nMi estado de �nimo es: " + estadoAnimo;
    }

    private String tengoDiscapacidad()
    {

        if (discapacidad)
            return "S�";
        else
            return "No";

    }

    private String tengoLentes()
    {
        if (usaLentes)
            return "S�";
        else
            return "No";
    }

}
