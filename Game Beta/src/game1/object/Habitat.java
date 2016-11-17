package game1.object;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import Game.BufferedImageLoader;
import game1.framework.GameObject;
import game1.framework.ObjectId;
import game1.window.Handler;

public class Habitat extends GameObject{
	
	private int count = 200;
	private int timer = 0;
	private double health = 20;
	private final int TRASHDAMAGE = 1;
	private Handler handler;
	private int numberOfHazards = 0;
	private double width;
	private BufferedImage habitImg;

	public Habitat(double x, double y, ObjectId id, Handler handler, Dimension dm) {
		super(x, y, id);
		this.handler = handler;
		width = dm.getWidth()-dm.getWidth()*3/4;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			habitImg = loader.loadImage("/2D_estuary.jpg");
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		if(health>0){
			if(timer==count){
				timer=0;
				collision(object);
				health -= numberOfHazards * TRASHDAMAGE;
			}
			timer++;	
		}
		//System.out.println(timer + ", " + count + ", "+ health);
	}

	@Override
	public void render(Graphics g) {
		if(health<=0)
			g.setColor(Color.RED);
		else
			g.setColor(Color.GRAY);
		g.fillRect((int) x, (int) y, (int)width, 64);
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, (int) ((health / 20) * width), 2);
	}
	
	private void collision(LinkedList<GameObject> object) {
		numberOfHazards = 0;
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			if (temp.getId() == ObjectId.trash) {
				if (getBounds().intersects(temp.getBounds())) {
					numberOfHazards += 1;
				}
			}
			if (temp.getId() == ObjectId.recycle) {
				if (getBounds().intersects(temp.getBounds())) {
					numberOfHazards += 1;
				}
			}
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x + 6, (int) y + 26, (int)width, 64);
	}

}
