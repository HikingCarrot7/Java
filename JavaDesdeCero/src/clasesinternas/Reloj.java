package clasesinternas;

import java.awt.Toolkit;
import java.util.Date;

import javax.swing.Timer;

public class Reloj
{

    public void enMarcha(int intervalo, final boolean sonido)
    {

        Timer miTemporizador = new Timer(intervalo, (e) ->
        {
            Date ahora = new Date();

            System.out.println("La hora es: " + ahora);

            if (sonido)
                Toolkit.getDefaultToolkit().beep();
        });

        miTemporizador.start();

    }
}
