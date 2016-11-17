package game3.object;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;

import game3.framework.GameObject;
import game3.framework.ObjectId;
import game3.window.Handler;

public class ConcreteSlab extends GameObject{

	int tickcount1 = 0;
	int tickcount2 = 0;
	private Handler handler;
	boolean falling = true;
	boolean tSand = false;
	boolean tLand = false;
	
	public ConcreteSlab(double x, double y, ObjectId id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		collision(object);
		if(falling){
			y += 1;
		} 
		if(!falling){
			if(tLand){
				flashLand();
			}
			if(tSand){
				flashSand();
			}
		}
		
		
		
	}
	
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect((int)x, (int) y, 16, 16);
		
	}

	private void collision(LinkedList<GameObject> object){
		Iterator<GameObject> itr = object.iterator();
		GameObject temp;
		for(;itr.hasNext();){
			temp=itr.next();
			if(temp.getId()==ObjectId.landSurface){
				if(getBoundsFall().intersects(temp.getBounds())){
					falling = false;
					tLand = true;
				}
			}
			
			if(temp.getId()==ObjectId.sand){
				if(getBoundsFall().intersects(temp.getBounds())){
					falling = false;
					tSand = true;
				}
			}
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x-16, (int)y-16, 16,16);
	}
	
	public Rectangle getBoundsFall() {
		return new Rectangle((int)x-16, (int)y-16, 32,32);
	}
	
	public void flashLand(){
		if(tickcount1 % 20 == 0){
			y = 625;
		}
		if(tickcount1 % 40 == 0){
			y = 635;
		}
		tickcount1++;
	}
	
	public void flashSand(){
		if(tickcount2 % 20 == 0){
			y = 960;
		}
		if(tickcount2 % 40 == 0){
			y = 970;
		}
		tickcount2++;
	}
	
}
