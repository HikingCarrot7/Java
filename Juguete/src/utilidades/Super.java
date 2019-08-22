package utilidades;

import java.util.ArrayList;

/**
 *
 * @author HikingCarrot7
 */
public class Super
{

    public static void main(String[] args)
    {
        ArrayList<C> lista = new ArrayList<>();

        C<E> c = new C<>();

        B<D> b = new B<>();

        System.out.println(c.getStatus(b));

        System.out.println(new C<>().getStatusC(new D()));

    }
}

class A<T>
{

    public String getStatus()
    {
        return "Estoy dentro de la clase A";
    }

    public String getStatusA()
    {
        return "Hola desde la clase A";
    }

}

class B<T> extends A
{

    @Override
    public String getStatus()
    {
        return "Estoy dentro de la clase B";
    }

    public String getStatusB()
    {
        return "Hola desde la clase B";
    }

    public void Saludar(T t)
    {

    }

}

class C<T extends D> extends B
{

    public String getStatus(B<? extends D> t)
    {

        return t.getStatusB();
    }

    public String getStatusC(T t)
    {
        return t.getStatusD();
    }

}

class D<T>
{

    public String getStatusD()
    {
        return "Estoy dentro de la clase D";
    }

}

class E extends D
{

    @Override
    public String getStatusD()
    {
        return "Estoy dentro de la clase E";
    }
}
