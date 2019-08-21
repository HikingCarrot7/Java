package funciones_math;

public class FuncionesMath
{

    public static void main(String[] args)
    {

		//Las funciones b�sicas de Math reciben el �ngulo en radianes 
		/*Math.cos();, Math.sin();, Math.tan();, Math.atan();, Math.round();, Math.pow();, Math.sqrt();*/
        /*Podemos consultar todos los m�todos (aqu� en eclipse poniendo cuando escribamos el Math.)*/
        //Sufijo F para las variables de tipo float   ejem 5.56F
        //Refundiciones
        int base = 10;
        int exponente = 2;

        double resultado = Math.pow(base, exponente);

        /*
         numero = Math.sin(numero);
		
         numero = Math.toDegrees(numero);*/
        System.out.printf("El resultado de elevar %d a %d es: %.2f", base, exponente, resultado);

    }

}
