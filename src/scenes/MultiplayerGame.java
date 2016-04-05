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
import java.util.Iterator;
import java.util.List;

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
	PowerUps powerUps;
	public static int hitTank = 0;

	//	private ArrayList powerUps;

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
		//		powerUps = new ArrayList();
		double k = Math.random()*1000+20;
		double l = Math.random()*750+20;
		powerUps = new PowerUps((int)k, (int)l);

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

		//		Iterator<PowerUps> p = ((List<PowerUps>) powerUps).iterator();
		//		while (p.hasNext()) {
		//			PowerUps powerUp = p.next(); // must be called before you can call i.remove()
		//			
		//			powerUp.paint(g);
		//
		//			if(PowerUps.powerUpConsumed == 1) { 
		//				p.remove();
		//				PowerUps.powerUpConsumed = 0;
		//				// Give tank powerUp, animation
		//			}
		//		}
	}

	private void doDrawing(Graphics g) {
		playerOne.paint(g);
		playerTwo.paint(g);
		wall.paint(g);

		//TODO add in timer for 10 second gaps
		powerUps.paint(g);

		if (powerUps.getX()+12 > playerOne.x && playerOne.x > powerUps.getX()-12){
			if (powerUps.getY()+12 > playerOne.y && playerOne.y > powerUps.getY()-12){
				PowerUps.randomPowerUp();
				System.out.println("hit powerup!");
				PowerUps.powerUpConsumed = 1;
			}
		}
		if (powerUps.getX()+12 > playerTwo.x && playerTwo.x > powerUps.getX()-12){
			if (powerUps.getY()+12 > playerTwo.y && playerTwo.y > powerUps.getY()-12){
				PowerUps.randomPowerUp();
				System.out.println("hit powerup!");
				PowerUps.powerUpConsumed = 1;
			}
		}


		//		for (int i=0;i<=1024;i+=48){
		//		wall = new Walls(i,0);
		//		wall.paint(g);
		//			}
		Bullet playerOneBullet = playerOne.getBullet();
		if(playerOneBullet!=null){
			if (playerOne.x+20 > playerOneBullet.getX() && playerOneBullet.getX() > playerOne.x-20){
				if (playerOne.y+20 > playerOneBullet.getY() && playerOneBullet.getY() > playerOne.y-20){
					hitTank++;
					playerOne.setBulletToExplode();
					System.out.println("Tank hit!");
				}
			}
			if (playerTwo.x+20 > playerOneBullet.getX() && playerOneBullet.getX() > playerTwo.x-20){
				if (playerTwo.y+20 > playerOneBullet.getY() && playerOneBullet.getY() > playerTwo.y-20){
					hitTank++;
					playerOne.setBulletToExplode();
					System.out.println("Tank hit!");
				}
			}
		}
		Bullet playerTwoBullet = playerTwo.getBullet();
		if (playerTwoBullet!=null){
			if (playerOne.x+20 > playerTwoBullet.getX() && playerTwoBullet.getX() > playerOne.x-20){
				if (playerOne.y+20 > playerTwoBullet.getY() && playerTwoBullet.getY() > playerOne.y-20){
					hitTank++;
					playerTwo.setBulletToExplode();
					System.out.println("Tank hit!");
				}
			}
			if (playerTwo.x+20 > playerTwoBullet.getX() && playerTwoBullet.getX() > playerTwo.x-20){
				if (playerTwo.y+20 > playerTwoBullet.getY() && playerTwoBullet.getY() > playerTwo.y-20){
					hitTank++;
					playerTwo.setBulletToExplode();
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