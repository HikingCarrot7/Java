package clasesmetodosgenericos;

import java.util.ArrayList;

public class PasoDeArrays
{

    private static ArrayList<E> a;

    public static void main(String[] args)
    {

        a = new ArrayList<>();

        a.add(new E());

        imprimir(a);

    }

    public static void imprimir(ArrayList<? extends D> array)
    {
        System.out.println(array.get(0).getE());
    }

}

class D
{

    public int getD()
    {
        return 1;
    }

    public int getE()
    {
        return 2;
    }
}

class E extends D
{

    @Override
    public int getE()
    {
        return 3;
    }
}
