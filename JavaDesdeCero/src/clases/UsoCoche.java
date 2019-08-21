package clases;

public class UsoCoche
{

    public static void main(String[] args)
    {
        //Instanciar una clase (Nissan es una instancia de la clase Coche). Ejemplar de la clase Coche.
        Coche Nissan = new Coche();
        //Busca la clase coche//Se invoca al constructor

        Nissan.establecerColor("Amarillo");

        System.out.println("Las ruedas del auto son " + Nissan.obtenerRuedas());

        System.out.println("El volumen del motor es " + Nissan.obtenerMotor());

        System.out.println("El color es " + Nissan.obtenerColor());

        Nissan.establecerColor("Azul");

        System.out.println("El color es " + Nissan.obtenerColor());

    }

}
