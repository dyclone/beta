package game3.object;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;

import Game.BufferedImageLoader;

import java.awt.color.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game3.framework.GameObject;
import game3.framework.ObjectId;
import game3.window.Handler;

public class Oyster extends GameObject {

	int tickcount1 = 0;
	int tickcount2 = 0;
	private Handler handler;
	boolean falling = true;
	boolean tSand = false;
	boolean tLand = false;
	private BufferedImage oysImg;

	public Oyster(double x, double y, ObjectId id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		

		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			oysImg = loader.loadImage("/clam_left_0.png");
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		collision(object);
		if (falling) {
			y += 1;
		}
		if (!falling) {
			if (tLand) {
				flashLand();
			}
			if (tSand) {
				flashSand();
			}
		}

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(oysImg, (int)x, (int)y, 16, 16, null);

	}

	private void collision(LinkedList<GameObject> object) {
		Iterator<GameObject> itr = object.iterator();
		GameObject temp;
		for (; itr.hasNext();) {
			temp = itr.next();
			if (temp.getId() == ObjectId.landSurface) {
				if (getBoundsFall().intersects(temp.getBounds())) {
					falling = false;
					tLand = true;
				}
			}

			if (temp.getId() == ObjectId.sand) {
				if (getBoundsFall().intersects(temp.getBounds())) {
					falling = false;
					tSand = true;
				}
			}
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x - 16, (int) y - 16, 16, 16);
	}

	public Rectangle getBoundsFall() {
		return new Rectangle((int) x - 16, (int) y - 16, 32, 32);
	}

	public void flashLand() {
		if (tickcount1 % 20 == 0) {
			y = 625;
		}
		if (tickcount1 % 40 == 0) {
			y = 635;
		}
		tickcount1++;
	}

	public void flashSand() {
		if (tickcount2 % 20 == 0) {
			y = 960;
		}
		if (tickcount2 % 40 == 0) {
			y = 970;
		}
		tickcount2++;
	}

}
