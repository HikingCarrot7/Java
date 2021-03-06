package practicadialogos;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class LaminaSecciones extends JPanel
{

    private static final long serialVersionUID = 1L;

    private ButtonGroup grupos;

    private JRadioButton boton;

    public LaminaSecciones(String titulo, String[] opciones)
    {
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), titulo));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        grupos = new ButtonGroup();

        for (int i = 1; i < opciones.length; i++)
        {

            boton = new JRadioButton(opciones[i]);

            boton.setActionCommand(opciones[i]);

            add(boton);

            grupos.add(boton);

            boton.setSelected(i == 1);

        }

    }

    @Override
    public String toString()
    {
        return grupos.getSelection().getActionCommand();
    }

}
