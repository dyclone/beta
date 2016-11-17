package game2.object;


import java.awt.Color;


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

public class LandSurface extends GameObject {
	Game2 game;

	
	public LandSurface(double x, double y, ObjectId id, Game2 game2) {
		super(x, y, id);
		this.game=game2;
	
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
		
	}

	@Override
	public void render(Graphics g) {
		if(id==ObjectId.landSurface){
			g.setColor(Color.orange);
			g.fillRect((int)x, (int)y, 32, 32);
		}
		if(id==ObjectId.seaLevel){
			switch(game.count){
			case 0:
				g.setColor(Color.CYAN);
				g.fillRect((int)x, (int)y, 32, 32);
				break;
			case 1:
				g.setColor(Color.blue);
				g.fillRect((int)x, (int)y, 32, 32);
				break;
			case 2:
				g.setColor(Color.green);
				g.fillRect((int)x, (int)y, 32, 32);
				break;
			case 3:
				g.setColor(Color.darkGray);
				g.fillRect((int)x, (int)y, 32, 32);
				break;
			default:
				g.setColor(Color.black);
				g.fillRect((int)x, (int)y, 32, 32);
				break;
			}
			
			
		}
		if(id==ObjectId.wall){
			g.setColor(Color.gray);
			g.fillRect((int)x, (int)y, 32, 32);
		}
		if(id==ObjectId.Sand){
			g.setColor(Color.YELLOW);
			g.fillRect((int)x, (int)y, 32, 32);
		}
		
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 32,32);
	}

}
