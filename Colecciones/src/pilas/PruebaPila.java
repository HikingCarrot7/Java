package pilas;

public class PruebaPila 
{
	private static PilaDinamica<Integer> pila1;
	
	public static void main(String[] args) 
	{
		pila1 = new PilaDinamica<>();
		
		pila1.push(5);
		pila1.push(15);
		pila1.push(25);
		pila1.push(35);
		pila1.push(45);
		
		System.out.println(pila1);

	}

}
