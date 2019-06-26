package clasesinternas;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Timer;

public class Reloj 
{
	
	public void enMarcha(int intervalo, final boolean sonido) 
	{
		
		Timer miTemporizador = new Timer(intervalo, new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				Date ahora = new Date();
				
				System.out.println("La hora es: " + ahora);
				
				if(sonido) 
				{
					Toolkit.getDefaultToolkit().beep();
				}
			}
			
		});
		
		miTemporizador.start();
		
	}
}
