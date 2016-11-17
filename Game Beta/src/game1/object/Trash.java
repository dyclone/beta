package game1.object;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import Game.BufferedImageLoader;
import game1.framework.GameObject;
import game1.framework.ObjectId;
import game1.window.Handler;

public class Trash extends GameObject {
	int damage = 5;
	double health = 20;
	boolean canAttack = false;
	private Handler handler;
	boolean isDead = false;
	double gravityFactor = 0;
	private BufferedImage trashImage;
	TrashBin trashBin;

	public Trash(double x, double y, ObjectId id, TrashBin trashBin, Handler handler) {
		super(x, y, id);
		this.trashBin = trashBin;
		this.handler = handler;
		setVelY(1);
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			trashImage = loader.loadImage("/banana.png");
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
		if (health <= 0) {
			//setVelX(((trashBin.getX() - x) / (trashBin.getY()-y))*8);
			setVelX((trashBin.getX() - x) / 50);
			setVelY(-5);
			gravityFactor = 10-((trashBin.getX() - x)/20);
			canAttack = false;
			isDead = true;
		}
	}

	public boolean checkDeath() {
		return isDead;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		if (!isDead)
			collision(object);
		else
			velY += gravity * gravityFactor;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(trashImage, (int)x, (int)y, 32, 32, null);
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
			if (temp.getId() == ObjectId.landSurface) {
				if (getBoundsBottom().intersects(temp.getBounds())) {
					setY(temp.getY() - 32);
					setVelY(0);
				}
			}
		}
	}

	public Rectangle getBoundsBottom() {

		return new Rectangle((int) x + 6, (int) y + 26, 20, 6);
	}

}
