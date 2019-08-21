package clasesabstractas;

public class UsoPersonas
{

    public static void main(String[] args)
    {

        Persona[] misPersonas = new Persona[3];

        misPersonas[0] = new Alumno("Javier", "21", "Soltero", false, false, "Feliz", "Masculino", "FMAT", 76.78);
        misPersonas[1] = new Empleado("Emilio", "56", "Casado", false, true, "Molesto", "Masculino", "EBN567", 5600, "FCA");
        misPersonas[2] = new Empleado("Viviana", "25", "Soltera", false, true, "Triste", "Femenino", "JGHD875", 7550, "FMAT");

        for (Persona i : misPersonas)
        {
            System.out.println(i);
        }

    }

}
