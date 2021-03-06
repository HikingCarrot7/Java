package firsttry;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.security.*;

public class Gameplay extends JPanel implements KeyListener, ActionListener
{

    private static final long serialVersionUID = 1L;

    private class Rectangulos
    {

        private int xPos;
        private int yPos;
        private int ancho;
        private int largo;

        public Rectangulos(int xPos, int yPos, int ancho, int largo)
        {
            this.xPos = xPos;
            this.yPos = yPos;
            this.ancho = ancho;
            this.largo = largo;
        }

        public int getxPos()
        {
            return xPos;
        }

        public int getyPos()
        {
            return yPos;
        }

        public int getAncho()
        {
            return ancho;
        }

        public int getLargo()
        {
            return largo;
        }

        public void setxPos(int xPos)
        {
            this.xPos = xPos;
        }

        public void setyPos(int yPos)
        {
            this.yPos = yPos;
        }

        public void dibujar(Graphics g)
        {
            g.setColor(new Color(0, 0, 255));
            g.fillRect(xPos, yPos, ancho, largo);
            g.drawRect(xPos, yPos += 2, ancho, largo);
        }
    }

    private Rectangle player1;
    private Rectangle ball;
    Rectangulos[] rect;
    private int velocidadX = 20;
    private int velocidadY = 30;
    private int puntuacion;
    private boolean gameOver;
    private ImageIcon player;
    private SecureRandom rand = new SecureRandom();
    private Timer timer;

    public Gameplay()
    {
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(60, this);

        puntuacion = 0;
        gameOver = false;

        player1 = new Rectangle(350, 700, 20, 20);
        ball = new Rectangle(rand.nextInt(500), rand.nextInt(400), 50, 50);

        rect = new Rectangulos[8];

        int x = rand.nextInt(700);

        rect[0] = new Rectangulos(-x, 10, 700, 80);
        rect[1] = new Rectangulos(-x + 800, 10, 700, 80);

        x = rand.nextInt(700);

        rect[2] = new Rectangulos(-x, 160, 700, 80);
        rect[3] = new Rectangulos(-x + 800, 160, 700, 80);

        x = rand.nextInt(700);

        rect[4] = new Rectangulos(-x, 310, 700, 80);
        rect[5] = new Rectangulos(-x + 800, 310, 700, 80);

        x = rand.nextInt(700);

        rect[6] = new Rectangulos(-x, 460, 700, 80);
        rect[7] = new Rectangulos(-x + 800, 460, 700, 80);

        timer.start();
    }

    @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.pink);
        g.fillRect(0, 0, 800, 800);

        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + puntuacion, 700, 20);

        player = new ImageIcon("src/firsttry/player.png");
        player.paintIcon(this, g, player1.x, player1.y);

        for (Rectangulos rectangulos : rect)
            rectangulos.dibujar(g);

        g.setColor(Color.GREEN);
        g.fillOval(ball.x, ball.y, 50, 50);

        if (gameOver)
        {
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 70));
            g.drawString("GAME OVER", 180, 390);

            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Presiona Enter", 300, 430);

            g.drawString("Puntuaci�n " + puntuacion, 300, 470);

            player = new ImageIcon("src/firsttry/playerdead.png");

            player.paintIcon(this, g, player1.x, player1.y);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (!gameOver)
        {
            puntuacion++;

            for (Rectangulos rectangulos : rect)
                if (player1.intersects(rectangulos.getxPos(), rectangulos.getyPos(), rectangulos.getAncho(), rectangulos.getLargo()) || player1.intersects(ball))
                    gameOver = true;

            for (int i = 0; i < rect.length; i++)
                if (new Rectangle(0, 730, 800, 10).intersects(rect[i].getxPos(), rect[i].getyPos(), rect[i].getAncho(), rect[i].getLargo()))
                {
                    int x = rand.nextInt(700);
                    rect[i].setxPos(-x);
                    rect[i].setyPos(50);

                    rect[i + 1].setxPos(-x + 800);
                    rect[i + 1].setyPos(50);
                }

            if (player1.x > 740)
                player1.x = 740;
            else if (player1.x < 0)
                player1.x = 0;

            if (player1.y > 720)
                player1.y = 720;
            else if (player1.y < 0)
                player1.y = 0;
            else if (player1.y < 300)
                player1.y = 300;

            if (ball.x > 750 || ball.x < 0)
                velocidadX *= -1;

            if (ball.y > 750 || ball.y < 0)
                velocidadY *= -1;

            ball.x += velocidadX;
            ball.y += velocidadY;

            repaint();

        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            player1.x += 30;

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            player1.x -= 30;

        if (e.getKeyCode() == KeyEvent.VK_UP)
            player1.y -= 30;

        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            player1.y += 30;

        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            if (gameOver)
            {
                int x = rand.nextInt(700);

                rect[0] = new Rectangulos(-x, 10, 700, 80);
                rect[1] = new Rectangulos(-x + 800, 10, 700, 80);

                x = rand.nextInt(700);

                rect[2] = new Rectangulos(-x, 160, 700, 80);
                rect[3] = new Rectangulos(-x + 800, 160, 700, 80);

                x = rand.nextInt(700);

                rect[4] = new Rectangulos(-x, 310, 700, 80);
                rect[5] = new Rectangulos(-x + 800, 310, 700, 80);

                x = rand.nextInt(700);

                rect[6] = new Rectangulos(-x, 460, 700, 80);
                rect[7] = new Rectangulos(-x + 800, 460, 700, 80);

                player1.x = 350;
                player1.y = 700;

                ball.x = rand.nextInt(500);;
                ball.y = rand.nextInt(400);;

                puntuacion = 0;
                gameOver = false;

                repaint();
            }
    }

    @Override
    public void keyReleased(KeyEvent arg0)
    {
    }

    @Override
    public void keyTyped(KeyEvent arg0)
    {
    }
}
