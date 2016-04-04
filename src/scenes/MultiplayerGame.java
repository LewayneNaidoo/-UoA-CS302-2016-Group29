package scenes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import generators.WallPainter;
import objects.Bullet;
import objects.Player;
import objects.PowerUps;
// http://opengameart.org/content/top-down-painted-tanks
public class MultiplayerGame extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Timer timer = null;
//	private Timer PUTimer = null;
	Player playerOne;
	Player playerTwo;
	WallPainter wall;
	PowerUps powerUp;
	public static int hitTank = 0;

	public MultiplayerGame(){
		frame = new JFrame();
		//		    Player playerOne = new Player(0, 0, 90, true);
		//		    Player playerTwo = new Player(900, 600, 270, false);
		//		    frame.add(playerOne);
		//		    frame.add(playerTwo);
		frame.pack();
		frame.setResizable(false);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(150,0);
		frame.setSize(1024,768);
		addKeyListener(new TAdapter());
		setFocusable(true);
		setDoubleBuffered(true);

		playerOne = new Player(400, 400, 270, true);
		playerTwo = new Player(200, 200, 90, false);
		wall = new WallPainter();
		
		//TODO add in timer for 10 second gaps
		double k = Math.random()*1000+20;
		double l = Math.random()*750+20;
		powerUp = new PowerUps((int)k, (int)l);

		timer = new Timer(33, this);
		timer.start(); 

//		PUTimer = new Timer(0, this);
//		PUTimer.start();


	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color( 0, 0, 0));
		g.fillRect(0, 0, 1024, 768);
		doDrawing(g);
		Toolkit.getDefaultToolkit().sync();
	}

	private void doDrawing(Graphics g) {
		playerOne.paint(g);
		playerTwo.paint(g);
		wall.paint(g);
		
		//TODO add in timer for 10 second gaps
		powerUp.paint(g);
		
		if (powerUp.getX()+12 > playerOne.x && playerOne.x > powerUp.getX()-12){
			if (powerUp.getY()+12 > playerOne.y && playerOne.y > powerUp.getY()-12){
				PowerUps.randomPowerUp();
				System.out.println("hit powerup!");
			}
		}
		if (powerUp.getX()+12 > playerTwo.x && playerTwo.x > powerUp.getX()-12){
			if (powerUp.getY()+12 > playerTwo.y && playerTwo.y > powerUp.getY()-12){
				PowerUps.randomPowerUp();
				System.out.println("hit powerup!");
			}
		}

		
		//		for (int i=0;i<=1024;i+=48){
		//		wall = new Walls(i,0);
		//		wall.paint(g);
		//			}
		ArrayList<Bullet> playerOneBullet = playerOne.getBullets();
		ArrayList<Bullet> playerTwoBullet = playerTwo.getBullets();

		for (Bullet bullet: playerOneBullet){
			if (playerOne.x+20 > bullet.getX() && bullet.getX() > playerOne.x-20){
				if (playerOne.y+20 > bullet.getY() && bullet.getY() > playerOne.y-20){
					hitTank++;
					System.out.println("Tank hit!");
				}
			}
			if (playerTwo.x+20 > bullet.getX() && bullet.getX() > playerTwo.x-20){
				if (playerTwo.y+20 > bullet.getY() && bullet.getY() > playerTwo.y-20){
					hitTank++;
					System.out.println("Tank hit!");
				}
			}

		}
		for (Bullet bullet: playerTwoBullet){
			if (playerOne.x+20 > bullet.getX() && bullet.getX() > playerOne.x-20){
				if (playerOne.y+20 > bullet.getY() && bullet.getY() > playerOne.y-20){
					hitTank++;
					System.out.println("Tank hit!");
				}
			}
			if (playerTwo.x+20 > bullet.getX() && bullet.getX() > playerTwo.x-20){
				if (playerTwo.y+20 > bullet.getY() && bullet.getY() > playerTwo.y-20){
					hitTank++;
					System.out.println("Tank hit!");
				}
			}

		}
//		Timer PUTimer = new Timer(5000, new ActionListener() {
//			  @Override
//			  public void actionPerformed(ActionEvent arg0) {
//					powerUp = new PowerUps();
//	        		powerUp.paint(g);
//	        		System.out.println("power up!");
//			  }
//			});
//			PUTimer.setRepeats(false); 
//			PUTimer.start();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		playerOne.move();
		playerTwo.move();
		repaint();
	}

	// http://zetcode.com/tutorials/javagamestutorial/movingsprites/
	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			playerOne.keyReleased(e);
			playerTwo.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			playerOne.keyPressed(e);
			playerTwo.keyPressed(e);
		}
	}
}