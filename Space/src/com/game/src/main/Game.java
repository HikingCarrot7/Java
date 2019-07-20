package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.game.src.main.animations.ExplosionAnimation;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	public static final int ANCHO = 320;
	public static final int LARGO = 240;
	public static final int ESCALA = 2;
	private boolean running = false;
	private static boolean disparando = false;
	private Thread thread;
	
//	private BufferedImage image = new BufferedImage(ANCHO, LARGO, BufferedImage.TYPE_INT_BGR);
	
	private BufferedImage fondo;
	
	private static Game game;
	
	private static JFrame frame;
	
	private Player player;
	
	private Controller controller;
	
	private Menu menu;
	
	private Graphics2D g;
	
	private ExplosionAnimation explosion;
	
	private BufferStrategy bs;
	
	private int enemigosDestruidos = 0;
	
	private int lastfps = 0;
	
	private Rectangle reset;
	
	private int update = 0;
	private int frames = 0;
	private long timer = System.currentTimeMillis();
	
	public static int VIDA = 200;
	
	public static enum STATE {GAME, MENU};
	
	public static STATE state = STATE.MENU;
	
	public static void main(String[] args) 
	{
		
		frame = new JFrame("2D space game");
		
		game = new Game();
		
		game.setPreferredSize(new Dimension(ANCHO * ESCALA, LARGO * ESCALA));
		
		game.setMaximumSize(new Dimension(ANCHO * ESCALA, LARGO * ESCALA));
		
		game.setMinimumSize(new Dimension(ANCHO * ESCALA, LARGO * ESCALA));
		
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		game.start();
	}
	
	
	private synchronized void start() 
	{
		if(!running) 
		{
			running = true;
			
			thread = new Thread(this);
			
			thread.start();
			
		}
	}
	
	public synchronized void stop() 
	{
		if(running) 
		{
			running = false;
			
			System.exit(1);
		}
	}
	
	public void init() 
	{
		explosion = new ExplosionAnimation(false);
		
		createBufferStrategy(3);
		
		bs = getBufferStrategy();
		
		g = (Graphics2D) bs.getDrawGraphics();
		
		reset = new Rectangle(ANCHO/2 +  70, - 50, 200, 50);
		
		menu = new Menu(this);
		
		controller = new Controller(game);
		
		fondo = new BufferedImageLoader().loadImage("/res/background/background.jpg");
		
		player = new Player(ANCHO - 30, LARGO * ESCALA, controller, this);
		
		controller.generarEnemigo(Enemy.getEnemigosTotales());
		
		setFocusable(true);
		requestFocus();
		setFocusTraversalKeysEnabled(false);
		addKeyListener(new KeyInput(this));
		addMouseListener(new MouseInput(menu));
	}

	@Override
	public void run()
	{	
		init();
		
		long lastTime = System.nanoTime();
		final double amountOfThicks = 60.0;
		double ns = 1000000000 / amountOfThicks;
		double delta = 0; 
		
		update = 0;
		frames = 0;
		timer = System.currentTimeMillis();
		
		while(running) 
		{
			long now = System.nanoTime();
			
			delta += (now - lastTime) / ns;
			
			lastTime = now;
			
			if(delta >= 1) 
			{
				tick();
				update++;
				delta--;
			}
			
			render();
			
			frames++;
		}
		
		stop();	
	}
	
	private void tick() 
	{
		
		if(state.equals(STATE.GAME)) 
		{
			player.tick();
			
			controller.tick();
			
			if(enemigosDestruidos >= Enemy.getEnemigosTotales()) 
			{
				Enemy.sumarEnemigosTotales(2); 
				
				enemigosDestruidos = 0;
				
				controller.generarEnemigo(Enemy.getEnemigosTotales());		
			}
			
		}
		
	}
	
	private void render() 
	{
		bs = getBufferStrategy();
		
		g = (Graphics2D) bs.getDrawGraphics();
		
		g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
		
		if(state.equals(STATE.GAME)) 
		{
			if(VIDA > 0) 
				player.render(g);
			
			if(player.getMuerte()) 
			{
				g.setColor(Color.white);
				g.setFont(new Font("serif", Font.BOLD, 50));
				reset.y += 1;
				g.drawRect(reset.x, reset.y, reset.width, reset.height);
				g.drawString("Reset", ANCHO/2 +  70 + 40, reset.y + 42);
				g.drawString("You died!", reset.x - 5, reset.y - 20);
				
				if(reset.y >= LARGO) 
					reset.y = LARGO;
			} 
			
			controller.render(g);
			explosion.render(g);
			
			g.setColor(Color.gray);
			g.fillRect(5, 5, 200, 20);
			
			g.setColor(Color.green);
			g.fillRect(5, 5, VIDA, 20);
			
			g.setColor(Color.white);
			g.drawRect(5, 5, 200, 20);
			
		}else 
		{
			menu.render((Graphics2D)g);
		}
		
		dibujarFps(g);
		
		g.dispose();
		bs.show();
		
	}
	
	public void setCoordenadasExp(boolean iniciar, int x, int y) 
	{
		explosion.setIniciar(iniciar);
		explosion.setX(x);
		explosion.setY(y);
	}
	
	public int getEnemigosDestruidos() 
	{
		return enemigosDestruidos;
	}


	public void setEnemigosDestruidos(int enemigosDestruidos) 
	{
		this.enemigosDestruidos = enemigosDestruidos;
	}
	
	public Rectangle getReset() 
	{
		return reset;
	}
	
	public boolean getMuerte() 
	{
		return player.getMuerte();
	}
	
	public void reset() 
	{
		VIDA = 200;
		
		reset.y = - 50;
		
		player.setMuerte(false);
		
		enemigosDestruidos = 0;
		
		Enemy.setEnemigosTotales(5);
		
		player.setX(ANCHO - 30);
		
		player.setY(LARGO * ESCALA);
		
		controller.obtenerEntidadesB().clear();
		
		controller.generarEnemigo(Enemy.getEnemigosTotales());
		
	}
	
	public void dibujarFps(Graphics2D g) 
	{
		
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.PLAIN, 20));
		
		g.drawString("FPS: " + lastfps , ANCHO * ESCALA - 80, 20);
		
		if(System.currentTimeMillis() - timer > 100)
		{
			lastfps = frames;
			
			timer += 1000;
			
			update = 0;
			
			frames = 0;
		}
	}
	
	public void keyPressed(KeyEvent e) 
	{		
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_RIGHT) 
		{
			player.setVelX(5);
			
		}else if(key == KeyEvent.VK_LEFT) 
		{
			player.setVelX(-5);
			
		}else if(key == KeyEvent.VK_UP) 
		{
			player.setVelY(-5);
			
		}else if(key == KeyEvent.VK_DOWN) 
		{
			player.setVelY(5);
			
		}else if(key == KeyEvent.VK_ENTER && !player.getMuerte()) 
		{
			if(!disparando) 
				controller.addEntity(controller.obtenerEntidadesA(), new Bullet(player.getX() + player.anchoPlayer()/2 - 5, player.getY() - player.largoPlayer()/2 - 10));
			
			disparando = true;
			
		}
	}


	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_RIGHT) 
		{
			player.setVelX(0);
			
		}else if(key == KeyEvent.VK_LEFT) 
		{
			player.setVelX(0);
			
		}else if(key == KeyEvent.VK_UP) 
		{
			player.setVelY(0);
			
		}else if(key == KeyEvent.VK_DOWN) 
		{
			player.setVelY(0);
			
		}else if(key == KeyEvent.VK_ENTER) 
		{
			disparando = false;
		}
		
	}
	
}
