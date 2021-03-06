package game1.object;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import Game.BufferedImageLoader;
import game1.framework.GameObject;
import game1.framework.ObjectId;
import game1.window.Handler;

public class RecycleBin extends GameObject {
	Handler handler;
	private BufferedImage rBinImage;
	
	public RecycleBin(double x, double y, ObjectId id,Handler handler) {
		super(x, y, id);
		this.handler=handler;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			rBinImage = loader.loadImage("/recycle_open.png");
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
		collision(object);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(rBinImage, (int)x, (int)y, 32, 32, null);

	}
	private void collision(LinkedList<GameObject> object){
		Iterator<GameObject> itr=object.iterator();
		for(;itr.hasNext();){
			GameObject temp = itr.next();
			if(temp.getId()==ObjectId.recycle){
				if(getBounds().intersects(temp.getBounds()))
					itr.remove();
			}
		}
	}
	
	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
