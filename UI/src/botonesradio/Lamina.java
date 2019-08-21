package botonesradio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;

public class Lamina extends JPanel
{

    private static final long serialVersionUID = 1L;

    private ButtonGroup miGrupoColores, miGrupoTexto;

    private JPanel botonesColores, botonesTexto;

    private JLabel texto;

    public Lamina()
    {
        setLayout(new BorderLayout());

        texto = new JLabel("Terraria es lo m�ximo!", JLabel.CENTER);
        texto.setFont(new Font("serif", Font.PLAIN, 25));

        miGrupoColores = new ButtonGroup();
        miGrupoTexto = new ButtonGroup();

        botonesColores = new JPanel();
        botonesTexto = new JPanel();

        anadirBotonesColores(this, botonesColores, "Azul", Color.blue);
        anadirBotonesColores(this, botonesColores, "Verde", Color.green);
        anadirBotonesColores(this, botonesColores, "Amarillo", Color.yellow);

        anadirBotonesTexto(botonesTexto, "Negritas", true);
        anadirBotonesTexto(botonesTexto, "Cursivas", false);

        add(texto, BorderLayout.CENTER);
        add(botonesColores, BorderLayout.NORTH);
        add(botonesTexto, BorderLayout.SOUTH);

        anadirComandos("ctrl A", "Azul", Color.blue, botonesColores);
        anadirComandos("ctrl V", "Verde", Color.green, botonesColores);
        anadirComandos("ctrl M", "Amarillo", Color.yellow, botonesColores);

    }

    public void anadirBotonesColores(Container lamina, Container botones, String titulo, Color colorfondo)
    {
        JRadioButton boton = new JRadioButton(titulo/*new AccionBotonesColores(titulo, colorfondo, lamina)*/);

        miGrupoColores.add(boton);

        botonesColores.add(boton);

        boton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                lamina.setBackground(colorfondo);

            }

        });
    }

    public void anadirBotonesTexto(Container texto, String titulo, boolean fuente)
    {
        JRadioButton boton = new JRadioButton(new AccionBotonesTexto(titulo, fuente));

        miGrupoTexto.add(boton);

        botonesTexto.add(boton);
    }

    public void anadirComandos(String combinacion, String llave, Color color, Container botones)
    {
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(combinacion), llave);

        getActionMap().put(llave, new AccionBotonesColores(color, botones));
    }

    private class AccionBotonesColores extends AbstractAction
    {

        private static final long serialVersionUID = 1L;

        Container lamina;

        public AccionBotonesColores(String titulo, Color colorfondo, Container lamina)
        {
            putValue(Action.NAME, titulo);
            putValue(Action.SHORT_DESCRIPTION, "Pone el fondo de abajo de color " + titulo);
            putValue("Color", colorfondo);

            this.lamina = lamina;

        }

        public AccionBotonesColores(Color colorfondo, Container lamina)
        {
            putValue("Color", colorfondo);

            this.lamina = lamina;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            lamina.setBackground((Color) getValue("Color"));
        }
    }

    private class AccionBotonesTexto extends AbstractAction
    {

        private static final long serialVersionUID = 1L;

        public AccionBotonesTexto(String titulo, boolean fuente)
        {
            putValue(Action.NAME, titulo);
            putValue(Action.SHORT_DESCRIPTION, "Pone el texto de arriba en " + titulo);
            putValue("Fuente", fuente);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            texto.setFont((boolean) getValue("Fuente") ? new Font("serif", Font.BOLD, 25) : new Font("serif", Font.ITALIC, 25));

        }

    }

}
