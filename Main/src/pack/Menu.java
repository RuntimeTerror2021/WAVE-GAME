package pack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import pack.Main.STATE;

public class Menu extends MouseAdapter{
	
	private Main game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Main game, Handler handler, HUD hud) {
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if(game.gameState == STATE.Menu) {
			//play button
			if(mouseOver(mx, my, 210, 150, 210, 64)) {
				game.gameState = STATE.Select;
				return;
				
			}
			
			
			//help button
			if(mouseOver(mx, my, 210, 250, 210, 64)) {
				game.gameState = STATE.Help;
			}
			
			//quit button
			if(mouseOver(mx, my, 210, 350, 210, 64)) {
				System.exit(0);
			}
			
		}
		
		if(game.gameState == STATE.Select) {
			//normal button
			if(mouseOver(mx, my, 210, 150, 210, 64)) {
				game.gameState = STATE.Main;
				handler.addObject(new Player(Main.WIDTH/2-32, Main.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.BasicEnemy, handler));
				
				game.diff = 0;
				
			}
			
			
			//hard button
			if(mouseOver(mx, my, 210, 250, 210, 64)) {
				game.gameState = STATE.Main;
				handler.addObject(new Player(Main.WIDTH/2-32, Main.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemies();
				handler.addObject(new HardEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.BasicEnemy, handler));
				
				game.diff = 1;
			}
			
			//back button
			if(mouseOver(mx, my, 210, 350, 210, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
			
		}
		
		//back button for help state
		if(game.gameState == STATE.Help) {
			if(mouseOver(mx, my, 210, 350, 210, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
		
		//game end button
		if(game.gameState == STATE.End) {
			if(mouseOver(mx, my, 210, 350, 210, 64)) {
				game.gameState = STATE.Menu;
				hud.setLevel(1);
				hud.setScore(0);
			}
		}
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Wave", 235, 50);
			
			g.setFont(fnt2);
			g.drawRect(210, 150, 210, 64);
			g.drawString("Play", 275, 190);
			
			g.drawRect(210, 250, 210, 64);
			g.drawString("Help", 275, 290);
			
			g.drawRect(210, 350, 210, 64);
			g.drawString("Quit", 275, 390);
		}else if(game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 235, 50);
			
			g.setFont(fnt3);
			g.drawString("Use WASD to move player, p to pause", 55, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 210, 64);
			g.drawString("Back", 275, 390);
		}else if(game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game over.", 158, 50);
			
			g.setFont(fnt3);
			g.drawString("You lost with a score of: " + hud.getScore(), 145, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 210, 64);
			g.drawString("Try Again", 238, 390);
		}else if(game.gameState == STATE.Select) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SELECT DIFFICULTY", 60, 40);
			
			g.setFont(fnt2);
			g.drawRect(210, 150, 210, 64);
			g.drawString("Normal", 255, 190);
			
			g.drawRect(210, 250, 210, 64);
			g.drawString("Hard", 275, 290);
			
			g.drawRect(210, 350, 210, 64);
			g.drawString("Back", 275, 390);
		}
	}
		
}
