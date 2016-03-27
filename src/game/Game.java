package game;

import java.awt.Canvas;

import javax.swing.JFrame;

import graphics.Bullet;
import graphics.PlayerTank;

public class Game extends Canvas implements Runnable{
	
private static final long serialVersionUID = 1L;
	private JFrame frame;
	
	public Game(){
		frame = new JFrame();
		PlayerTank player = new PlayerTank(frame);
		Bullet b = new Bullet("RIGHT",0,0);
		frame.add(b);
		frame.add(player);
		frame.pack();
		frame.setResizable(false);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024,768);
	}
	
	private synchronized void start(){
		new Thread(this).start();
	}
	
	public void run() {
	}
	
	public static void main(String args[]){
		//Show panel with 3 buttons,
		
		//on one button show this
		new Game().start();	
	}
}


