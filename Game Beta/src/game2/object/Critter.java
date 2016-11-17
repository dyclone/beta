package game2.object;

import java.awt.Color;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import Game.BufferedImageLoader;
import game2.control.Game2;
import game2.framework.GameObject;
import game2.framework.ObjectId;
import game2.window.Handler;

public class Critter extends GameObject {
	int landCo=1;
	int waterCo=2;
	int scale = 2;
	
	Game2 game;
	public int seed1=0;
	public int seed2=0;
	boolean xdir;
	boolean ydir;
	int character;
	int health0;
	int health1;
	int health2;
	int damage;
	public boolean jump;
	private Handler handler;
	public boolean onLand;
	public boolean inWater;
	Dimension dm;
	private BufferedImage cImage;
	private BufferedImage oImage;
	private BufferedImage hImage;
	private BufferedImage seedImage;
	private BufferedImage leafImage;
	/*
	 * Critter constructor
	 * 
	 */
	public Critter(double x, double y, ObjectId id,boolean xdir, boolean ydir,Handler handler, Dimension dm, Game2 game2) {
		super(x, y, id);
		this.xdir=xdir;
		this.ydir=ydir;
		character=0;
		setDamage();
		health0=100;
		health1=100;
		health2=100;
		this.handler=handler;
		jump=false;
		inWater=false;
		this.dm = dm;
		this.game=game2;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			cImage = loader.loadImage("/bluecrab_0.png");
		}catch (IOException e){
			e.printStackTrace();
		}
		
		try{
			oImage = loader.loadImage("/clam_left_2.png");
		}catch (IOException e){
			e.printStackTrace();
		}
		
		try{
			hImage = loader.loadImage("/horseshoe_crab_left_1.png");
		}catch (IOException e){
			e.printStackTrace();
		}
		
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
	
	public void setDamage(){
		switch(character){
		case 0:
			damage=20;
			break;
		case 1:
			damage=10;
			break;
		case 2:
			damage=10;
			break;
		}
	}
	public void changeCharacter(){
		character+=1;
		character=character%3;
		//System.out.println(character);
		setDamage();
	}
	public int getDamage(){
		return this.damage;
	}
	/*
	 * set character depends on character token
	 */
	public void setCharacter(int character){
		this.character=character;
	}
	
	/*
	 * ability switch depends on character token
	 */
	private void ability(int character){
		switch(character){
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		}
	}
	
	/*
	 * attack enemy
	 */
	public void attack(LinkedList<GameObject> object){
		for(int i=0;i<object.size();i++){
			GameObject temp = object.get(i);
			

			if(temp.getId()==ObjectId.waterTree){
				WaterTree wt = (WaterTree)temp;
				if(wt.canAttack){
					wt.hp-=damage;
				}
				if(wt.hp<=0)
					wt.dead();
			}
		}
	}
	
	
	public void planT(int type){
		handler.addObject(new Tree(x,y,ObjectId.tree,type,game));
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x+=velX;
		y+=velY;
		if(x>dm.getWidth()*3/4-64){
			onLand=false;
			falling=true;
		}
		
		if(falling ||(falling && x>dm.getWidth()*3/4)){
			velY+=gravity;
		}
		
		if(y<dm.getHeight()*3/5-48 && x>dm.getWidth()*3/4-32){
			setY(dm.getHeight()*3/5-48);
			setVelY(0);
			jump=true;
		}
		
		collision(object);

	}
	
