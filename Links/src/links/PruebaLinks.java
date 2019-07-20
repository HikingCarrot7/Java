package links;

import javax.swing.JFrame;

/**
 *
 * @author HikingCarrot
 */
public class PruebaLinks extends JFrame
{
    private final Links links;
    
    public PruebaLinks()
    {
        links = new Links();
        
        setBounds(0, 0, 900, 800);
        setLocationRelativeTo(null);
        setResizable(true);
        setTitle("URLS!");
        add(links);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) 
    {
        new PruebaLinks();
    }
}
