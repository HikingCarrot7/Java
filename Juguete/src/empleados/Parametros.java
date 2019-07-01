package empleados;

public class Parametros 
{
	
	private boolean xd;
	
	public Parametros(boolean xd) 
	{
		this.xd = xd;
	}

	public static void main(String[] args) 
	{
		new Parametros(true).prueba();
		
		new Parametros(false).prueba();

	}
	
	public void prueba() 
	{
		System.out.println(xd ? "Java es cool": "Java no es cool");
		
		System.out.println(Math.toRadians(1));
		
	}

}
