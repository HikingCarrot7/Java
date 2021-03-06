package tanks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.SecureRandom;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tanks extends JPanel implements ActionListener, KeyListener
{

    private static final long serialVersionUID = 1L;

    private class Balas
    {

        private int xPos;
        private int yPos;
        private int velocidadX;
        private int velocidadY;
        private int rebotes = 0;
        private Color color;

        public Balas(int xPos, int yPos, int velocidadX, int velocidadY, Color color)
        {
            this.xPos = xPos;
            this.yPos = yPos;
            this.velocidadX = velocidadX;
            this.velocidadY = velocidadY;
            this.color = color;
        }

        public void setRebotes()
        {
            rebotes++;
        }

        public int rebotes()
        {
            return rebotes;
        }

        public void velocidad(int velocidadX, int velocidadY)
        {
            this.velocidadX = velocidadX;
            this.velocidadY = velocidadY;
        }

        public int getVelocidadX()
        {
            return velocidadX;
        }

        public int getVelocidadY()
        {
            return velocidadY;
        }

        public void dibujar(Graphics g)
        {
            g.setColor(color);
            g.fillOval(xPos += velocidadX, yPos += velocidadY, 10, 10);
        }
    }

    //Variables---------
    private int vidasPlayer1;
    private int vidasPlayer2;
    private int yMov = 0;

    private boolean derecha1;
    private boolean izquierda1;
    private boolean arriba1;

    private boolean derecha2;
    private boolean izquierda2;
    private boolean arriba2;

    private boolean gameOver;

    private ArrayList<Balas> balas1;
    private ArrayList<Balas> balas2;
    private ArrayList<Integer> teclas;

    private SecureRandom rand;
    private Timer timer;

    private Rectangle[] rectangulos;
    private Rectangle player1;
    private Rectangle player2;

    private ImageIcon background;
    private ImageIcon lifes;
    private ImageIcon player1Sprite;

    public Tanks()
    {
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        timer = new Timer(60, this);

        //Posicion inicial jugador 1
        player1 = new Rectangle(5, 947, 20, 20);

        //Posicion inicial del jugador 2
        player2 = new Rectangle(969, 5, 20, 20);

        //Mapa
        rectangulos = new Rectangle[12];

        rectangulos[0] = new Rectangle(0, 0, 350, 150);
        rectangulos[1] = new Rectangle(700, 100, 200, 150);
        rectangulos[2] = new Rectangle(150, 250, 400, 300);
        rectangulos[3] = new Rectangle(150, 550, 150, 300);
        rectangulos[4] = new Rectangle(800, 500, 90, 300);
        rectangulos[5] = new Rectangle(650, 400, 30, 450);
        rectangulos[6] = new Rectangle(450, 50, 100, 100);
        rectangulos[7] = new Rectangle(450, 700, 100, 100);

        //Bordes
        rectangulos[8] = new Rectangle(0, 0, 5, 1000);
        rectangulos[9] = new Rectangle(0, 0, 1000, 5);
        rectangulos[10] = new Rectangle(989, 0, 5, 1000);
        rectangulos[11] = new Rectangle(0, 967, 1000, 5);

        //Balas
        balas1 = new ArrayList<Balas>();
        balas2 = new ArrayList<Balas>();
        teclas = new ArrayList<Integer>();

        init();

        rand = new SecureRandom();

        timer.start();
    }

    @Override
    public void paint(Graphics g)
    {
        //background
        background = new ImageIcon("src/tanks/background.jpg");

        if (gameOver)
        {
            g.fillRect(0, 0, 1000, 1000);

            background.paintIcon(this, g, 0, yMov += 30);

            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 70));

            if (vidasPlayer1 > 0)
            {
                g.drawString("Gan� el jugador 1", 220, 400);
                g.drawString("Presiona ENTER", 220, 480);

            } else
            {
                g.drawString("Gan� el jugador 2", 220, 400);
                g.drawString("Presiona ENTER", 220, 480);
            }

        } else
        {
            background.paintIcon(this, g, 0, 0);

            //player 1
            if (derecha1)
            {
                player1Sprite = new ImageIcon("src/tanks/playerright.jpg");
                player1Sprite.paintIcon(this, g, player1.x, player1.y);

            } else if (izquierda1)
            {
                player1Sprite = new ImageIcon("src/tanks/playerleft.jpg");
                player1Sprite.paintIcon(this, g, player1.x, player1.y);

            } else if (arriba1)
            {
                player1Sprite = new ImageIcon("src/tanks/playerarriba.jpg");
                player1Sprite.paintIcon(this, g, player1.x, player1.y);

            } else
            {
                player1Sprite = new ImageIcon("src/tanks/playerdown.jpg");
                player1Sprite.paintIcon(this, g, player1.x, player1.y);
            }

            //Player 2
            if (derecha2)
            {
                player1Sprite = new ImageIcon("src/tanks/player2right.jpg");
                player1Sprite.paintIcon(this, g, player2.x, player2.y);

            } else if (izquierda2)
            {
                player1Sprite = new ImageIcon("src/tanks/player2left.jpg");
                player1Sprite.paintIcon(this, g, player2.x, player2.y);

            } else if (arriba2)
            {
                player1Sprite = new ImageIcon("src/tanks/player2up.jpg");
                player1Sprite.paintIcon(this, g, player2.x, player2.y);

            } else
            {
                player1Sprite = new ImageIcon("src/tanks/player2down.jpg");
                player1Sprite.paintIcon(this, g, player2.x, player2.y);
            }

            //Balas jugador 1
            for (Balas bala : balas1)
                bala.dibujar(g);

            //Balas jugador 2
            for (Balas bala : balas2)
                bala.dibujar(g);

            //Vidas player 1
            g.setColor(Color.black);
            g.setFont(new Font("serif", Font.BOLD, 20));
            lifes = new ImageIcon("src/tanks/lifes.png");
            lifes.paintIcon(this, g, 120, 11);
            g.drawString(">Player 1: " + vidasPlayer1, 5, 25);

            //Vidas player 2
            lifes.paintIcon(this, g, 275, 11);
            g.drawString(">Player 2: " + vidasPlayer2, 160, 25);

            if (vidasPlayer1 < 1 || vidasPlayer2 < 1)
                gameOver = true;
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (!gameOver)
        {
            for (Rectangle rect : rectangulos)
                if (player1.intersects(rect) || player1.x < 0 || player1.x > 970 || player1.y < 0 || player1.y > 990)
                {
                    player1.x = 5;
                    player1.y = 947;

                    vidasPlayer1--;

                } else if (player2.intersects(rect) || player2.x < 0 || player2.x > 970 || player2.y < 0 || player2.y > 990)
                {
                    player2.x = 969;
                    player2.y = 5;

                    vidasPlayer2--;
                }

            for (int i = 0; i < balas2.size(); i++)
                if (player1.intersects(balas2.get(i).xPos, balas2.get(i).yPos, 10, 10))
                {
                    player1.x = 5;
                    player1.y = 947;

                    vidasPlayer1--;
                }

            for (int i = 0; i < balas1.size(); i++)
                if (player2.intersects(balas1.get(i).xPos, balas1.get(i).yPos, 10, 10))
                {
                    player2.x = 969;
                    player2.y = 5;

                    vidasPlayer2--;
                }

            choque(balas1);
            choque(balas2);
        }

        repaint();
    }

    public void choque(ArrayList<Balas> balas)
    {
        int x = 5, y = 5;

        for (int i = 0; i < balas.size(); i++)
        {
            Rectangle rectangulo = new Rectangle(balas.get(i).xPos - 10, balas.get(i).yPos - 10, 30, 30);

            if (balas.get(i).rebotes() == 15 || (balas.get(i).xPos < 0 || balas.get(i).xPos > 1000) || (balas.get(i).yPos < 0 || balas.get(i).yPos > 1000))
            {
                balas.remove(i);

                break;
            }

            for (Rectangle rect : rectangulos)
                if (rectangulo.intersects(rect))
                {
                    balas.get(i).setRebotes();

                    if (balas.get(i).getVelocidadX() > 0 && balas.get(i).getVelocidadY() > 0)
                        if (rect.intersects(balas.get(i).xPos, balas.get(i).yPos, 1, 35))
                        {
                            balas.get(i).yPos -= 20;
                            x = -15 + rand.nextInt(31);
                            y = -15 + rand.nextInt(15);

                        } else
                        {
                            balas.get(i).xPos -= 20;
                            x = -15 + rand.nextInt(15);
                            y = -15 + rand.nextInt(31);
                        }
                    else if (balas.get(i).getVelocidadX() < 0 && balas.get(i).getVelocidadY() < 0)
                        if (rect.intersects(balas.get(i).xPos + 5, balas.get(i).yPos - 50, 1, 1))
                        {
                            balas.get(i).yPos += 20;
                            x = -15 + rand.nextInt(31);
                            y = 1 + rand.nextInt(15);

                        } else
                        {
                            balas.get(i).xPos += 20;
                            x = 1 + rand.nextInt(15);
                            y = -15 + rand.nextInt(31);
                        }
                    else if (balas.get(i).getVelocidadX() < 0 && balas.get(i).getVelocidadY() > 0)
                        if (rect.intersects(balas.get(i).xPos, balas.get(i).yPos, 1, 35))
                        {
                            balas.get(i).yPos -= 20;
                            x = -15 + rand.nextInt(31);
                            y = -15 + rand.nextInt(15);

                        } else
                        {
                            balas.get(i).xPos += 20;
                            x = 1 + rand.nextInt(15);
                            y = -15 + rand.nextInt(31);
                        }
                    else if (balas.get(i).getVelocidadX() > 0 && balas.get(i).getVelocidadY() < 0)
                        if (rect.intersects(balas.get(i).xPos + 5, balas.get(i).yPos - 50, 1, 1))
                        {
                            balas.get(i).yPos += 20;
                            x = -15 + rand.nextInt(31);
                            y = 1 + rand.nextInt(15);

                        } else
                        {
                            balas.get(i).xPos -= 20;
                            x = -15 + rand.nextInt(15);
                            y = -15 + rand.nextInt(31);
                        }

                    balas.get(i).velocidad(x, y);

                    break;
                }
        }
    }

    public void disparar(boolean derecha, boolean izquierda, boolean arriba, ArrayList<Balas> balas, Color color, Rectangle player)
    {
        int x, y;

        if (derecha)
        {
            x = 1 + rand.nextInt(15);
            y = -15 + rand.nextInt(31);

        } else if (izquierda)
        {
            x = -15 + rand.nextInt(15);
            y = -15 + rand.nextInt(31);

        } else if (arriba)
        {
            x = -15 + rand.nextInt(31);
            y = -15 + rand.nextInt(15);

        } else
        {
            x = -15 + rand.nextInt(31);
            y = 1 + rand.nextInt(15);
        }

        balas.add(new Balas(player.x + player.width / 2, player.y + player.height / 2, x, y, color));
    }

    public void init()
    {
        gameOver = false;

        vidasPlayer1 = 10;
        vidasPlayer2 = 10;

        player1.x = 5;
        player1.y = 947;

        player2.x = 969;
        player2.y = 5;

        derecha1 = true;
        izquierda1 = false;
        arriba1 = false;

        derecha2 = false;
        izquierda2 = true;
        arriba2 = false;

        yMov = 0;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        teclas.add(e.getKeyCode());

        //Player 1
        if (teclas.contains(KeyEvent.VK_RIGHT))
        {
            derecha1 = true;
            izquierda1 = false;
            arriba1 = false;

            player1.x += 20;

        }

        if (teclas.contains(KeyEvent.VK_LEFT))
        {
            derecha1 = false;
            izquierda1 = true;
            arriba1 = false;

            player1.x -= 20;
        }

        if (teclas.contains(KeyEvent.VK_UP))
        {
            derecha1 = false;
            izquierda1 = false;
            arriba1 = true;

            player1.y -= 20;
        }

        if (teclas.contains(KeyEvent.VK_DOWN))
        {
            derecha1 = false;
            izquierda1 = false;
            arriba1 = false;

            player1.y += 20;
        }

        if (teclas.contains(KeyEvent.VK_ENTER))
            if (gameOver)
            {
                balas1.clear();
                balas2.clear();

                init();

            } else if (balas1.size() < 8)
                disparar(derecha1, izquierda1, arriba1, balas1, Color.green, player1);

        //Player 2
        if (teclas.contains(KeyEvent.VK_D))
        {
            derecha2 = true;
            izquierda2 = false;
            arriba2 = false;

            player2.x += 20;

        }

        if (teclas.contains(KeyEvent.VK_A))
        {
            derecha2 = false;
            izquierda2 = true;
            arriba2 = false;

            player2.x -= 20;
        }

        if (teclas.contains(KeyEvent.VK_W))
        {
            derecha2 = false;
            izquierda2 = false;
            arriba2 = true;

            player2.y -= 20;
        }

        if (teclas.contains(KeyEvent.VK_S))
        {
            derecha2 = false;
            izquierda2 = false;
            arriba2 = false;

            player2.y += 20;
        }

        if (teclas.contains(KeyEvent.VK_SPACE))
            if (balas2.size() < 8)
                disparar(derecha2, izquierda2, arriba2, balas2, Color.yellow, player2);
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        teclas.clear();
    }

    @Override
    public void keyTyped(KeyEvent arg0)
    {
    }
}
