package clases;

public class Coche
{

    //--Private-- para encapsular y evitar que estas variables sean modificadas en otras clases

    private int ruedas, largo, ancho, motor, peso_plataforma;

    private String color = "";

    private boolean asientos_cuero, climatizador;

    public Coche()
    {
        ruedas = 4;
        largo = 2000;
        ancho = 300;
        motor = 1600;
        peso_plataforma = 500;

    }

    public int obtenerRuedas()
    {

        return ruedas;

    }

    public int obtenerMotor()
    {

        return motor;

    }

    public void establecerColor(String color)
    {

        this.color = color;

    }

    public String obtenerColor()
    {

        return color;

    }

}
