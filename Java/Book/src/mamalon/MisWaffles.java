package mamalon;

public class MisWaffles {

	public static void main(String[] args) {
		
		int[][] matriz = {{1,2,3}, {1,2}, {5,6,7,9}, {1}};
		
		Ivan miIvan = new Ivan("mamado", "45 CM", "azules", "rubio");
		
		Fernando miFernando = new Fernando("mamado", "23 CM", "verdes", "pelirojo");
		
		
		System.out.println(miIvan);
		
		System.out.println(miFernando);
		
		System.out.println("  ");
		
		for(int[] m: matriz) 
		{
			for(int x: m) 
			{
				System.out.print(x);
			}
			
			System.out.println("");
			
		}


	}

}
