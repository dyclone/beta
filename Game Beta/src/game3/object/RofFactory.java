package game3.object;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import game3.framework.GameObject;
import game3.framework.ObjectId;
import game3.window.Handler;

public class RofFactory extends GameObject {

	public RofFactory(double x, double y, ObjectId id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int)x, (int)y-32, 32, 64);
		g.setColor(Color.ORANGE);
		g.drawRect((int)x, (int)y-32, 32, 64);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void prodRof(Handler handler,Dimension dm){
		//handler.addObject(new Runoff(x, y-32,ObjectId.runOff));
	}

}
