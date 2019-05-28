package tiposenumerados;

public class PruebaCompañeros {

	public static void main(String[] args) 
	{
		
		System.out.printf("%-15s%-28s%s\n\n", "Compañero:", "Descripción:", "Edad:");
		for(Compañeros compas: Compañeros.values()) 
		{
			System.out.printf("%-15s%-28s%s%n", compas, compas.getDescripcion(), compas.getEdad());
		}

	}

}
