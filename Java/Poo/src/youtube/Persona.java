package youtube;

public class Persona {
	
	private String nombre;
	private String fechaDeNacimiento;
	private int edad;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public String getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	public void setFechaDeNacimiento(String fechaDenacimiento) {
		this.fechaDeNacimiento = fechaDenacimiento;
	}
	
	
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public void creaPersona(String _nombre, String _fechadenacimiento, int _edad) {
		
		nombre = _nombre;
		fechaDeNacimiento = _fechadenacimiento;
		edad = _edad;
		
	}

}
