package gatomejorado;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Lamina extends JPanel implements MouseListener, KeyListener
{

    private static final long serialVersionUID = 1L;

    private Tablero tablero;
    private Timer timer;

    private int empate = 0;

    private boolean turno = true, gameOver = false, ganador = false;

    public Lamina()
    {
        tablero = new Tablero();
        timer = new Timer(60, (e) ->
        {
            if (!gameOver)
                repaint();
        });

        timer.start();
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        g.setColor(Color.pink);
        g.fillRect(0, 0, 1000, 1000);

        tablero.dibujar((Graphics2D) g);

        if (ganador || empate == 9)
        {
            gameOver = true;

            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 50));

            if (turno && empate != 9)
                g.drawString("�Ha ganado el jugador 2!", 230, 100);
            else if (!turno && empate != 9)
                g.drawString("�Ha ganado el jugador 1!", 230, 100);
            else
                g.drawString("�Hubo un empate!", 300, 100);
        }

        g.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        int x = e.getX(), y = e.getY();
        boolean legal;

        x = (int) Math.floor((x -= 200) / 200);
        y = (int) Math.floor((y -= 225) / 200);

        if (x >= 0 && x < 3 && y >= 0 && y < 3)
        {
            legal = validarPunto(x, y);

            actualizarTablero(legal, x, y);
        }
    }

    public boolean validarPunto(int x, int y)
    {
        if (!turno && tablero.tablero[x][y] == 0)
        {
            tablero.tablero[x][y] = 2;

            return true;

        } else if (turno && tablero.tablero[x][y] == 0)
        {
            tablero.tablero[x][y] = 1;

            return true;

        } else
            return false;
    }

    public void actualizarTablero(boolean legal, int x, int y)
    {
        if (legal)
        {
            if (turno)
            {
                tablero.modificarTablero(x, y, 1);
                ganador = validacion();

            } else
            {
                tablero.modificarTablero(x, y, 2);
                ganador = validacion();
            }

            empate -= ganador ? 1 : -1;

            turno = !turno;
        }
    }

    public boolean validacion()
    {
        int cont_filas = 0, cont_columnas = 0, cont_principal = 0, cont_inversa = 0;

        for (int i = 0; i < tablero.tablero.length; i++)
        {
            for (int j = 0; j < tablero.tablero.length; j++)
            {
                if (tablero.tablero[i][j] == 0 || (tablero.tablero[i][0] != tablero.tablero[i][1] || tablero.tablero[i][0] != tablero.tablero[i][2]))
                    cont_filas++;

                if (i == j && (tablero.tablero[i][j] == 0 || tablero.tablero[0][0] != tablero.tablero[1][1] || tablero.tablero[0][0] != tablero.tablero[2][2]))
                    cont_principal++;

                if (tablero.tablero[j][i] == 0 || (tablero.tablero[0][i] != tablero.tablero[1][i] || tablero.tablero[0][i] != tablero.tablero[2][i]))
                    cont_columnas++;
            }

            if (cont_filas == 0 || cont_principal == 0 || cont_columnas == 0)
                return true;

            cont_columnas = 0;

            cont_filas = 0;
        }

        for (int i = 2, j = 0; i >= 0; i--, j++)
        {
            if (tablero.tablero[i][j] == 0 || tablero.tablero[2][0] != tablero.tablero[1][1] || tablero.tablero[2][0] != tablero.tablero[0][2])
                cont_inversa++;

            if (cont_inversa == 0)
                return true;
        }

        return false;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            tablero = new Tablero();

            empate = 0;
            turno = true;
            ganador = false;
            gameOver = false;

            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0)
    {
    }

    @Override
    public void mouseExited(MouseEvent arg0)
    {
    }

    @Override
    public void mousePressed(MouseEvent arg0)
    {
    }

    @Override
    public void mouseReleased(MouseEvent arg0)
    {
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }
}
