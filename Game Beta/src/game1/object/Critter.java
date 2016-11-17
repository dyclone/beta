package game1.object;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import Game.BufferedImageLoader;
import game1.framework.GameObject;
import game1.framework.ObjectId;
import game1.window.Handler;

public class Critter extends GameObject {
	int landCo=1;
	int waterCo=2;
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
	private BufferedImage cImage;
	private BufferedImage oImage;
	private BufferedImage hImage;
	int scale = 2;
	/*
	 * Critter constructor
	 * 
	 */
	public Critter(double x, double y, ObjectId id,boolean xdir, boolean ydir,Handler handler) {
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
			if(temp.getId()==ObjectId.trash){
				Trash trash = (Trash)temp;
				if(!trash.checkDeath()){
					if(trash.canAttack){
						trash.health-=damage;
					}
					if(trash.health<=0){
						trash.dead();
					}
				}				
			}
			if(temp.getId()==ObjectId.recycle){
				Recycle recycle = (Recycle)temp;
				if(!recycle.checkDeath()){
					if(recycle.canAttack){
						recycle.health-=damage;
					}
					if(recycle.health<=0){
						recycle.dead();
					}
				}				
			}
			if(temp.getId()==ObjectId.compost){
				Compost compost = (Compost)temp;
				if(!compost.checkDeath()){
					if(compost.canAttack){
						compost.health-=damage;
					}
					if(compost.health<=0){
						compost.dead();
					}
				}	
			}
		}
	}
	
	/*
	 * collect item
	 */
	public void collect(){
		
	}
	
	public void planT(){
		handler.addObject(new Tree(x,y,ObjectId.tree));
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x+=velX;
		y+=velY;
		
		if(falling){
			velY+=gravity;
		}
		
		collision(object);

	}
	
	private void collision(LinkedList<GameObject> object){
		for(int i = 0; i < handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(temp.getId() == ObjectId.landSurface){
				if(getBoundsBottom().intersects(temp.getBounds())){
					setY(temp.getY()-32);
					falling=false;
					onLand=true;
					setVelY(0);
					
				}
				else{
					onLand=false;
				}
			}
			if(temp.getId()== ObjectId.seaLevel){
				if(getBoundsTop().intersects(temp.getBounds())){
					
					falling=false;
					
					
				}
				else{
					falling=true;
				}
			}
			if(temp.getId() == ObjectId.trash){
				Trash trash = (Trash)temp;
				if(getBounds().intersects(temp.getBounds())){
					trash.canAttack=true;
					//System.out.println("in range");
				}
				if(!getBounds().intersects(temp.getBounds())){
					trash.canAttack=false;
					//System.out.println("out of range");
				}
			}
			if(temp.getId() == ObjectId.recycle){
				Recycle recycle = (Recycle)temp;
				if(getBounds().intersects(temp.getBounds())){
					recycle.canAttack=true;
					//System.out.println("in range");
				}
				if(!getBounds().intersects(temp.getBounds())){
					recycle.canAttack=false;
					//System.out.println("out of range");
				}
			}
			if(temp.getId() == ObjectId.compost){
				Compost compost = (Compost)temp;
				if(getBounds().intersects(temp.getBounds())){
					compost.canAttack=true;
					//System.out.println("in range");
				}
				if(!getBounds().intersects(temp.getBounds())){
					compost.canAttack=false;
					//System.out.println("out of range");
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
