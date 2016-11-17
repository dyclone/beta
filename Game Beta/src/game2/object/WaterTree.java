package game2.object;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import Game.BufferedImageLoader;
import game2.framework.GameObject;
import game2.framework.ObjectId;
import game2.window.Handler;

public class WaterTree extends GameObject {
	Dimension dm;
	public boolean canAttack;
	public int hp;
	int type;
	Handler handler;
	private BufferedImage tree1;
	private BufferedImage tree2;
	public WaterTree(double x, double y, ObjectId id, Dimension dm, Handler handler) {
		super(x, y, id);
		this.dm=dm;
		hp=20;
		Random random =  new Random();
		
		type=random.nextInt(10)%2;
		this.handler=handler;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			tree1 = loader.loadImage("/sadmilkweed.png");
		}catch (IOException e){
			e.printStackTrace();
		}
		
		try{
			tree2 = loader.loadImage("/dunegrass.png");
		}catch (IOException e){
			e.printStackTrace();
		}
	}
//	public void change(){
//		type+=1;
//		type=type%2;
//	}

	public void dead(){
			
		canAttack=false;
		drop();
		
	}
	public void drop(){
		handler.addObject(new Seed(x,y-64,ObjectId.seed, type));
	}
	

	@Override
	public void tick(LinkedList<GameObject> object) {
		
		x+=velX;
		y+=velY;
//		if(hp<=0){
//			velY+=gravity*10;
//		}
	}

	@Override
	public void render(Graphics g) {
		switch(type){
		case 0:
			g.drawImage(tree1, (int)x, (int)y + 32 , 64, 64, null);
			break;
		case 1:
			g.drawImage(tree2, (int)x, (int)y + 32, 64, 64, null);
			
			break;
		}
			
		
		
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 64, 5);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 96);
	}

}
