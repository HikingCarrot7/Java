package tiposenumerados;

import java.util.Scanner;

public class UsoTallas
{

    //enum Tallas {MINI, MEDIANO, GRANDE, MUY_GRANDE};
    enum Tallas
    {

        MINI("S"), MEDIANO("M"), GRANDE("L"), MUY_GRANDE("XL");

        private final String abreviatura;

        private Tallas(String abreviatura)
        {
            this.abreviatura = abreviatura;
        }

        public String getAbreviatura()
        {
            return abreviatura;
        }

    }

    public static void main(String[] args)
    {

        Scanner in = new Scanner(System.in);

        System.out.println("Introduce una talla: MINI, MEDIANO, GRANDE, MUY_GRANDE");
        String respuesta = in.nextLine().toUpperCase();

        Tallas laTalla = Enum.valueOf(Tallas.class, respuesta);

        System.out.println("Talla: " + laTalla);

        System.out.println("Abreviatura: " + laTalla.getAbreviatura());

        in.close();

    }

}
