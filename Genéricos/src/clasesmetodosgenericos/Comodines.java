package clasesmetodosgenericos;

import javax.swing.JButton;

public class Comodines
{

    private static Generica<A> a;

    private static Generica<B> b;

    private static Generica<A> c;

    private static Generica<C> d;

    private static Generica<A> e;

    private static Generica<JButton> f;

    public static void main(String[] args)
    {
        a = new Generica<>(new A("Nicolás"));

        b = new Generica<>(new B("Javier", 23));

        c = new Generica<>(new B("Daniel", 45));

        d = new Generica<>(new C("Irma", 67, 5400));

        e = new Generica<>(new C("Carlos", 56, 3400));

        f = new Generica<>(new JButton("Nicolás es una..."));

        Generica.imprimirValores(a);

        Generica.imprimirValores(b);

        Generica.imprimirValores(c);

        Generica.imprimirValores(d);

        Generica.imprimirValores(e);

        Generica.imprimirTexto(f);

        imprimirObjetos(new A("Nicolás"));

    }

    public static <T> void imprimirObjetos(T t)
    {
        System.out.println(t);
    }
}

class Generica<T>
{

    private T t;

    public Generica(T t)
    {
        this.t = t;
    }

    public T obtenerValor()
    {
        return t;
    }

    public String obtenerTexto()
    {
        return ((JButton) t).getActionCommand();
    }

    public static void imprimirValores(Generica<? extends A> valor)
    {
        System.out.println(valor.obtenerValor());
    }

    public static void imprimirTexto(Generica<JButton> boton)
    {
        System.out.println(boton.obtenerTexto());
    }
}

class A
{

    public String nombre;

    public A(String nombre)
    {
        this.nombre = nombre;
    }

    @Override
    public String toString()
    {
        return "Mi nombre es: " + nombre;
    }
}

class B extends A
{

    private final int edad;

    public B(String nombre, int edad)
    {
        super(nombre);

        this.edad = edad;
    }

    @Override
    public String toString()
    {
        return super.toString() + ", mi edad es: " + edad;
    }
}

class C extends B
{

    private final double sueldo;

    public C(String nombre, int edad, double sueldo)
    {
        super(nombre, edad);

        this.sueldo = sueldo;
    }

    @Override
    public String toString()
    {
        return super.toString() + " y mi sueldo es: $" + sueldo;
    }

}
