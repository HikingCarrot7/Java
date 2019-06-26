package dialog;

import javax.swing.JOptionPane;

public class DialogNombre {

	public static void main(String[] args) {
		
		String nombre = JOptionPane.showInputDialog("¿Cuál es su nombre?");
		
		String mensaje = 
				String.format("Bienvenido, %s, a la programación en Java", nombre);
		
		JOptionPane.showMessageDialog(null, mensaje);

	}

}
