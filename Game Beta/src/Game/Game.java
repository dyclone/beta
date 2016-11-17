package Game;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public abstract class Game extends Canvas implements Runnable{
	
	/**
	 * Generated serialVersionUID
	 */
	/*private static final long serialVersionUID = -6771508490304664935L;
	
	private boolean running = false;
	private Thread thread;
	
	//Object
	Handler handler;
	TrashBin trashBin=new TrashBin(550, 550, ObjectId.trashBin,handler);
	RecycleBin recyclebin=new RecycleBin(500, 550, ObjectId.recycleBin,handler);*/
	public static int win;
	public void init(){};
	
	public void start(){};
	@Override
	public void run(){};
	public void tick(){};
	public void render(){};
	
	
	
}
