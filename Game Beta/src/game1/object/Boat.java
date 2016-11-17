package game1.object;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import Game.BufferedImageLoader;
import game1.framework.GameObject;
import game1.framework.ObjectId;
import game1.window.Handler;

public class Boat extends GameObject{
	
	private Handler handler;
	private int direction = 1;
	private double initialY = 0;
	private TrashBin trashBin;
	private RecycleBin recyclebin;
	private CompostCounter counter;
	private double boundary1;
	private double boundary2;
	private BufferedImage boatImage;
	
	//Clock
	int timer1 = 0;
	int countdown1 = 0;
	int amount = 0;
	Random rand = new Random();

	public Boat(double x, double y, ObjectId id, Handler handler, TrashBin trashBin, RecycleBin recyclebin, CompostCounter counter, double boundary1, double boundary2) {
		super(x, y, id);
		this.handler=handler;
		initialY = y;
		this.trashBin = trashBin;
		this.recyclebin = recyclebin;
		this.counter = counter;
		this.boundary1=boundary1;
		this.boundary2=boundary2;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			boatImage = loader.loadImage("/hotrod_vessel.png");
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		if(timer1==countdown1){
			countdown1 = rand.nextInt(200) + 1;
			spawnTrash();
			spawnRecycle();
			spawnCompost();
			timer1 = 0;
		}
		timer1 ++;
		//System.out.println(timer1 + ", "+ countdown1);
		x += direction;
		y += .25 * Math.sin(x/25);
		collide();
	}
	
	public void collide(){
		if(x+128 >= boundary2 || x <= boundary1){
			direction *= -1;
			y=initialY;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(boatImage, (int)x, (int)y, 128, 42, null);
	}

	@Override
	public Rectangle getBounds() {

		return null;
	}
	
	public void spawnTrash(){
		amount = rand.nextInt(2);
		for(int i = 0; i < amount; i++){
			handler.addObject(new Trash(rand.nextInt((int)x+10)+x, rand.nextInt((int)y+5)+y, ObjectId.trash,trashBin,handler));
		}
	}
	
	public void spawnRecycle(){
		amount = rand.nextInt(2);
		for(int i = 0; i < amount; i++){
			handler.addObject(new Recycle(rand.nextInt((int)x+10)+x, rand.nextInt((int)y+5)+y, ObjectId.recycle,recyclebin,handler));
		}
	}
	
	public void spawnCompost(){
		amount = rand.nextInt(10);
		if(amount%2==0)
			handler.addObject(new Compost(rand.nextInt((int)x+10)+x, rand.nextInt((int)y+5)+y, ObjectId.compost, handler, counter));
	}

}
