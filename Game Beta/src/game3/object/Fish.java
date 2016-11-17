package game3.object;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import Game.BufferedImageLoader;
import game3.framework.GameObject;
import game3.framework.ObjectId;
import game3.window.Handler;

public class Fish extends GameObject {
	int damage = 5;
	double health = 20;
	boolean canAttack = false;
	private Handler handler;
	boolean isDead = false;
	int speed = 2;
	boolean colliding = false;
	Random rand = new Random();
	private BufferedImage fishImg;
	
	public Fish(double x, double y, ObjectId id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		velX = speed;
		velY = speed;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			fishImg = loader.loadImage("/fish_pickerel_left.png");
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	/*
	 * get damage
	 */
	public int getDamage() {
		return this.damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void dead() {
			canAttack = false;
			isDead = true;
			
			int drop = rand.nextInt(2);
			if(drop == 1){
				handler.addObject(new Oyster(x, y, ObjectId.oysterI, handler));
			}
			if(drop == 0){
				handler.addObject(new ConcreteSlab(x, y, ObjectId.cSlab, handler));
			}
	}


	@Override
	public void tick(LinkedList<GameObject> object) {

	
		x -= velX;
	
		y -= velY;
		
		if(x <= 1450 || x >= 1900 - 32){
			velX *= -1;
		}
		
		if(y <= 670 || y >= 950 - 32){
			velY *= -1;
		}
		
		
		if(!isDead)
			collision(object);

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(fishImg, (int)x, (int)y, 32, 32, null);
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, (int) ((health / 20) * 32), 2);
	}

	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 32, 32);
	}

	private void collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			if (temp.getId() == ObjectId.fishSpawn) {
				if (getBoundsBottom().intersects(temp.getBounds())) {
					setY(temp.getY() - 32);

					colliding = true;
				}
			}
		}
	}

	public Rectangle getBoundsBottom() {

		return new Rectangle((int) x + 6, (int) y + 26, 20, 6);
	}

	public double getHealth() {
		return health;
	}

}
