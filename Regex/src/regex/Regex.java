package regex;

/**
 *
 * @author Mohammed
 */
public class Regex
{

    public static void main(String[] args)
    {
        System.out.printf("$%,.2f\n", 32498327498.6343);

        System.out.printf("%s", String.valueOf(validarCadena3("(Nicolas)")));

    }

    private static boolean validarCadena1(String cadena)
    {
        return cadena.matches("^([0-9]{8})*[A-Za-z]$");
    }

    private static boolean validarCadena2(String cadena)
    {
        return cadena.matches("[A-Za-z]?|[A-Za-z]+\\s[A-Za-z]+");
    }

    private static boolean validarCadena3(String cadena)
    {
        return cadena.matches("^([(][A-Z][a-z]+[)]\\s*){1,2}$");
    }

    private static boolean validarCadena4(String cadena)
    {
        return cadena.matches("(a-c)b");
    }
}
