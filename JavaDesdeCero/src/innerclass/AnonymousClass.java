package innerclass;

public class AnonymousClass 
{
	
	private interface Saludar
	{
		public String saludo();
		
		public String R();
	}
	
	public void Saludo(Saludar S) 
	{
		System.out.println(S.saludo());
		
		System.out.println(S.R());
	}

	public static void main(String[] args) 
	{
		new AnonymousClass().Saludo(new Saludar() 
		{
			@Override
			public String saludo() 
			{
				return "Hola";
			}

			@Override
			public String R() 
			{

				return "Una clase anónima que implementaba una interface fue pasada como parámetro. \nKe kreizy xdxd";
			}
			
		});

	}
	
}
