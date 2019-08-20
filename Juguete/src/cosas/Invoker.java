package cosas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author HikingCarrot7
 */
public class Invoker extends JFrame
{

    private final JButton b;

    public Invoker()
    {
        b = new JButton(new AbstractAction("Calcular")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                SwingUtilities.invokeLater(() ->
                {
                    fibonacci(70);

                });
            }
        });

        add(b);

        setTitle("Fibonacci");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private int fibonacci(int n)
    {
        if (n == 0 || n == 1)
        {
            return 1;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            new Invoker();

        });

    }

}

class Background extends SwingWorker<Integer, Integer>
{

    @Override
    protected Integer doInBackground() throws Exception
    {
        publish(7);

        return 7;
    }

    @Override
    protected void process(List<Integer> n)
    {

    }

    @Override
    protected void done()
    {
        try
        {
            System.out.println(get());

        } catch (InterruptedException | ExecutionException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

}
