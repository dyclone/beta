package RunGame;

import game1.control.Game1;

import game2.control.Game2;
import game3.control.Game3;
import window.Window;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import Game.Game;


class game2Task extends TimerTask{

	@Override
	public void run() {
		RunGame.window.changeWindow(RunGame.dm,"Estuary", new Game2());
		System.out.println("Game 2");
		
	}
	
}

class game3Task extends TimerTask{

	@Override
	public void run() {
		RunGame.window.changeWindow(RunGame.dm,"Estuary", new Game3());
		System.out.println("Game 3");
		
	}
	
}

public class RunGame {
	public static boolean change;
	static boolean isRunning;
	public static Window window;
	static Game game;//current game state
	static Toolkit tk = Toolkit.getDefaultToolkit();
	public static Dimension dm = new Dimension(tk.getScreenSize());
	
	public static void main(String[] args){
		int gamecount = 1;
		isRunning = true;
		game = new Game1();
		window = new Window(dm,"Estuary",game);
		System.out.println("Game 1");
		//Timer t = new Timer();
		//t.schedule(new game2Task(), 5*1000);
		//t.schedule(new game3Task(), 10*1000);
		
		
	}
}
