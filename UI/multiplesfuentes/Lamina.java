package multiplesfuentes;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Lamina extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	JButton yellow;
	JButton blue;
	JButton red;
	
	public Lamina()
	{
		yellow = new JButton("Yellow");
		blue = new JButton("Blue");
		red = new JButton("Red");
		
		add(yellow);
		add(blue);
		add(red);
		
		red.addActionListener(new AccionColor());
		
	}

}

class AccionColor extends AbstractAction
{

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("Soy rojo xD");
		
	}
}