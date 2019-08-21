package cadenas;

public class Cadenas
{

    //Revisar la API de JAVA para checar todas las funciones 
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

        String palabra = "";
        String subPalabra = "";

        //boolean comparar = false;
        palabra += "Hola ";

        palabra += "soy ";

        palabra += "Nicol�s. ";

        //comparar = palabra.equalsIgnoreCase("Hola soy nicol�s. ");
        System.out.printf("Frase: %s\nEl �ltimo caracter es \"%c\"\n\n", palabra, palabra.charAt(palabra.length() - 3));

        subPalabra = palabra.substring(3);

        System.out.print(subPalabra + "\n");

        char[] secuencia = new char[palabra.length()];

        secuencia = palabra.toCharArray();

    }

}
