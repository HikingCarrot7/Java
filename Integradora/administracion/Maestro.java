package administracion;

public class Maestro extends Persona implements InicioSesion
{
	
	private String asignatura;
	private double sueldo;
	private String usuario;
	private String contrasena;
	
	
	public Maestro(String nombre, int edad, String asignatura, double sueldo, String usuario , String contrasena) 
	{
		
		super(nombre, edad);
		
		this.asignatura = asignatura;
		this.sueldo = sueldo;
		this.usuario = usuario;
		this.contrasena = contrasena;
		
	}
	
	public Maestro(String nombre) 
	{
		super(nombre);
	}
	
	public void cambiarSueldo(double sueldo) 
	{
		this.sueldo = sueldo;
	}
	
	@Override
	public String getUsuario() 
	{
		return usuario;
	}
	
	@Override
	public String getContrasena() 
	{
		return  contrasena;
		
	}
	
	public String infoPrivada() 
	{
		return "Usuario: " + usuario + ". Contraseña: " + contrasena;
	}
	
	public String datosImportantes()
	{
		return "Has Iniciado sesión como: " + getNombre() + ".     Asignatura: " + asignatura + ".";
	}
	
	@Override
	public String toString() 
	{
		return getNombre();
	}
	
	public String mostrarDatos() 
	{
		return "--> Maestro " + super.toString() + "\nAsignatura: " + asignatura + "\nSueldo: $" + sueldo + "\n\n"; 
	}

}
