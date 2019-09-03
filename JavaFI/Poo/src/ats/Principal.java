package ats;

import java.util.ArrayList;

public class Principal
{

    public static void main(String[] args)
    {

        ArrayList<Vehiculo> misVehiculos = new ArrayList<>();

        misVehiculos.add(new Vehiculo("GH67", "Ferrari", "A89"));
        misVehiculos.add(new VehiculoTurismo(4, "TY89", "Audi", "P14"));
        misVehiculos.add(new VehiculoTurismo(6, "TY21", "Aveo", "P187"));
        misVehiculos.add(new VehiculoDeportivo(500, "ZS43", "Toyota", "KJ8"));
        misVehiculos.add(new VehiculoFurgoneta(2000, "WA21", "Toyota", "HG3"));

        for (Vehiculo vehiculos : misVehiculos)
            System.out.println(vehiculos.mostrarDatos());

    }

}
