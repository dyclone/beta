package game2.object;


import java.awt.Color;




import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import game2.control.Game2;
import game2.framework.GameObject;
import game2.framework.ObjectId;
import game2.window.Handler;

public class RofFactory extends GameObject {
	Game2 game;
	public RofFactory(double x, double y, ObjectId id,Game2 game2) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.game=game2;
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
		int type;
		Random random=new Random();
		type=random.nextInt(100)%2;
		handler.addObject(new Runoff(x, y-32,dm,handler, ObjectId.runOff, type, game));
	}
	public void prodT(Handler handler, Dimension dm){
		
			Random random = new Random();
			int xx=random.nextInt((int) (dm.getWidth()-(dm.getWidth()*3/4)));
			handler.addObject(new WaterTree(dm.getWidth()*3/4 + xx, dm.getHeight()-192, ObjectId.waterTree, dm, handler));
		
	}

}
