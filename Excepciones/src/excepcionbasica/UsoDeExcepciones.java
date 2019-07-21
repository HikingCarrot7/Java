package excepcionbasica;

public class UsoDeExcepciones
{

    public static void main(String[] args)
    {
        try
        {
            LanzaExcepciones();

        } catch (IndexOutOfBoundsException e)
        {
            System.out.println("Atrap� la excepci�n en m�todo main");
        }

        noLanzaExcepcion();
    }

    public static void LanzaExcepciones() throws IndexOutOfBoundsException
    {
        try
        {
            System.out.println("M�todo lanza excepci�n");

            throw new IndexOutOfBoundsException();

        } catch (IndexOutOfBoundsException a)
        {
            System.out.println("La Excepcion ha sido manejada");

            throw a;

        } finally
        {
            System.out.println("Se ejecut� el finally xd");
        }
    }

    public static void noLanzaExcepcion()
    {
        try
        {
            System.out.println("No lanzar� una excepci�n esta vez");

        } catch (Exception e)
        {
            System.out.println("No entrar� aqu�");

        } finally
        {
            System.out.println("Adi�s!");
        }
    }
}

/*class PruebaException extends Exception
 {
 private static final long serialVersionUID = 1L;

 public PruebaException() {};
	
 public PruebaException(String mensaje) 
 {
 super(mensaje);
 };

 }*/
