package swingworkerprimos;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class CalculadoraPrimos extends SwingWorker<Integer, Integer>
{
	
	private final Random rand;
	private final JTextArea mostrarNumeros;
	private final JButton obtenerPrimos, cancelar;
	private final JLabel estado;
	private final boolean[] primos;
	private boolean detenido;
	
	public CalculadoraPrimos(int max, JTextArea mostrarNumeros, JButton obtenerPrimos, JButton cancelar, JLabel estado) 
	{
		rand = new Random();
		
		primos = new boolean[max];
		
		this.mostrarNumeros = mostrarNumeros;
		this.obtenerPrimos = obtenerPrimos;
		this.cancelar = cancelar;
		this.estado = estado;
		
		for (int i = 0; i < primos.length; i++) 
			primos[i] = true;
	}
	
	@Override
	protected Integer doInBackground()
	{
		int cuenta = 0;
		
		for (int i = 2; i < primos.length; i++) 
		{
			if(detenido)
				return cuenta;
			else
				setProgress(100 * (i+1)/primos.length);
			
			try 
			{
				Thread.sleep(rand.nextInt(4));
				
			} catch (InterruptedException e) {}
			
			if(primos[i]) 
			{
				publish(i);
				++cuenta;
				
				for (int j = i + i; j < primos.length; j += i)
					primos[j] = false;
			}
			
		}
		
		return cuenta;
	}
	
	@Override
	protected void process(List<Integer> primos) 
	{
		for (int i = 0; i < primos.size(); i++) 
			mostrarNumeros.append(primos.get(i) + "\n");
		
	}
	
	@Override
	protected void done() 
	{
		obtenerPrimos.setEnabled(true);
		cancelar.setEnabled(false);
		
		int numPrimos = 0;
		
		try 
		{
			numPrimos = get();
			
		} catch (InterruptedException | ExecutionException e) {}
		
		estado.setText("Primos encontrados: " + numPrimos);
	}
	
	public void detenerCalculo() 
	{
		detenido = true;
	}
}
