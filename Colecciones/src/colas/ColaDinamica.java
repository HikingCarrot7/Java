package colas;

public class ColaDinamica<T>
{	
	private Nodo<T> primero;
	private Nodo<T> ultimo;
	private int tamano;
	
	public ColaDinamica() 
	{
		primero = ultimo = null;
		tamano = 0;
	}
	
	public boolean isEmpty() 
	{
		return primero == null;
	}
	
	public int size() 
	{
		return tamano;
	}
	
	public T primero() 
	{
		if(isEmpty())
			return null;
		
		return primero.getElemento();
	}
	
	public T enqueue(T elemento) 
	{
		Nodo<T> aux = new Nodo<>(elemento, null);
		
		if(isEmpty()) 
		{
			primero = aux;
			ultimo = aux;
			
		}else 
		{
			ultimo.setSiguiente(aux);

			ultimo = aux;
		}
		
		tamano++;
		
		return aux.getElemento();
		
	}
	
	public T dequeue() 
	{
		if(isEmpty())
			return null;
		
		T elemento = primero.getElemento();
		
		Nodo<T> aux = primero.getSiguiente();
		
		primero = null;
		
		primero = aux;
		
		if(isEmpty())
			ultimo = null;
		
		return elemento;
	}
	
	@Override
	public String toString() 
	{
		if(isEmpty())
			return "La cola está vacía";
		
		String texto = "";
		
		Nodo<T> aux = primero;
		
		while(aux != null) 
		{
			texto += aux;
			
			aux = aux.getSiguiente();
			
		}
		
		return texto;
	}
}
