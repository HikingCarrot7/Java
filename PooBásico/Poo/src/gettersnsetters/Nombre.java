package gettersnsetters;

public class Nombre {
	
	String nombre;
	
	public Nombre(String nombre){
		
		this.nombre = nombre;
		
	}
	
	public void establecerNombre() {
		this.nombre = nombre;
	}
	
	public String obtenerNombre() {
		return nombre;
	}
}
