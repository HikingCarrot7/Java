package grafos;

public class Colores 
{
	protected static int[] coloresVert = new int[50];
	
	private static int[][] adyacencia = new int[50][50];
	
	public static void colores()
	{
	  boolean encontradoRojo = false;
	  boolean encontradoAmarillo = false;
	  boolean encontradoVerde = false;
	  
	  //Inicializamos la matriz
	  for(int i = 0; i < Lamina.xPos.size(); i++)
	  {
	    for(int j = 0; j <  Lamina.xPos.size(); j++)
	    {      
	      adyacencia[i][j] = Lamina.matrizAdyacencia[i][j];
	      
	      if((i == 0 || j == 0) && adyacencia[i][j] == 1)
	    	  adyacencia[i][j] = 2;
	    }
	    
	    coloresVert[i] = 0;
	  }
	  
	  coloresVert[0] = 2;
	  
	  for(int i = 1; i <  Lamina.xPos.size(); i++)
	  {
	    for(int j = 0; j <  Lamina.xPos.size(); j++)
	    {
	      
	      if(adyacencia[i][j] == 2)
	      {     
	        encontradoRojo = true;
	        
	        for(int x = 0; x <  Lamina.xPos.size(); x++)
	        {
	          
	          if(adyacencia[i][x] == 3)
	          {
	            encontradoAmarillo = true;
	            
	            for(x = 0; x <  Lamina.xPos.size(); x++)
	            	if(adyacencia[i][x] == 4)
	            	{
	            		
	            		encontradoVerde = true;
	            		
	            		coloresVert[i] = 5;
	            		
	            		break;
	            		
	            	}
	            
	            if(!encontradoVerde)
	            {
	               coloresVert[i] = 4;
	              
	                for(x = 0; x <  Lamina.xPos.size(); x++)
	                	if(adyacencia[i][x] == 1)
	                	{	
	                		adyacencia[i][x] = 4;
	                		adyacencia[x][i] = 4;
	                	}
	              }
	            
	            encontradoVerde = false;
	            
	            break;
	        }
	      }
	      
	      if(!encontradoAmarillo)
	      {    
	          coloresVert[i] = 3;
	          
	          for(int x = 0; x <  Lamina.xPos.size(); x++)
	        	  if(adyacencia[i][x] == 1)
	        	  {		  
	        		  adyacencia[i][x] = 3;
	        		  adyacencia[x][i] = 3;
	        	  }
	        }
	        
	        encontradoAmarillo = false;
	        
	         break;
	     }
	      
	    }//For j
	    
	    if(!encontradoRojo)
	    {
	      coloresVert[i] = 2;
	        
	        for(int x = 0; x <  Lamina.xPos.size(); x++)
	        	if(adyacencia[i][x] == 1)
	        	{	
	        		adyacencia[i][x] = 2;
	        		adyacencia[x][i] = 2;
	        	}
	      }
	      
	      encontradoRojo = false;
	  }//For i
	}
}
