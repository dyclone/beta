package game2.object;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import Game.BufferedImageLoader;
import game2.framework.GameObject;
import game2.framework.ObjectId;

public class Seed extends GameObject {
	int type;
	private BufferedImage seedImage;
	private BufferedImage leafImage;
	boolean falling = true;
	boolean tSand = false;
	int tickcount2;
	public Seed(double x, double y, ObjectId id, int type) {
		super(x, y, id);
		this.type=type;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			seedImage = loader.loadImage("/seed.png");
		}catch (IOException e){
			e.printStackTrace();
		}
		
		try{
			leafImage = loader.loadImage("/leaf.png");
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
			if (tSand) {
				flashSand();
			}
		}

	}

	@Override
	public void render(Graphics g) {
		switch(type){
		case 0:
			g.drawImage(leafImage, (int)x, (int)y, 32, 32, null);
			break;
		case 1:
			g.drawImage(seedImage, (int)x, (int)y, 32, 32, null);
			break;
		}
		//g.fillOval((int)x, (int)y, 32, 32);
		

	}

	private void collision(LinkedList<GameObject> object) {
		Iterator<GameObject> itr = object.iterator();
		GameObject temp;
		for (; itr.hasNext();) {
			temp = itr.next();

			if (temp.getId() == ObjectId.Sand) {
				if (getBoundsFall().intersects(temp.getBounds())) {
					falling = false;
					tSand = true;
				}
			}
		}
	}

	public Rectangle getBoundsFall() {
		return new Rectangle((int) x - 16, (int) y - 16, 32, 32);
	}
	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 32,32);
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
