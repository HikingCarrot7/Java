package minichat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author HikingCarrot
 */
public final class Chat extends JFrame implements Observer
{
    private JPanel texto, enviar, general; 
    private JTextArea areaTexto;
    private JScrollPane soporte;
    private JTextField mensaje;
    private JButton enviarMensaje;
    private final int persona;
    
    public Chat(Servidor s, int persona)
    {
        s.addObserver(this);
        this.persona = persona;
        
        iniciarElementos();
        anadirElementos();
        setupVentana();
        
        add(general);
    }
    
    public void setupVentana()
    {
        setBounds(0, 0, 500, 400);
        setLocationRelativeTo(null);
        setTitle("Chat!" + " (" + persona + ")");
        setResizable(false);
        setAlwaysOnTop(true);
        add(general);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
    
    public void iniciarElementos()
    {
        general = new JPanel(new BorderLayout());
        texto = new JPanel(new BorderLayout());
        enviar = new JPanel();
        
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        soporte = new JScrollPane(areaTexto);
        
        mensaje = new JTextField(25);
        enviarMensaje = new JButton("Enviar!");
        
        accionBoton();
        
    }
    
    public void anadirElementos()
    {
        texto.add(soporte, BorderLayout.CENTER);
        
        enviar.add(mensaje);
        enviar.add(enviarMensaje);
        
        general.add(texto, BorderLayout.CENTER);
        general.add(enviar, BorderLayout.SOUTH);
    
    }
    
    public void accionBoton()
    {
        enviarMensaje.addActionListener((ActionEvent e) ->
        {
            new Cliente(9999, persona + ". " + mensaje.getText() + "\n");
        });
    
    }

    @Override
    public void update(Observable o, Object arg)
    {
        areaTexto.append((String) arg);
    }
    
}
