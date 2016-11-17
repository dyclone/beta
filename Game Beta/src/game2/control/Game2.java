package game2.control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Game.BufferedImageLoader;
import Game.Game;
import game2.framework.KeyInput;
import game2.framework.ObjectId;
import game2.object.Critter;
import game2.object.RofFactory;
import game2.window.Handler;

public class Game2 extends Game {
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -6771508490304664935L;
	
	static Toolkit tk = Toolkit.getDefaultToolkit();
	static Dimension dm = new Dimension(tk.getScreenSize());
	private boolean running = false;
	private Thread thread;
	private BufferedImage image2;
	
	public int nRof = 0;
	public int trees = 0;
	public int count= 0;
	//Object
	Handler handler;
	RofFactory factory;
//	TrashBin trashBin=new TrashBin(550, 550, ObjectId.trashBin,handler);
	
	 
	public void init(){
		
		handler = new Handler(this);
		factory=new RofFactory(0,dm.getHeight()*3/5 - 32,ObjectId.RofFactory,this);
		handler.creatSurface(dm);
		handler.addObject(factory);
		handler.addObject(new Critter(600, dm.getHeight()*3/5 - 32, ObjectId.critter, true, true, handler,dm,this));
		this.addKeyListener(new KeyInput(handler));
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			image2 = loader.loadImage("/overfishing_background.jpg");
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	public synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {
		
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
//				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
				if(trees<=3){
					factory.prodT(handler, dm);
					trees+=1;
				}
				if(nRof<4){
					factory.prodRof(handler,dm);
					
					nRof+=1;
					
				}
			}
			
		}
		
	}
	public void tick(){
		handler.tick();
		
	}
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		//Drow here
		
		g.drawImage(image2, 0 , 0, 1950, 700, null);
		handler.render(g);
		//
		g.dispose();
		bs.show();
	}
	
}
