package utilidades;

/**
 *
 * @author HikingCarrot7
 */
public class Email
{

    public static void main(String[] args)
    {
        System.out.println(validarFormatoEmail("ricardoibarra2044@gmail.com.mx"));
        System.out.println(validarFormatoEmail("ricardoibarra2044gmail.com.mx"));
        System.out.println(validarFormatoEmail("ricardoibarra2044@gmail.com"));
        System.out.println(validarFormatoEmail("a4@gmail.com"));
        System.out.println(validarFormatoEmail("ricardoibarra2044@gmail.com"));
    }

    private static boolean validarFormatoEmail(String email)
    {
        return email.matches("([a-zA-z0-9_]{4,}.?)+@([a-zA-Z]{3,}.?)+.([a-zA-Z]+){2,}");
    }
}
