package administracion;

public class Administradores implements InicioSesion
{

    private final String usuario;
    private final String contrasena;

    public Administradores(String usuario, String contrasena)
    {
        this.usuario = usuario;
        this.contrasena = contrasena;

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

}
