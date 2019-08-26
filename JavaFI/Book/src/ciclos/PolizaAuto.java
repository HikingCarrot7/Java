package ciclos;

public class PolizaAuto
{

    private int numeroCuenta;
    private String marcaYModelo;
    private String estado;

    //Constructor
    public PolizaAuto(int numeroCuenta, String marcaYModelo, String estado)
    {
        this.numeroCuenta = numeroCuenta;
        this.marcaYModelo = marcaYModelo;
        this.estado = estado;
    }

    //M�todo establecerCuenta 
    public void establecerNumeroCuenta(int numeroCuenta)
    {
        this.numeroCuenta = numeroCuenta;
    }

    //M�todo obtenerCuenta (Lo devolver� a quien lo solicite)
    public int obtenerNumeroCuenta()
    {
        return numeroCuenta;
    }

    //M�todo establecer marca y modelo 
    public void establecerMarcaYModelo(String marcaYModelo)
    {
        this.marcaYModelo = marcaYModelo;
    }

    //M�todo obtener marca y modelo (Lo devolver� a quien lo solicite)
    public String obtenerMarcaYModelo()
    {
        return marcaYModelo;
    }

    //M�todo establecer estado
    public void establecerEstado(String estado)
    {
        this.estado = estado;
    }

    //M�todo obtener estado (Lo devolver� a quien lo solicite)	
    public String obtenerEstado()
    {
        return estado;
    }

    //M�todo predicado que devuelve sin el estado tienen seguro sin culpa
    public boolean esEstadoSinCulpa()
    {
        boolean estadoSinCulpa;

        switch (obtenerEstado())
        { //Invoca el valor de estado

            case "MA":
            case "NJ":
            case "NY":
            case "PA":
                estadoSinCulpa = true;
                break;

            default:
                estadoSinCulpa = false;
                break;
        }

        return estadoSinCulpa;
    }
}
