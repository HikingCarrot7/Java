package mamalon;

public class Ivan {
	
	private String musculos;
	private String mide;
	private String ojos;
	private String cabello;
	
	public Ivan(String musculos, String mide,String ojos, String cabello) 
	{
		
		this.musculos = musculos;
		this.mide = mide;
		this.ojos = ojos;
		this.cabello = cabello;
		
	}
	
	public String toString() 
	{
		return "Ahora tu Ivan esta bien " + musculos + "\nLe mide " + mide + " el nepe\nTiene los ojos " + ojos + "\nEs " + cabello + " y se ve bien mamal�n";
		
	}
	

}
