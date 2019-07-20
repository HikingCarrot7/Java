package swingworker;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.InputMismatchException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class NumerosFibonacci extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	//Calculadora Fibonacci
	private JPanel calculoFibonacci;
	private JTextField capturarNumero;
	private JLabel resultado;
	private JButton empezar;
	
	public NumerosFibonacci() 
	{
		iniciarCalculadora();
		
		anadirElementos();
		
		add(calculoFibonacci);
	}
	
	private void iniciarCalculadora()
	{
		calculoFibonacci = new JPanel(new GridLayout(2, 2, 5, 5));
		calculoFibonacci.setBorder(new TitledBorder(new LineBorder(Color.black), "Con SwingWorker"));
		capturarNumero = new JTextField();
		resultado = new JLabel();
		empezar = new JButton("Iniciar");
	}
	
	private void anadirElementos() 
	{
		accionBoton();
		
		calculoFibonacci.add(new JLabel("Obtener fibonacci de: "));
		calculoFibonacci.add(capturarNumero);
		calculoFibonacci.add(empezar);
		calculoFibonacci.add(resultado);
	}
	
	private void accionBoton() 
	{
		empezar.addActionListener((ActionEvent e) ->
		{
			int n;
			
			try 
			{
				n = Integer.parseInt(capturarNumero.getText());
				
			}catch(NumberFormatException | InputMismatchException ex) 
			{
				resultado.setText("Inserte un número válido");
				
				return;
			}
			
			resultado.setText("Calculando...");
			
			new CalculadoraSegundoPlano(n, resultado).execute();
			
		});
	}

}
