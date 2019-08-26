package swing;

public class Juguete
{

    private static int matriz[][];

    private static int i = 1;

    public static void main(String[] args)
    {

        matriz = new int[5][5];

        new MiJuguete().imprimirMatriz(new Juguete()
        {
        });

    }

    public void matrizImp()
    {
        for (int[] I : matriz)
        {
            for (int J : I)
            {
                System.out.print(i < 10 ? "[0" + i++ + "]" : "[" + i++ + "]");
            }

            System.out.println("");
        }
    }

}

class MiJuguete
{

    public void imprimirMatriz(Juguete J)
    {
        J.matrizImp();
    }

}
