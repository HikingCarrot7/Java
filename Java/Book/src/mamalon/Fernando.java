package mamalon;

public class Fernando {
	
	private String musculos;
	private String mide;
	private String ojos;
	private String cabello;
	
	public Fernando(String musculos, String mide,String ojos, String cabello) 
	{
		
		this.musculos = musculos;
		this.mide = mide;
		this.ojos = ojos;
		this.cabello = cabello;
		
	}
	
	public String toString() 
	{
		return "\n\nAhora tu Fernando esta bien " + musculos + "\nNo es homo\nLe mide " + mide + " el nepe\nTiene los ojos " + ojos + "\nEs " + cabello + " y se ve bien mamalón";
		
	}
	

}
