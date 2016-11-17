package game2.object;

import java.awt.Color;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import Game.BufferedImageLoader;
import Game.Game;
import game2.control.Game2;
import game2.framework.GameObject;
import game2.framework.ObjectId;
import game2.window.Handler;

public class Runoff extends GameObject {
	Handler handler;
	public int type;
	Dimension dm;
	boolean active;
	Game2 game;
	private BufferedImage runOff1;
	private BufferedImage runOff2;
	public Runoff(double x, double y,Dimension dm, Handler handler, ObjectId id, int type,Game2 game2) {
		super(x, y, id);
		this.handler=handler;
		setVelX(1.2);
		this.type=type;
		this.dm=dm;
		active=true;
		this.game=game2;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			runOff1 = loader.loadImage("/algae_bad.png");
		}catch (IOException e){
			e.printStackTrace();
		}
		
		try{
			runOff2 = loader.loadImage("/algae_medium.png");
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		if(falling)
			velY+=gravity;
		x+=velX;
		y+=velY;
		if(x>dm.getWidth()+64){
			x=-32;
			active=true;
		}
		collision(handler.object);
	}

	@Override
	public void render(Graphics g) {
		switch(type){
		case 0:
			g.drawImage(runOff1, (int)x, (int)y, 64, 64, null);
			break;
		case 1:
			g.drawImage(runOff2, (int)x, (int)y, 64, 64, null);
			break;
		}
		
		//g.fillRect((int)x, (int)y, 32, 32);

	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,32,32);
	}
	
	private void collision(LinkedList<GameObject> object){
		for(int i = 0; i < handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId() == ObjectId.landSurface){
				if(getBounds().intersects(temp.getBounds())){
					falling=false;
					velY=0;
					setY(temp.getY()-32);
				}
				else{
					falling=true;
				}
			}
			if(temp.getId() == ObjectId.Sand){
				if(getBounds().intersects(temp.getBounds())){
					falling=false;
					velY=0;
					setY(temp.getY()-32);
				}
				else{
					falling=true;
				}
			}
			if(temp.getId() == ObjectId.seaLevel){
				
				if(getBounds().intersects(temp.getBounds())){
					
					if(active){
						game.count+=1;
						active=false;
						System.out.println(game.count);
					}
				}
			}
			
		}	
	}

}
