package youtube;

public class Poo {

	public static void main(String[] args) {
		
		Persona objPersona = new Persona();
		
		objPersona.creaPersona("Fernando", "6 de enero de 2000", 18);
		
		System.out.println("Persona creada: ");
		System.out.println("Nombre: " + objPersona.getNombre());
		System.out.println("Fecha de nacimiento: " + objPersona.getFechaDeNacimiento());
		System.out.println("Edad: " + objPersona.getEdad());

	}

}
