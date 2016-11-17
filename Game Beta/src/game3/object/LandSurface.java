package game3.object;


import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import game3.framework.GameObject;
import game3.framework.ObjectId;

public class LandSurface extends GameObject {

	public LandSurface(double x, double y, ObjectId id) {
		super(x, y, id);
		
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
		
	}

	@Override
	public void render(Graphics g) {
		if(id==ObjectId.landSurface){
			g.setColor(Color.ORANGE);
			g.fillRect((int)x, (int)y, 32, 32);
		}
		if(id==ObjectId.seaLevel){
			g.setColor(Color.CYAN);
			g.fillRect((int)x, (int)y, 32, 32);
		}
		if(id==ObjectId.wall){
			g.setColor(Color.gray);
			g.fillRect((int)x, (int)y, 32, 32);
		}
		if(id==ObjectId.sand){
			g.setColor(Color.yellow);
			g.fillRect((int)x, (int)y, 32, 32);
		}
		
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 32,32);
	}

}
