package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

import Game.Game;

import game1.framework.KeyInput;
import game1.framework.ObjectId;
import game1.object.Clock;
import game1.object.Critter;
import game1.object.RecycleBin;
import game1.object.Runoff;
import game1.object.TrashBin;
import game1.window.Handler;

public class GameWin extends Game{
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -6771508490304664935L;
	private boolean running = false;
	private Thread thread;
	private static int timeRemaining = 4;
	
	//Object
	Handler handler;
	int count = 1;
	Timer t = new Timer();
	TrashBin trashBin=new TrashBin(550, 550, ObjectId.trashBin,handler);
	RecycleBin recyclebin=new RecycleBin(500, 550, ObjectId.recycleBin,handler);
	//Game game2 = new Game2();
	
	public void init(){
		//handler = new Handler();
		//handler.creatSurface();
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
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			//also sets value to change game state
			
			
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			/*if(timeRemaining <= 0){
				timeRemaining = 0;
				t.schedule(new ExitTask(), 1*1000);
				running = false;
			}*/
			render();
			if(count == 1){
				t.schedule(new WinTask(), 1*1000);
				t.schedule(new WinTask(), 2*1000);
				t.schedule(new WinTask(), 3*1000);
				t.schedule(new Exit2Task(), 4*1000);
				count = 0;
			}
			
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
//				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		
	}
	public void tick(){
		//handler.tick();
	}
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		//Drow here
		
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("You Win!!!", this.getWidth()/2 - (this.getWidth()/26), this.getHeight()/2- (this.getHeight()/10));
		g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		g.drawString("Exiting game in: "+this.timeRemaining, this.getWidth()/2 - (this.getWidth()/26) - 20, this.getHeight()/2);
		
		//handler.render(g);
		//handler.render(g);
		//
		g.dispose();
		bs.show();
	}
	public static int getTime(){
		return timeRemaining;
	}
	public static void setTime(int tt){
		timeRemaining = tt;
	}
}
class WinTask extends TimerTask{

	@Override
	public void run() {
		
		GameWin.setTime(GameWin.getTime()-1);
		
	}
	
}
class Exit2Task extends TimerTask{

	@Override
	public void run() {
		System.exit(1);
		
	}
	
}

