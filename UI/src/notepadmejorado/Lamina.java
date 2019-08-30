package notepadmejorado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.text.StyledEditorKit;

public final class Lamina extends JPanel
{

    private static final long serialVersionUID = 1L;

    private String[] misFuentes = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    private JMenuBar mibarra;

    private JToolBar herramientas;

    private ButtonGroup tamano, fuentes, herramientascolores, alineacionitems;

    private JTextPane texto;

    private JScrollPane soporteTexto;

    private JPanel soporteHerramientas;

    private JPopupMenu emergente;

    private JMenu[] opciones =
    {
        new JMenu("Font"), new JMenu("Style"), new JMenu("Size"), new JMenu("Text color")
    };

    private JMenuItem[] fuente;

    private JMenuItem[] estilo =
    {
        new JCheckBoxMenuItem("Bold"), new JCheckBoxMenuItem("Italic"), new JCheckBoxMenuItem("Underline")
    };

    private JMenuItem[] size =
    {
        new JRadioButtonMenuItem("12"), new JRadioButtonMenuItem("14"), new JRadioButtonMenuItem("16"), new JRadioButtonMenuItem("18"), new JRadioButtonMenuItem("24"), new JRadioButtonMenuItem("36")
    };

    private JMenuItem[] herramientasColor =
    {
        anadirColores(Color.yellow, "src/notepadmejorado/yellow.png"), anadirColores(Color.green, "src/notepadmejorado/green.png"), anadirColores(Color.pink, "src/notepadmejorado/pink.png"), anadirColores(Color.black, "src/notepadmejorado/black.png")
    };

    private JMenuItem[] alineacion =
    {
        anadirAlineacion(0, "src/notepadmejorado/left.png"), anadirAlineacion(2, "src/notepadmejorado/right.png"), anadirAlineacion(1, "src/notepadmejorado/center.png")
    };

    public Lamina()
    {
        setLayout(new BorderLayout());

        anadirFuentes();

        JMenuItem[][] items =
        {
            fuente, estilo, size, herramientasColor, alineacion
        };

        anadirElementos(items);

        anadirAcciones(items);

        anadirAlGrupo(items, 0, fuentes);
        anadirAlGrupo(items, 2, tamano);
        anadirAlGrupo(items, 3, herramientascolores);
        anadirAlGrupo(items, 4, alineacionitems);

        add(soporteTexto, BorderLayout.CENTER);

        add(soporteHerramientas, BorderLayout.WEST);

        add(mibarra, BorderLayout.NORTH);

    }

    public void anadirFuentes()
    {
        fuente = new JMenuItem[misFuentes.length - 235];

        for (int i = 0; i < misFuentes.length - 235; i++)
            fuente[i] = new JRadioButtonMenuItem(misFuentes[i]);

    }

    public void anadirElementos(JMenuItem[][] items)
    {
        soporteHerramientas = new JPanel();

        herramientas = new JToolBar(JToolBar.VERTICAL);

        soporteHerramientas.add(herramientas);

        texto = new JTextPane();

        soporteTexto = new JScrollPane(texto);

        soporteTexto.setWheelScrollingEnabled(true);

        mibarra = new JMenuBar();

        emergente = new JPopupMenu();

        anadirMenuEmergente();

        anadirHerramientas(herramientas, herramientasColor);

        herramientas.addSeparator();

        anadirHerramientas(herramientas, alineacion);

        texto.setComponentPopupMenu(emergente);

        for (int i = 0; i < items.length - 2; i++)
        {
            mibarra.add(opciones[i]);

            anadirItems(opciones[i], items[i]);
        }

    }

    public void anadirMenuEmergente()
    {
        JMenuItem cabecera = new JMenuItem("Set a background color...");
        cabecera.setEnabled(false);

        emergente.add(cabecera);
        emergente.add(new JMenuItem(new AccionEmergente("Yellow", Color.yellow)));
        emergente.add(new JMenuItem(new AccionEmergente("Green", Color.green)));
        emergente.add(new JMenuItem(new AccionEmergente("Blue", Color.blue)));
        emergente.add(new JMenuItem(new AccionEmergente("Pink", Color.pink)));
        emergente.add(new JMenuItem(new AccionEmergente("White", Color.white)));
    }

    public void anadirHerramientas(JToolBar herramientas, JMenuItem items[])
    {
        for (JMenuItem H : items)
            herramientas.add(H);

    }

    private class AccionEmergente extends AbstractAction
    {

        private static final long serialVersionUID = 1L;

        private Color color;

        public AccionEmergente(String titulo, Color color)
        {
            putValue(Action.NAME, titulo);
            this.color = color;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            texto.setBackground(color);
        }

    }

    public void anadirItems(JMenu opcion, JMenuItem[] items)
    {
        for (JMenuItem I : items)
            opcion.add(I);
    }

    public void anadirAcciones(JMenuItem[][] items)
    {

        for (JMenuItem F : fuente)
            F.addActionListener(new StyledEditorKit.FontFamilyAction("", F.getText()));

        for (JMenuItem S : size)
            S.addActionListener(new StyledEditorKit.FontSizeAction("", Integer.parseInt(S.getText())));

        anadirAtajos(items);

    }

    public void anadirAtajos(JMenuItem[][] items)
    {
        items[1][0].addActionListener(new StyledEditorKit.BoldAction());

        items[1][0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));

        items[1][1].addActionListener(new StyledEditorKit.ItalicAction());

        items[1][1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));

        items[1][2].addActionListener(new StyledEditorKit.UnderlineAction());

        items[1][2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));
    }

    public void anadirAlGrupo(JMenuItem[][] items, int indice, ButtonGroup grupo)
    {
        grupo = new ButtonGroup();

        for (JMenuItem M : items[indice])
            grupo.add(M);

    }

    public JRadioButtonMenuItem anadirColores(Color color, String ruta)
    {
        JRadioButtonMenuItem etiqueta = new JRadioButtonMenuItem(new ImageIcon(ruta));

        etiqueta.addActionListener(new StyledEditorKit.ForegroundAction("", color));

        return etiqueta;
    }

    public JRadioButtonMenuItem anadirAlineacion(int i, String ruta)
    {
        JRadioButtonMenuItem etiqueta = new JRadioButtonMenuItem(new ImageIcon(ruta));

        etiqueta.addActionListener(new StyledEditorKit.AlignmentAction("", i));

        return etiqueta;
    }
}
