package applets;

import javax.swing.JApplet;
import javax.swing.JLabel;

public class Applet extends JApplet
{

    private static final long serialVersionUID = 1L;

    @Override
    public void init()
    {
        add(new JLabel("Sample test", JLabel.CENTER));
    }

}
