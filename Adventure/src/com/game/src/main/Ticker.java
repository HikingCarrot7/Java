package com.game.src.main;

public class Ticker implements Runnable
{
	
	private Game game;
	
	public Ticker(Game game) 
	{
		this.game = game;
		
		new Thread(this).start();
	}
	
	@Override
	public void run() 
	{
		game.init();
		
		long lastTime = System.nanoTime();
		double ns = 1000000000 / 60.0;
		double delta = 0; 
		
		int update = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(true) 
		{
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			
			if(delta >= 1) 
			{
				game.tick();
				update++;
				delta--;
			}
			
			game.render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				//System.out.println("TICKS: " + update + " FPS: " + frames);
				timer += 1000;
				update = 0;
				frames = 0;
			}
			
		}
		
	}

}