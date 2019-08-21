package reloj;

import java.util.Observable;
import java.util.Observer;

public class ImpresorReloj implements Observer
{

    private Thread thread;

    public void init()
    {
        Reloj r = new Reloj(6, 50, 55);

        r.addObserver(this);

        thread = new Thread(r);

        thread.start();
    }

    @Override
    public void update(Observable obs, Object obj)
    {
        System.out.println((String) obj);

    }

}
