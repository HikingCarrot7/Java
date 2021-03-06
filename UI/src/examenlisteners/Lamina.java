package examenlisteners;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.security.SecureRandom;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Lamina extends JPanel implements MouseListener, MouseMotionListener
{

    private static final long serialVersionUID = 1L;

    //Clases
    private class Circular
    {

        private int radioX;
        private int radioY;
        private double teta;
        private double inicio;
        private Color color;

        private Point centro;
        private Point movimiento;

        public Circular(int radioX, int radioY, double teta, Point centro, Color color)
        {
            this.radioX = radioX;
            this.radioY = radioY;
            this.centro = centro;
            this.teta = teta;
            this.color = color;

            inicio = 0;
        }

        public void dibujar(Graphics g)
        {
            movimiento = trayectoria(inicio -= teta);

            g.setColor(color);
            g.fillOval(movimiento.x, movimiento.y, 10, 10);

            g.drawOval(centro.x - radioX, centro.y - radioY, radioX * 2, radioY * 2);
        }

        public Point trayectoria(double t)
        {
            return new Point((int) (radioX * Math.cos(t) + centro.x), (int) (radioY * Math.sin(t) + centro.y));
        }

    }

    private class Lineas
    {

        private int x1;
        private int y1;
        private int x2;
        private int y2;
        private Color color;

        public Lineas(int x1, int y1, int x2, int y2, Color color)
        {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;

        }

        public void setX1(int x1)
        {
            this.x1 = x1;
        }

        public void setY1(int y1)
        {
            this.y1 = y1;
        }

        public void setX2(int x2)
        {
            this.x2 = x2;
        }

        public void setY2(int y2)
        {
            this.y2 = y2;
        }

        public void dibujar(Graphics g)
        {
            g.setColor(color);
            g.drawLine(x1, y1, x2, y2);
        }

    }

    private class Zoom
    {

        private Point centro;
        private int radio;
        private Color color;
        private int cambio;
        private int razon;
        private int tamano;

        public Zoom(Point centro, int radio, int razon, int tamano, Color color)
        {
            this.centro = centro;
            this.radio = radio;
            this.razon = razon;
            this.tamano = tamano;
            this.color = color;
            cambio = 20;
        }

        public void dibujar(Graphics g)
        {
            g.setColor(color);
            g.fillOval(centro.x - cambio, centro.y - cambio, radio + cambio * 2, radio + cambio * 2);

            if (cambio > tamano || cambio < 20)
                razon *= -1;

            cambio += razon;
        }
    }

    private class Circulos
    {

        private int diametro;
        private Color color;
        private Point centro;

        public Circulos(int diametro, Point centro, Color color)
        {
            this.diametro = diametro;
            this.centro = centro;
            this.color = color;
        }

        public int getX()
        {
            return centro.x;
        }

        public int getY()
        {
            return centro.y;
        }

        public int getRadio()
        {
            return diametro / 2;
        }

        public void setX(int x)
        {
            centro.x = x - distX;
        }

        public void setY(int y)
        {
            centro.y = y - distY;
        }

        public void dibujar(Graphics g)
        {
            g.setColor(color);
            g.fillOval(centro.x, centro.y, diametro, diametro);
        }
    }

    //Campos de la clase
    Timer timer;
    SecureRandom rand;
    Circulos[] circulos;
    Circular[] circular;
    Lineas[] lineas;
    Zoom[] zoom;

    private boolean generacionCircular = false;
    private boolean generacionLineas = false;
    private boolean generacionZoom = false;
    private boolean generacionCirculos = false;
    private boolean moviendoCirculo = false;

    private int posicion = -1;
    private int distX;
    private int distY;

    Point[] conexiones;

    //constructor
    public Lamina()
    {
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ctrl A"), "Desactivar");

        getActionMap().put("Desactivar", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                apagar();
            }
        });

        timer = new Timer(60, (e) ->
        {
            repaint();
        });

        rand = new SecureRandom();
        generarCirculos();
        timer.start();
    }

    @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (generacionCircular)
        {
            for (Circular c : circular)
                c.dibujar(g);

            if (!generacionLineas)
                generarLineas();

            for (Lineas l : lineas)
                l.dibujar(g);

            updateLines();

        } else if (generacionZoom)
            for (Zoom z : zoom)
                z.dibujar(g);
        else if (generacionCirculos)
            for (Circulos c : circulos)
                c.dibujar(g);

        g.dispose();

    }

    public void generarCircular()
    {
        circular = new Circular[30];

        for (int i = 0; i < circular.length; i++)
        {
            int radioX = 20 + rand.nextInt(300);
            int radioY = 20 + rand.nextInt(300);
            double teta = -100 + rand.nextInt(200);
            Point centro = new Point(50 + rand.nextInt(1800), 50 + rand.nextInt(900));
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));

            circular[i] = new Circular(radioX, radioY, teta / 1000, centro, color);
        }

        generacionZoom = false;
        generacionCircular = true;
        generacionCirculos = false;
    }

    public void generarCirculos()
    {
        circulos = new Circulos[10];

        for (int i = 0; i < circulos.length; i++)
        {
            int diametro = 30 + rand.nextInt(200);
            Point centro = new Point(50 + rand.nextInt(800), 50 + rand.nextInt(800));
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));

            circulos[i] = new Circulos(diametro, centro, color);
        }

        generacionZoom = false;
        generacionCircular = false;
        generacionCirculos = true;
    }

    public void generarLineas()
    {
        lineas = new Lineas[30];

        conexiones = new Point[30];

        for (int i = 0; i < lineas.length; i++)
        {
            int x = rand.nextInt(circular.length);
            int y = rand.nextInt(circular.length);
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));

            lineas[i] = new Lineas(circular[x].movimiento.x + 5, circular[x].movimiento.y + 5, circular[y].movimiento.x + 5, circular[y].movimiento.y + 5, color);

            conexiones[i] = new Point(x, y);
        }

        generacionLineas = !generacionLineas;
    }

    public void updateLines()
    {
        for (int i = 0; i < lineas.length; i++)
        {
            lineas[i].setX1(circular[conexiones[i].x].movimiento.x);
            lineas[i].setY1(circular[conexiones[i].x].movimiento.y);
            lineas[i].setX2(circular[conexiones[i].y].movimiento.x);
            lineas[i].setY2(circular[conexiones[i].y].movimiento.y);
        }
    }

    public void generarZoom()
    {
        zoom = new Zoom[20];

        for (int i = 0; i < zoom.length; i++)
        {
            Point centro = new Point(50 + rand.nextInt(700), 50 + rand.nextInt(700));
            int radio = 5 + rand.nextInt(30);
            int tamano = 100 + rand.nextInt(300);
            int razon = 1 + rand.nextInt(5);
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));

            zoom[i] = new Zoom(centro, radio, razon, tamano, color);
        }

        generacionZoom = true;
        generacionCircular = false;
        generacionCirculos = false;

    }

    public double distanciaPuntos(int x1, int y1, int x2, int y2)
    {
        return (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }

    public int mouseTocandoVert(int mouseX, int mouseY)
    {
        posicion = -1;

        for (int i = 0; i < circulos.length; i++)
        {
            double dist = distanciaPuntos(mouseX, mouseY, circulos[i].getX() + circulos[i].getRadio(), circulos[i].getY() + circulos[i].getRadio());

            if (dist <= circulos[i].getRadio())
            {
                posicion = i;
                break;
            }
        }

        return posicion;
    }

    public void apagar()
    {
        generacionCircular = false;
        generacionLineas = false;
        generacionZoom = false;
        generacionCirculos = false;
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if (moviendoCirculo)
        {
            circulos[posicion].setX(e.getX());
            circulos[posicion].setY(e.getY());
        }

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        posicion = mouseTocandoVert(e.getX() - 10, e.getY() - 30);

        if (posicion >= 0)
        {
            moviendoCirculo = true;

            distX = e.getX() - circulos[posicion].getX();
            distY = e.getY() - circulos[posicion].getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        moviendoCirculo = false;
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }
}
