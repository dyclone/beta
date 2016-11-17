package game3.framework;
import Game.*;

import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;

import RunGame.RunGame;
import game2.control.Game2;
import game3.control.Game3;
import game3.object.Critter;
import game3.window.Handler;
import window.Window;

public class KeyInput extends KeyAdapter {
	Handler handler;
	
	
	public KeyInput(Handler handler){
		this.handler=handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		for(int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			
			if(temp.getId() == ObjectId.critter){
				if(key == KeyEvent.VK_A){
					temp.setVelX(-3);
				}
				if(key == KeyEvent.VK_D){
					temp.setVelX(3);
				}
				if(key == KeyEvent.VK_W){
					temp.setVelY(-3);
				}
				if(key == KeyEvent.VK_S){
					temp.setVelY(3);
				}
				
			}
			
		}
		if(key == KeyEvent.VK_ESCAPE){
			System.exit(1);
		}
		if(key == KeyEvent.VK_X){
			RunGame.window.changeWindow(RunGame.dm, "Estuary", new GameWin());
		}
		if(key == KeyEvent.VK_Z){
			RunGame.window.changeWindow(RunGame.dm, "Estuary", new GameOver());
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i=0;i<handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			
			if(temp.getId() == ObjectId.critter){
				Critter t = (Critter)temp;
				if(key == KeyEvent.VK_A){
					temp.setVelX(0);
				}
				if(key == KeyEvent.VK_D){
					temp.setVelX(0);
				}
				if(key == KeyEvent.VK_W){
					temp.setVelY(0);
				}
				if(key == KeyEvent.VK_S){
					temp.setVelY(0);
				}
				if(key == KeyEvent.VK_SPACE){
					
					t.attack(handler.object);
					
				}
				if(key== KeyEvent.VK_I){
					t.changeCharacter();
				}
				if(key==KeyEvent.VK_L){
					t.planT();
				}
				if(key==KeyEvent.VK_K){
					t.constructG();
				}
				
			}
			
		}

	}
}