	private void collision(LinkedList<GameObject> object){
		for(int i = 0; i < handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId() == ObjectId.landSurface){
				if(getBoundsBottom().intersects(temp.getBounds())){
					setY(temp.getY()-31);
					falling=false;
					setVelY(0);
					jump=false;
					onLand=true;
					
				}
			}
			if(temp.getId()== ObjectId.seaLevel){
				if(getBoundsSelf().intersects(temp.getBounds())){
					
					
					jump = false;
					
					
				}
				
				
			}
			if(temp.getId() == ObjectId.Sand){
				if(getBoundsBottom().intersects(temp.getBounds())){
					setY(temp.getY()-31);
					setVelY(0);
					jump=false;
				}
			}
			
			if(temp.getId() == ObjectId.waterTree){
				WaterTree tree = (WaterTree)temp;
				if(getBounds().intersects(temp.getBounds())){
					tree.canAttack=true;
					//System.out.println("in range");
				}
				if(!getBounds().intersects(temp.getBounds())){
					tree.canAttack=false;
					//System.out.println("out of range");
				}
			}
			if(temp.getId()==ObjectId.wall){
				if(getBoundsLeft().intersects(temp.getBounds())){
					setX(dm.getWidth()*3/4);
					setVelX(0);
				}
			}
			if(temp.getId()==ObjectId.seed){
				Seed seed= (Seed)temp;
				if(getBoundsSelf().intersects(temp.getBounds())){
					switch(seed.type){
					case 0:
						seed1+=1;
						break;
					case 1:
						seed2+=1;
						break;
					}
					object.remove(temp);
				}
			}
		}
	}
	
	

	@Override
	public void render(Graphics g) {
		switch(character){
		case 0:
			g.drawImage(cImage, (int)x, (int)y, 32, 32, null);
			break;
		case 1:
			g.drawImage(oImage, (int)x, (int)y, 32, 32, null);
			break;
		case 2:
			g.drawImage(hImage, (int)x, (int)y, 32, 32, null);
			break;
		}
		
		g.drawImage(seedImage, 40, 50, 16, 16, null);
		g.setColor(Color.BLACK);
		if (seed2 >= 1) {
			g.setColor(Color.green);
		}
		g.drawString(" x " + seed2, 60, 60);

		g.drawImage(leafImage, 40, 70, 16, 16, null);

		g.setColor(Color.BLACK);
		if (seed1 >= 1) {
			g.setColor(Color.MAGENTA);
		}
		g.drawString(" x " + seed1, 60, 80);
		
		// Crab health
		g.setColor(Color.BLACK);
		if(health0 <= 0){
			g.setColor(Color.RED);
		}
		g.drawString("Crab", 1650, 40);
		g.setColor(Color.green);
		g.drawRect(1650, 50, 100 * scale, 10);
		g.setColor(Color.RED);
		g.fillRect(1651, 52, (int) health0 * scale, 7);

		// Oyster Health
		g.setColor(Color.BLACK);
		if(health1 <= 0){
			g.setColor(Color.RED);
		}
		g.drawString("Oyster", 1650, 80);
		g.setColor(Color.green);
		g.drawRect(1650, 90, 100 * scale, 10);
		g.setColor(Color.RED);
		g.fillRect(1651, 92, (int) health1 * scale, 7);

		// Horseshoe crab health
		g.setColor(Color.BLACK);
		if(health2 <= 0){
			g.setColor(Color.RED);
		}
		g.drawString("Horseshoe Crab", 1650, 120);
		g.setColor(Color.green);
		g.drawRect(1650, 130, 100 * scale, 10);
		g.setColor(Color.RED);
		g.fillRect(1651, 132, (int) health2 * scale, 7);

		
		Graphics2D g2d = (Graphics2D)g;
		/*
		g.setColor(Color.green);
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsBottom());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());
		*/
		g.setColor(Color.gray);
		g2d.draw(getBounds());
		
	}

	@Override
	
	public Rectangle getBounds() {
		
		return new Rectangle((int)x-16,(int)y-16, 64,64);
	}
	
	public Rectangle getBoundsSelf(){
		return new Rectangle((int)x, (int)y, 32,32);
	}
	
	public Rectangle getBoundsTop() {
		
		return new Rectangle((int)x+6,(int)y, 20,6);
	}
	public Rectangle getBoundsBottom() {
		
		return new Rectangle((int)x+6,(int)y+26, 20,6);
	}
	
	public Rectangle getBoundsLeft() {
		
		return new Rectangle((int)x,(int)y+6, 6,20);
	}
	public Rectangle getBoundsRight() {
		
		return new Rectangle((int)x+26,(int)y+6, 6,20);
	}

}
