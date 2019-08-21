package disposicionespropias;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class Distribucion implements LayoutManager
{

    @Override
    public void addLayoutComponent(String arg0, Component arg1)
    {

    }

    @Override
    public void layoutContainer(Container c)
    {

        int n = c.getComponentCount();
        int x = c.getWidth() / 2 - 100, y = 20;

        for (int i = 0; i < n; i++)
        {
            Component componente = c.getComponent(i);

            componente.setBounds(x, y, 100, 20);

            x += 100;

            if ((i + 1) % 2 == 0)
            {
                x = c.getWidth() / 2 - 100;

                y += 30;

            }

        }

    }

    @Override
    public Dimension minimumLayoutSize(Container arg0)
    {

        return null;
    }

    @Override
    public Dimension preferredLayoutSize(Container arg0)
    {

        return null;
    }

    @Override
    public void removeLayoutComponent(Component arg0)
    {

    }

}
