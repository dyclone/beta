package game3.object;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import Game.BufferedImageLoader;
import game3.framework.GameObject;
import game3.framework.ObjectId;

public class Runoff extends GameObject {
	private BufferedImage waveImg;
	
	public Runoff(double x, double y, ObjectId id) {
		super(x, y, id);
		setVelX(3);
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			waveImg = loader.loadImage("/watersplash_sideblast_1.png");
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x-=velX;
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, 32, 32);

	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,32,32);
	}

}
