package game2.framework;

import java.awt.event.KeyAdapter;



import java.awt.event.KeyEvent;

import RunGame.RunGame;
import game2.object.Critter;
import game2.window.Handler;
import game3.control.Game3;

import Game.GameOver;

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
				Critter t =(Critter)temp;
				if(key == KeyEvent.VK_A){
					t.setVelX(-2);
				}
				if(key == KeyEvent.VK_D){
					t.setVelX(2);
				}
				if(key == KeyEvent.VK_W && !t.jump){
					t.setVelY(-2);
					t.falling= true;
					if(!t.inWater){
						t.jump=true;
					}
					t.onLand=false;
					
				}
				if(key == KeyEvent.VK_S && !((Critter)temp).onLand){
					temp.setVelY(2);
				}
				
			}
			
		}
		if(key == KeyEvent.VK_ESCAPE){
			System.exit(1);
		}
		if(key == KeyEvent.VK_X){
			RunGame.window.changeWindow(RunGame.dm, "Estuary", new Game3());
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
				if(key==KeyEvent.VK_P && t.onLand){
					if(t.seed1>0){
						t.seed1-=1;
						t.planT(0);
					}
					
				}
				if(key==KeyEvent.VK_O && t.onLand){
					if(t.seed2>0){
						t.seed2-=1;
						t.planT(1);
					}
				}
				
			}
			
		}

	}
}
