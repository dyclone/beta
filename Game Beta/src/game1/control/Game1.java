package game1.control;

import java.awt.Color;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Game.BufferedImageLoader;
import Game.Game;
import game1.framework.KeyInput;
import game1.framework.ObjectId;
import game1.object.Boat;
import game1.object.CompostCounter;
import game1.object.Critter;
import game1.object.Habitat;
import game1.object.RecycleBin;
import game1.object.RofFactory;
import game1.object.TrashBin;
import game1.window.Handler;
import window.Window;
import RunGame.RunGame;

public class Game1 extends Game{

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -6771508490304664935L;
	//public static boolean win = false;
	static Toolkit tk = Toolkit.getDefaultToolkit();
	static Dimension dm = new Dimension(tk.getScreenSize());
	private boolean running = false;
	private Thread thread;
	private BufferedImage image;
	
	int nRof = 0;
	// Object
	Handler handler;
	RofFactory factory;
	TrashBin trashBin=new TrashBin(dm.getWidth()-50, dm.getHeight()-70, ObjectId.trashBin,handler);
	RecycleBin recyclebin=new RecycleBin(dm.getWidth()-100, dm.getHeight()-70, ObjectId.recycleBin,handler);
	CompostCounter counter = new CompostCounter(10,10, ObjectId.compostCounter);
	double[] tempLoc;

	public void init() {

		//Default Objects
		handler = new Handler();
		factory = new RofFactory(0, dm.getHeight() * 3 / 5 - 32, ObjectId.RofFactory);
		handler.creatSurface(dm);
		handler.addObject(factory);
		
		//Game 1 Objects
		tempLoc = handler.boatLoc(dm);
		handler.addObject(new Boat(tempLoc[0], tempLoc[1], ObjectId.boat,handler,trashBin, recyclebin, counter,tempLoc[0],dm.getWidth()));		
		handler.addObject(trashBin);
		handler.addObject(recyclebin);
		handler.addObject(counter);
		tempLoc = handler.habitatLoc(dm);
		handler.addObject(new Habitat(tempLoc[0], tempLoc[1], ObjectId.habitat,handler, dm));
		
		//Critter
		handler.addObject(new Critter(600, dm.getHeight() * 3 / 5 - 32, ObjectId.critter, true, true, handler));
		this.addKeyListener(new KeyInput(handler));
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			image = loader.loadImage("/overfishing_background.jpg");
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public synchronized void start() {
		if (running)
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

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
				if (nRof < 4) {
					//factory.prodRof(handler, dm);
					nRof += 1;
				}
			}

		}

	}

	public void tick() {
		handler.tick();

	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		// Draw here
		
		g.drawImage(image, 0 , 0, 1950, 700, null);
		handler.render(g);
		//
		g.dispose();
		bs.show();
	}


}


