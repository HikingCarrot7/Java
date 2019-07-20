package minichat;

/**
 *
 * @author HikingCarrot
 */
public class PruebaChat 
{
    public static void main(String[] args) 
    {
        Servidor s = new Servidor(9999);
        
        new Chat(s, 1);
    }
    
}
