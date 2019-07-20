package innerclass;

public class Examen 
{
	private interface Despedida
	{
		public void despedirme();
	}

	private interface Saludar
	{
		public String saludar();
		
		public void info();
		
	}
	
	private static abstract class JavaIsCool
	{
		public abstract String Resp();
		
		public abstract int Sueldo();
		
	}
	
	public static void info(Saludar S) 
	{
		System.out.println(S.saludar());
		
		S.info();
		
	}
	
	public void Resp(JavaIsCool J) 
	{
		System.out.println(J.Resp());
		
		System.out.println("Mi sueldo es de: " + J.Sueldo() + " pesos xd");
		
	}
	
	public static void main(String[] args) 
	{
		
		Examen.info(new Saludar() 
		{

			@Override
			public String saludar() 
			{
				
				return "Hola, este es un examen";
			}

			@Override
			public void info() 
			{
				System.out.println("Estoy mandando una clase anónima que implementa una interface como parámetro");
				
			}
			
		});
		
		new Examen().Resp(new JavaIsCool() 
		{

			@Override
			public String Resp() 
			{
				return "Java no es tan difícil, bueno sí, lo es xd";
			}

			@Override
			public int Sueldo() 
			{
				return 45;
			}
			
		});
		
		Despedida desp = new Despedida() 
		{

			@Override
			public void despedirme() 
			{
				System.out.println("Bueno, adiós!");
				
			}
			
		};
		
		desp.despedirme();
		
		new Recomendacion().R();
		
		new Recomendacion().new Recom().R();
		
		new Recomendacion().new Recom().new AhoraSi().Now();
		
	}

}

class Recomendacion
{
	
	public void R() 
	{
		System.out.println("Sígueme en Instagram xd");
	} 
	
	public class Recom extends Recomendacion
	{
		
		public class AhoraSi
		{
			public void Now() 
			{
				System.out.println("Ahora ya me voy de verdad xd");
			}
		}
		
		@Override
		public void R() 
		{
			System.out.println("Sígueme en Facebook también xd");
		} 
	}
}
