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

public class Bird extends GameObject {
	int damage = 1;
	private double health = 20;
	boolean canAttack = false;
	int fallvalue = 1;
	private Handler handler;
	boolean isDead = false;
	int speed = 1;
	int grav = 2;
	boolean colliding = false;
	int direction;
	Random rand = new Random();
	private BufferedImage birdImg;

	public Bird(double x, double y, ObjectId id, Handler handler, int direction) {
		super(x, y, id);

		this.handler = handler;
		velX = speed;
		velY = grav;
		this.direction = direction;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			birdImg = loader.loadImage("/redknot_0.png");
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
		y += velY;

		if (colliding) {
			if (direction == 0) {
				x += velX;
			} 
			if (direction == 1){
				x -= velX;
			}
			
		}

		if (x <= 0 || x >= 1400 - 32) {
			velX *= -1;
		}


		if (!isDead)
			collision(object);

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(birdImg, (int)x, (int)y, 32, 32, null);
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, (int) ((getHealth() / 20) * 32), 2);
	}

	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 32, 32);
	}

	private void collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			if (temp.getId() == ObjectId.landSurface) {
				if (getBoundsBottom().intersects(temp.getBounds())) {
					setY(temp.getY() - 32);
					setVelY(0);
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

	public void setHealth(double health) {
		this.health = health;
	}

}
