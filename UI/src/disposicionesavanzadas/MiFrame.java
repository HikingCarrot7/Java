package disposicionesavanzadas;

import javax.swing.JFrame;

public class MiFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	Lamina lamina;
	
	public MiFrame() 
	{
		lamina = new Lamina();
		
		setBounds(0, 0, 300, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Disposiciones Box");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(lamina);
		setVisible(true);
	}

	public static void main(String[] args) 
	{
		new MiFrame();

	}

}