package grafos;

public class DistanciasPuntos 
{
	public static int mouseTocandoVert(int mouseX, int mouseY)
	{
	  int posicion = -1;
	   
	  for(int i = 0; i < Lamina.xPos.size(); i++)
	  {
	      double dist = distanciaPuntos(mouseX, mouseY,Lamina.xPos.get(i), Lamina.yPos.get(i));
	    
	      if(dist <= 25)
	      {
	    	  posicion = i;

	    	  break;
	      }
	  }
	  
	  return posicion;
	}
	
	public static double distanciaPuntos(int x1, int y1, int x2, int y2)
	{	
		double distancia = (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
		
		return distancia;
	}
}
