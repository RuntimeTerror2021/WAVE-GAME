package pack;

import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private Main game;
	private Random r = new Random();
	
	private int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud, Main game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}
	
	public void tick() {
		scoreKeep++;
		
		if(scoreKeep >= 250) {
			scoreKeep = 0;
			
			hud.setLevel(hud.getLevel() + 1);
			
			if(game.diff == 0) {
				if (hud.getLevel() == 2) {
					handler.addObject(new BasicEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.BasicEnemy, handler));
				}else if (hud.getLevel() == 3) {
					handler.addObject(new BasicEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.BasicEnemy, handler));
				}else if (hud.getLevel() == 4) {
					handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
				}else if (hud.getLevel() == 5) {
					handler.addObject(new SmartEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.SmartEnemy, handler));
				}else if (hud.getLevel() == 6) {
					handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
				}else if (hud.getLevel() == 7) {
					handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
				}else if (hud.getLevel() == 10) {
					handler.clearEnemies();
					handler.addObject(new EnemyBoss((Main.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler));
				}
			}else if(game.diff == 1) {
				if (hud.getLevel() == 2) {
					handler.addObject(new HardEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.BasicEnemy, handler));
				}else if (hud.getLevel() == 3) {
					handler.addObject(new HardEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.BasicEnemy, handler));
				}else if (hud.getLevel() == 4) {
					handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
				}else if (hud.getLevel() == 5) {
					handler.addObject(new SmartEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.SmartEnemy, handler));
				}else if (hud.getLevel() == 6) {
					handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
				}else if (hud.getLevel() == 7) {
					handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
				}else if (hud.getLevel() == 10) {
					handler.clearEnemies();
					handler.addObject(new EnemyBoss((Main.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler));
				}
			}
			
			
		}
		
	}
	
}
