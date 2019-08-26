package reloj;

import java.util.Observable;

public class Reloj extends Observable implements Runnable
{

    private int horas, minutos, segundos;

    public Reloj(int horas, int minutos, int segundos)
    {
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    @Override
    public void run()
    {
        String tiempo;

        try
        {
            while (true)
            {
                tiempo = "";

                if (horas < 10)
                {
                    tiempo += "0" + horas;
                } else
                {
                    tiempo += horas;
                }

                tiempo += ":";

                if (minutos < 10)
                {
                    tiempo += "0" + minutos;
                } else
                {
                    tiempo += minutos;
                }

                tiempo += ":";

                if (segundos < 10)
                {
                    tiempo += "0" + segundos;
                } else
                {
                    tiempo += segundos;
                }

                setChanged();
                notifyObservers(tiempo);
                clearChanged();

                segundos++;

                Thread.sleep(1000);

                if (segundos == 60)
                {
                    minutos++;
                    segundos = 0;

                    if (minutos == 60)
                    {
                        horas++;
                        minutos = 0;

                        if (horas == 24)
                        {
                            horas = 0;
                        }
                    }

                }

            }

        } catch (InterruptedException e)
        {
            System.out.println(e.getMessage());
        }

    }

}
