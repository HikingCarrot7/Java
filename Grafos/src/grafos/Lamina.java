package grafos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Lamina extends JPanel implements MouseListener, MouseMotionListener, KeyListener
{

    private static final long serialVersionUID = 1L;

    private Timer timer;

    protected static ArrayList<Integer> xPos, yPos;

    protected static int[][] matrizAdyacencia;

    protected int posicion = -1, x, y;

    protected boolean moviendoVertice = false;
    protected boolean anadirArista = false;
    protected boolean borrandoArista = false;

    protected boolean controlPresionado = false;

    protected ImageIcon background;

    public Lamina()
    {
        xPos = new ArrayList<>();
        yPos = new ArrayList<>();
        matrizAdyacencia = new int[50][50];
        background = new ImageIcon("src/grafos/background.jpg");

        timer = new Timer(8, (ActionEvent e) ->
        {
            repaint();
        });

        timer.start();
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

        background.paintIcon(this, g2, 0, 0);

        dibujarAristas(g2);
        dibujarLinea(g2);
        dibujarVertices(g2);
        dibujarEtiquetas(g2);

        g2.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        anadirVertice(e);
        anadirArista(e);
        borrarVertice(e);
        borrarArista(e);
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if (moviendoVertice)
        {
            xPos.set(posicion, e.getX() - 25);
            yPos.set(posicion, e.getY() - 50);
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        moverVertice(e);
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (moviendoVertice)
        {
            moviendoVertice = false;

            posicion = -1;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        if (anadirArista)
        {
            x = e.getX();
            y = e.getY();
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL)
        {
            controlPresionado = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            anadirArista = false;
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (controlPresionado)
        {
            controlPresionado = false;
        }
    }

    public void dibujarVertices(Graphics2D g)
    {
        if (xPos.size() > 0)
        {
            for (int i = 0; i < xPos.size(); i++)
            {
                if (Colores.coloresVert[i] == 2)
                {
                    pintarVertices(Color.red, i, g);

                } else if (Colores.coloresVert[i] == 3)
                {
                    pintarVertices(Color.yellow, i, g);

                } else if (Colores.coloresVert[i] == 4)
                {
                    pintarVertices(Color.green, i, g);

                } else
                {
                    pintarVertices(Color.blue, i, g);
                }
            }
        }
    }

    public void pintarVertices(Color color, int i, Graphics2D g)
    {
        g.setColor(Color.orange);
        g.fillOval(xPos.get(i), yPos.get(i), 50, 50);
        g.setStroke(new BasicStroke(5));
        g.setColor(color);
        g.drawOval(xPos.get(i), yPos.get(i), 50, 50);
    }

    public void dibujarAristas(Graphics2D g)
    {
        g.setColor(Color.magenta);
        g.setStroke(new BasicStroke(5));

        for (int i = 0; i < xPos.size(); i++)
        {
            for (int j = 0; j < xPos.size(); j++)
            {
                if (matrizAdyacencia[i][j] == 1)
                {
                    g.drawLine(xPos.get(i) + 25, yPos.get(i) + 25, xPos.get(j) + 25, yPos.get(j) + 25);
                }
            }
        }
    }

    public void dibujarEtiquetas(Graphics2D g)
    {
        int cont = 1;

        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 18));

        for (int i = 0; i < xPos.size(); i++)
        {
            g.drawString("" + cont++, xPos.get(i) + 21, yPos.get(i) + 31);
        }
    }

    public void dibujarLinea(Graphics2D g)
    {
        g.setColor(Color.magenta);
        g.setStroke(new BasicStroke(5));

        if (anadirArista && !borrandoArista)
        {
            g.drawLine(xPos.get(posicion) + 25, yPos.get(posicion) + 25, x, y);
        }
    }

    public void anadirVertice(MouseEvent e)
    {
        if (e.getButton() == 1 && !anadirArista && !borrandoArista)
        {
            posicion = DistanciasPuntos.mouseTocandoVert(e.getX() - 25, e.getY() - 50);

            if (e.getButton() == 1 && posicion < 0)
            {
                xPos.add(e.getX() - 25);
                yPos.add(e.getY() - 50);

                Colores.colores();
            }
        }
    }

    public void moverVertice(MouseEvent e)
    {
        if (e.getButton() == 1 && !anadirArista && !borrandoArista)
        {
            posicion = DistanciasPuntos.mouseTocandoVert(e.getX() - 25, e.getY() - 50);

            if (posicion >= 0)
            {
                moviendoVertice = true;
            }
        }
    }

    public void anadirArista(MouseEvent e)
    {
        if (e.getButton() == 3 && xPos.size() > 1 && !anadirArista && !borrandoArista)
        {
            posicion = DistanciasPuntos.mouseTocandoVert(e.getX() - 25, e.getY() - 50);

            if (posicion >= 0)
            {
                anadirArista = true;

                x = e.getX();
                y = e.getY();
            }

        } else if (e.getButton() == 3 && anadirArista)
        {
            int vertice2 = DistanciasPuntos.mouseTocandoVert(e.getX() - 25, e.getY() - 50);

            if (vertice2 != posicion && vertice2 >= 0 && vertice2 <= 25)
            {
                matrizAdyacencia[posicion][vertice2] = 1;
                matrizAdyacencia[vertice2][posicion] = 1;

                Colores.colores();

                anadirArista = false;
            }
        }
    }

    public void borrarVertice(MouseEvent e)
    {
        if (e.getButton() == 1 && controlPresionado)
        {
            posicion = DistanciasPuntos.mouseTocandoVert(e.getX() - 25, e.getY() - 50);

            if (posicion >= 0)
            {
                moverMatriz(posicion);

                xPos.remove(posicion);
                yPos.remove(posicion);

            }
        }
    }

    public void moverMatriz(int index)
    {
        for (int i = index; i < xPos.size(); i++)
        {
            for (int j = 0; j < xPos.size(); j++)
            {
                matrizAdyacencia[i][j] = matrizAdyacencia[i + 1][j];
            }
        }

        for (int i = 0; i < xPos.size(); i++)
        {
            for (int j = index; j < xPos.size(); j++)
            {
                matrizAdyacencia[i][j] = matrizAdyacencia[i][j + 1];
            }
        }
    }

    public void borrarArista(MouseEvent e)
    {
        if (e.getButton() == 3 && controlPresionado && !borrandoArista)
        {
            posicion = DistanciasPuntos.mouseTocandoVert(e.getX() - 25, e.getY() - 50);

            if (posicion >= 0)
            {
                borrandoArista = true;
            }

        } else if (e.getButton() == 3 && borrandoArista)
        {
            int vertice2 = DistanciasPuntos.mouseTocandoVert(e.getX() - 25, e.getY() - 50);

            if (vertice2 >= 0 && vertice2 != posicion)
            {
                matrizAdyacencia[posicion][vertice2] = 0;
                matrizAdyacencia[vertice2][posicion] = 0;

                borrandoArista = false;

            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }
}
