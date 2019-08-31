package vehiculo_herencia;

public class UsoVehiculo
{

    public static void main(String[] args)
    {

        //Array de objetos de tipo vehiculo
        Vehiculo[] misVehiculos = new Vehiculo[3];

        misVehiculos[0] = new Camioneta("Marco Gonzalez", "Camioneta", "YZY-584-A", 2.5, 1.5, 250);
        misVehiculos[1] = new Motos("Luis Mena", "Moto", "YWZ-569-C", 1.2, 0.8, 2);
        misVehiculos[2] = new Triciclo("Iv�n Ojeda", "Triciclo", "PDA-458-B", 1.8, 1, 40);

        System.out.println("-->Veh�culos<--");

        for (Vehiculo i : misVehiculos)
            /*Polimorfismo en acci�n. La variable i de tipo objeto llama al m�todo toString de cada clase. N�tese que cada m�todo toString de cada clase es diferente*/
            System.out.println(i);

        /*Imprimimos el n�mero de elementos de veh�culos que se registraron (3). Se llama al m�todo est�tico de la clase veh�culo (este m�dodo le pertenece a la clase, no a 
         * los objetos)*/
        System.out.println(Vehiculo.getVehiculosRegistrados());

    }

}
