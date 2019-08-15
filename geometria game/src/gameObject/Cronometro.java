package gameObject;

public class Cronometro
{

    private long delta, LastTime;
    private long time;
    private boolean running;

    public Cronometro()
    {
        delta = 0;
        LastTime = 0;
        running = false;

    }

    public void run(long time)
    {
        running = true;
        this.time = time;
    }

    public void update()
    {
        if (running)
        {
            delta += System.currentTimeMillis() - LastTime;
        }
        if (delta >= time)
        {
            running = false;
            delta = 0;
        }
        LastTime = System.currentTimeMillis();

    }

    public boolean isRunning()
    {
        return running;
    }
}
