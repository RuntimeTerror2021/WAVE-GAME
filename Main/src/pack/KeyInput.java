package pack;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import pack.Main.STATE;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	Main game;
	
	public KeyInput(Handler handler, Main game){
		this.handler = handler;
		this.game = game;
		
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				
				if(key == KeyEvent.VK_W) { tempObject.setVelY(-handler.spd); keyDown[0]=true;}
				if(key == KeyEvent.VK_S) { tempObject.setVelY(handler.spd); keyDown[1]=true;}
				if(key == KeyEvent.VK_D) { tempObject.setVelX(handler.spd); keyDown[2]=true;}
				if(key == KeyEvent.VK_A) { tempObject.setVelX(-handler.spd); keyDown[3]=true;} 
				
			}
		}
		
		if (key == KeyEvent.VK_P) {
			if(game.gameState == Main.STATE.Main) {
				if(Main.paused) Main.paused = false;
				else Main.paused = true;
			}
		}
		if(key == KeyEvent.VK_ESCAPE) System.exit(0);
		if(key == KeyEvent.VK_SPACE) {
			if(Main.gameState == STATE.Main) Main.gameState = STATE.Shop;
			else if(Main.gameState == STATE.Shop) Main.gameState = STATE.Main;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				
				if(key == KeyEvent.VK_W) keyDown[0]=false;
				if(key == KeyEvent.VK_S) keyDown[1]=false;
				if(key == KeyEvent.VK_D) keyDown[2]=false;
				if(key == KeyEvent.VK_A) keyDown[3]=false;
				
				//vertical movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				//horizontal movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
			}
		}
		
	}
	
}
