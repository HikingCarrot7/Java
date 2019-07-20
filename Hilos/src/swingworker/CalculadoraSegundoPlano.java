package swingworker;

import java.util.concurrent.ExecutionException;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class CalculadoraSegundoPlano extends SwingWorker<String, Object>
{

	private final int numero;
	private final JLabel resultado;
	
	public CalculadoraSegundoPlano(int numero, JLabel resultado) 
	{
		this.numero = numero;
		this.resultado = resultado;
	}
	
	@Override
	protected String doInBackground() throws Exception
	{
		long nesimoFib = fibonacci(numero);
		
		return String.valueOf(nesimoFib);
	}
	
	@Override
	protected void done() 
	{
		try 
		{
			resultado.setText(get());
			
		} catch (InterruptedException | ExecutionException e) 
		{
			resultado.setText("Hubo un error al procesar los datos");
		}
	}
	
	private long fibonacci(int numero) 
	{
		if(numero == 0 || numero == 1)
			return numero;
		else
			return fibonacci(numero - 1) + fibonacci(numero - 2);
	}

}
