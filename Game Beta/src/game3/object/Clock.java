package game3.object;

import java.awt.Graphics;

import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;
import game3.window.Handler;

import game3.framework.GameObject;
import game3.framework.ObjectId;

public class Clock extends GameObject{
	
	private Handler handler;
	int timer1 = 0;
	int countdown1 = 0;
	int amount = 0;
	int wSummon = 0;
	Random rand = new Random();


	public Clock(double x, double y, ObjectId id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		if(timer1==countdown1){
			countdown1 = rand.nextInt(400) + 1;
			spawnTrash();
			spawnRecycle();
			timer1 = 0;
		}
		if(wSummon == 1200){
			summonWave();
		}
		timer1 ++;
		wSummon++;
		System.out.println(timer1 + ", "+ countdown1);
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void spawnTrash(){
		amount = rand.nextInt(3);
		for(int i = 0; i < amount; i++){
			handler.addObject(new Bird(rand.nextInt(1300), rand.nextInt(200), ObjectId.trash,handler, rand.nextInt(2)));
		}
	}
	
	
	public void spawnRecycle(){
		amount = rand.nextInt(3);
		for(int i = 0; i < amount; i++){
			handler.addObject(new Fish(1850, 800, ObjectId.recycle, handler));
		}
	}
	
	public void summonWave(){
		handler.addObject(new Runoff (1900, 620, ObjectId.runOff));
		handler.addObject(new Runoff (2000, 620, ObjectId.runOff));
	}

}
