package game2.object;

import java.awt.Color;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import Game.BufferedImageLoader;
import game2.control.Game2;
import game2.framework.GameObject;
import game2.framework.ObjectId;

public class Tree extends GameObject {
	public int hp;
	public int type;
	Game2 game;
	private BufferedImage tree1Img;
	private BufferedImage tree2Img;
	
	public Tree(double x, double y, ObjectId id, int type, Game2 game2) {
		super(x, y, id);
		hp=3;
		this.type=type;
		this.game=game2;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			tree1Img = loader.loadImage("/phragmite_spawning3.png");
		}catch (IOException e){
			e.printStackTrace();
		}
		
		try{
			tree2Img = loader.loadImage("/cordgrass.png");
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
		collision(object);
	}
	
	private void collision(LinkedList<GameObject> object){
		Iterator<GameObject> itr = object.iterator();
		GameObject temp;
		for(;itr.hasNext();){
			temp=itr.next();
			if(temp.getId()==ObjectId.runOff){
				Runoff rof=(Runoff)temp;
				if(getBounds().intersects(temp.getBounds())){
					if(this.type==rof.type){
						game.nRof-=1;
						hp-=1;
						itr.remove();
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		switch(type){
		case 0:
			g.drawImage(tree1Img, (int)x, (int)y - 96, 64, 128, null);
			break;
		case 1:
			g.drawImage(tree2Img, (int)x, (int)y - 96, 64, 128, null);
			break;
		}
		
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y-96, (int)(hp*64/3), 2);
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y-96,32,128);
	}

}
