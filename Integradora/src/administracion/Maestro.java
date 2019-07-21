package administracion;

public class Maestro extends Persona implements InicioSesion
{

    private String asignatura;
    private double sueldo;
    private String usuario;
    private String contrasena;
    private String fechaAlta;

    public Maestro(String nombre, int edad, String asignatura, double sueldo, String usuario, String contrasena, String fechaAlta)
    {

        super(nombre, edad);

        this.asignatura = asignatura;
        this.sueldo = sueldo;
        this.usuario = usuario;
        this.contrasena = contrasena;

        this.fechaAlta = fechaAlta;

    }

    public Maestro(String nombre)
    {
        super(nombre);
    }

    public void cambiarSueldo(double sueldo)
    {
        this.sueldo = sueldo;
    }

    public double getSueldo()
    {
        return sueldo;
    }

    public String getAsignatura()
    {
        return asignatura;
    }

    public String getFecha()
    {
        return fechaAlta;
    }

    @Override
    public String getUsuario()
    {
        return usuario;
    }

    @Override
    public String getContrasena()
    {
        return contrasena;

    }

    public String infoPrivada()
    {
        return "Usuario: " + usuario + ". Contrase�a: " + contrasena;
    }

    public String datosImportantes()
    {
        return "Has Iniciado sesi�n como: " + getNombre() + ".     Asignatura: " + asignatura + ".";
    }

    @Override
    public String toString()
    {
        return getNombre();
    }

    @Override
    public String mostrarDatos()
    {
        return "--> Maestro " + super.toString() + "\nAsignatura: " + asignatura + "\nSueldo: $" + sueldo + "\nFecha de alta: " + fechaAlta + "\n\n";
    }

}
