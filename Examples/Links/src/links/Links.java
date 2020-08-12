package links;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;

/**
 *
 * @author HikingCarrot
 */
public final class Links extends JPanel
{   
    private JTextField areaIntroducir;
    private JEditorPane areaContenido;
    
    public Links()
    {
        iniciarElementos();
        anadirElementos();
    }
    
    public void iniciarElementos()
    {
        setLayout(new BorderLayout());
        
        areaIntroducir = new JTextField("Una URL?");
        areaIntroducir.addActionListener((ActionEvent e) ->
        {
            obtenerLaPagina(e.getActionCommand());
        });
        
        areaContenido = new JEditorPane();
        areaContenido.setEditable(false);
        areaContenido.addHyperlinkListener((HyperlinkEvent e) -> 
        {
            if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
                obtenerLaPagina(e.getURL().toString());
        });
    }
    
    public void anadirElementos()
    {
        add(new JScrollPane(areaContenido), BorderLayout.CENTER);
        add(areaIntroducir, BorderLayout.NORTH);
    }
    
    public void obtenerLaPagina(String ubicacion)
    {
        try 
        {
            areaContenido.setPage(ubicacion);
            areaIntroducir.setText(ubicacion);
            
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog(this, "Error al leer el URL", "URL incorrecto", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getMessage());
        }
    }
    
}
