package utilidades;

import java.util.Calendar;

/**
 *
 * @author HikingCarrot7
 */
public class Fechas
{

    public static void main(String[] args)
    {
        Calendar fechaHora = Calendar.getInstance();
        
        System.out.println(fechaHora.get(Calendar.MONTH));

        System.out.printf("%tc\n", fechaHora);// jue ago 22 18:10:44 CDT 2019
        System.out.printf("%tF\n", fechaHora);// 2019-08-22
        System.out.printf("%tD\n", fechaHora);// 08/22/19
        System.out.printf("%tr\n", fechaHora);// 06:11:19 PM
        System.out.printf("%tT\n", fechaHora);// 18:10:44

        System.out.printf("%1$tA, %1$tB %1$td, %1$tY\n", fechaHora);//jueves, agosto 22, 2019

        System.out.printf("%1$TA, %1$TB %1$Td, %1$TY\n", fechaHora);//JUEVES, AGOSTO 22, 2019
        System.out.printf("%1$ta, %1$tb %1$te, %1$ty\n", fechaHora);//jue, ago 22, 19

        System.out.printf("%1$tH:%1$tM:%1$tS\n", fechaHora);//18:12:58
        System.out.printf("%1$tZ %1$tI:%1$tM:%1$tS %Tp", fechaHora);//CDT 06:12:58 PM

    }
}
