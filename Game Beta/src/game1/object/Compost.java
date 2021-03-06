package game1.object;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import game1.framework.GameObject;
import game1.framework.ObjectId;
import game1.window.Handler;

public class Compost extends GameObject {
	int damage = 5;
	double health = 20;
	boolean canAttack = false;
	int fallvalue = 1;
	private Handler handler;
	boolean isDead = false;
	CompostCounter counter;

	public Compost(double x, double y, ObjectId id, Handler handler, CompostCounter counter) {
		super(x, y, id);
		this.handler = handler;
		this.counter = counter;
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
			canAttack = false;
			isDead = true;
			counter.setCount();
		}

	}

	public boolean checkDeath() {
		return isDead;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {

		x += velX;
		y += velY;
		if (health <= 0) {
			velY += gravity * 10;
		}

		y += fallvalue;

		if (!isDead)
			collision(object);

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect((int) x, (int) y, 32, 32);
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
					fallvalue = 0;
				}
			}
		}
	}

	public Rectangle getBoundsBottom() {

		return new Rectangle((int) x + 6, (int) y + 26, 20, 6);
	}

}
