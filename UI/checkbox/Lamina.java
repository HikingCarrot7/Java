package checkbox;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Lamina extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private JLabel texto;
	
	private JCheckBox[] checkboxs = {new JCheckBox(new AccionBotones("Negritas")), new JCheckBox(new AccionBotones("Cursiva"))};
	
	private JPanel checks;
	
	public Lamina() 
	{
		setLayout(new BorderLayout());
		
		texto = new JLabel("Terraria es lo máximo!", JLabel.CENTER);
		texto.setFont(new Font("serif", Font.PLAIN, 25));
		
		checks = new JPanel();
		
		for(int i = 0; i < checkboxs.length; i++) 
		{
			checks.add(checkboxs[i]);
		}
		
		add(texto, BorderLayout.CENTER);
		add(checks, BorderLayout.SOUTH);
		
	}
	
	private class AccionBotones extends AbstractAction
	{

		private static final long serialVersionUID = 1L;


		public AccionBotones(String titulo) 
		{
			putValue(Action.NAME, titulo);
			putValue(Action.SHORT_DESCRIPTION, "Pone el texto en " + titulo);
		}
		

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			int tipo = 0;
			
			if(checkboxs[0].isSelected()) tipo += Font.BOLD;
			
			if(checkboxs[1].isSelected()) tipo += Font.ITALIC;
			
			texto.setFont(new Font("serif", tipo, 25));
			
			
		}
		
	}
	
}