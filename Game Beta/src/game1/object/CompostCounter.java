package game1.object;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import game1.framework.GameObject;
import game1.framework.ObjectId;

public class CompostCounter extends GameObject{
	
	private int count=0;

	public CompostCounter(double x, double y, ObjectId id) {
		super(x, y, id);
		
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
		
	}
	
	public void setCount(){
		count++;
	}

	@Override
	public void render(Graphics g) {
		String output = "x" + count;
		g.setColor(Color.white);
		g.drawString(output, (int)x+15, (int)y+10);
		g.setColor(Color.orange);
		g.fillRect((int)x, (int)y, 10, 10);
	}

	@Override
	public Rectangle getBounds() {
		
		return null;
	}

}
