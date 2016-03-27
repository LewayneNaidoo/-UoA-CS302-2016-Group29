package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import graphics.Bullet;
import graphics.PlayerOne;

public class Game extends JPanel implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	private PlayerOne playerOne;
	private static List<Bullet> bullets;
	private Timer timer = new Timer(20,this);
	
	private static JFrame frame;
	
	public Game() {
		// Tanks and bullets
		playerOne = new PlayerOne();
		frame.add(playerOne);
		bullets = new ArrayList<Bullet>();
//		timer.start();
	}

	public void addBullet(Bullet bullet){
		bullets.add(bullet);	
	}

	public void run() {
	}

	public static void main(String args[]){
		//Show panel with 3 buttons,

		//on one button show this

		frame = new JFrame();
		frame.add(new Game());

		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024,768);
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1024, 768);

		//For each object call paint
//		playerOne.paint(g);
		for (int i = 0 ; i < bullets.size(); i ++){
			bullets.get(i).paint(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}


