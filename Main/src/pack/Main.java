/**
 * 
 * Version Control
 * Created By: Aaryan Soni
 * Current Version: 1.3
 * ----------------------------------------------------
 * Version     Date          Updated By       Comments
 * 1.1 	   	12/12/20      	Aaryan Soni      Initial Version
 * 
 * 1.2      1/2/21        	Aaryan Soni 	  Added Normal vs Hard Mode select menu, added Shop state/menu
 * 
 * 1.3      1/4/21			Aaryan Soni 	  Added Shop Menu(Extend HEALTH, add SPEED, replenish HEALTH)
 * 
 * 1.4      3/20/21         Aaryan Soni      Moved to Replit.com and adjusted to work on it
 */

package pack;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Main extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 7941458321007931275L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9; 
	
	private Thread thread;
	private boolean running = false;
	
	public static boolean paused = false;
	public int diff = 0;
	
	// Difficulty
	//0 = normal, 1 = hard
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;
	
	public enum STATE {
		Menu,
		Select,
		Help,
		Shop,
		Main,
		End
	};
	
	public static STATE gameState = STATE.Menu;
	
	public Main() {
		handler = new Handler();
		hud = new HUD();
		shop = new Shop(handler, hud);
		menu = new Menu(this, handler, hud);
		
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		new Window(WIDTH, HEIGHT, "Wave Game", this);
		
		
		spawner =  new Spawn(handler, hud, this);
		r = new Random();
		
		if(gameState == STATE.Main) {
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.BasicEnemy, handler));
		}else {
			for(int i = 0; i < 20; i++) {
				handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
			}
		}
				
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		
		if(gameState == STATE.Main) {
			
			if(!paused) {
				handler.tick();
				hud.tick();
				spawner.tick();
				
				
				if(HUD.HEALTH <= 0) {
					HUD.HEALTH = 100;
					gameState = STATE.End; 
					handler.clearEnemies();
					for(int i = 0; i < 12; i++) {
						handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
					}
				}
			}
			
		}else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select) {
			handler.tick();
			menu.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		
		if(paused) {
			g.setColor(Color.white);
			g.drawString("PAUSED", 130, 100);
		}
		
		if(gameState == STATE.Main) {
			handler.render(g);
			hud.render(g);
		}else if(gameState == STATE.Shop) {
			shop.render(g);
		}else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select) {
			handler.render(g);
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		
		else 
			return var;
		
	}
	
	public static void main(String[] args) {
		new Main();
	}


}
