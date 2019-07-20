package innerclass;

public class Outer 
{
	
	private interface Saludar
	{
		public void saludar();
	}

	public static void main(String[] args) 
	{
		Saludar saludo = new Saludar() 
		{
			@Override
			public void saludar() 
			{
				System.out.println(new My_class().new Prueba().getSaludo() + My_class.getR());
			}

		};
		
		saludo.saludar();
		
	}

}

class My_class 
{
	private String saludo = "¿Por qué el método main es static?";
	
	public class Prueba 
	{
		public String getSaludo() 
		{	
			return saludo;
		}
		
	}
	
	public static String getR() 
	{
		return " \nR = Porque Java está demente.";
	}
	
}
