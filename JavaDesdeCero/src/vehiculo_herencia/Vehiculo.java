package vehiculo_herencia;

public class Vehiculo
{
    /*Campos de la clase*/

    private static int vehiculosRegistrados = 0;
    private double largo;
    private double ancho;
    private int pesoMotor;
    private int llantas;
    private int pesoPlataforma;
    private String placa;
    private String dueno;
    private String nombreVehiculo;

    /*Este constructor inicializa aquellos atributos que se comparten en todas las clases*/
    public Vehiculo(String dueno, String nombreVehiculo, String placa, double largo, double ancho)
    {
        /*El "this." hace distinci�n entre los campos de la clase y los par�metros que recibe el constructor*/
        this.dueno = dueno;
        this.nombreVehiculo = nombreVehiculo;
        this.placa = placa;
        this.largo = largo;
        this.ancho = ancho;

        /*Atributos por default*/
        vehiculosRegistrados++;
        pesoMotor = 120;
        llantas = 4;
        pesoPlataforma = 400;

    }

    //Getter datos
	/*Getter para retornar los datos que se comparten entre todos los objetos (cada objeto tiene su propia copia de estos datos)*/
    @Override
    public String toString()
    {

        return "\nDue�o: " + dueno + "\nNonmbre del veh�culo: " + nombreVehiculo + "\nPlaca: " + placa + "\nLargo: " + largo + " M Ancho: " + ancho + " M";

    }

    //Setter llantas
	/*No todas los veh�culos tienen las mismas llantas. Este m�todo ser� sobrescrito por las dem�s clases*/
    public int getLlantas()
    {
        return llantas;

    }

    //Setter pesoPlataforma
    public int getPesoPlataforma()
    {

        return pesoPlataforma;

    }

    //Setter motor
    public int getPesoMotor()
    {

        return pesoMotor;

    }

    /*Los m�todos static no le pertenecen a ning�n objeto (son de clase). Accedemos a este m�todo con Vehiculo.getVehiculosRegistrados en alg�n otro m�todo*/
    public static String getVehiculosRegistrados()
    {

        return "\nVeh�culos registrados: " + vehiculosRegistrados;

    }

}
