package scenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import generators.WallPainter;
import objects.Bullet;
import objects.Enemy;
import objects.Player;
import objects.PowerUps;
// http://opengameart.org/content/top-down-painted-tanks
public class Training extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Timer timer = null;
	//	private Timer PUTimer = null;
	Player playerOne;
	Enemy enemy;
	WallPainter wall;
	PowerUps powerUps;
	public static int hitTank = 0;
	public static int enemyDestroyed = 0;
	public static int respawn = 1;
	private Image explosion = null;

	//	private ArrayList powerUps;

	public Training(){
		frame = new JFrame();
		//		    Player playerOne = new Player(0, 0, 90, true);
		//		    Player enemy = new Player(900, 600, 270, false);
		//		    frame.add(playerOne);
		//		    frame.add(enemy);
		frame.pack();
		frame.setResizable(false);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(150,0);
		frame.setSize(1024,768);
		addKeyListener(new TAdapter());
		setFocusable(true);
		setDoubleBuffered(true);

		playerOne = new Player(900, 320, 180, true);
		
		wall = new WallPainter();
		double k = Math.random()*1000+20;
		double l = Math.random()*750+20;
		powerUps = new PowerUps((int)k, (int)l);

		timer = new Timer(33, this);
		timer.start(); 

		//		PUTimer = new Timer(0, this);
		//		PUTimer.start();
		try {
			explosion = ImageIO.read(new File ("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\explosion.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public Dimension getPreferredSize(){
		return new Dimension(1024, 768);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color( 0, 0, 0));
		g.fillRect(0, 0, 1024, 768);
		g.setColor(new Color( 55, 55, 55));
		g.fillRect(0, 0, 1024, 60);
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
		wall.paint(g);
		if (respawn == 1){
		int xRandom = (int) (Math. random() * 950 + 20);
		int yRandom = (int) (Math. random() * 500 + 20);
		enemy = new Enemy(xRandom, yRandom, 270, false);
		System.out.println("enemyX: "+ xRandom + "enemyY: " + yRandom);
		respawn = 0;
		}
		enemy.paint(g);
		PowerUps.powerUpConsumed = 2;
		//TODO add in timer for 10 second gaps
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {

			public void run() {
				PowerUps.powerUpConsumed = 0;
			}
		 
		}, 0, 10, TimeUnit.SECONDS);

		if (powerUps.getX()+12 > playerOne.x && playerOne.x > powerUps.getX()-12){
			if (powerUps.getY()+12 > playerOne.y && playerOne.y > powerUps.getY()-12){
				PowerUps.randomPowerUp();
				System.out.println("hit powerup!");
				PowerUps.powerUpConsumed = 1;
			}
		}
		if (powerUps.getX()+12 > enemy.x && enemy.x > powerUps.getX()-12){
			if (powerUps.getY()+12 > enemy.y && enemy.y > powerUps.getY()-12){
				PowerUps.randomPowerUp();
				System.out.println("hit powerup!");
				PowerUps.powerUpConsumed = 1;
			}
		}
		if (PowerUps.powerUpConsumed == 0){
			powerUps.paint(g);
			ScheduledExecutorService exec1 = Executors.newSingleThreadScheduledExecutor();
			exec1.scheduleAtFixedRate(new Runnable() {

				public void run() {
					PowerUps.powerUpConsumed = 2;
				}
			 
			}, 0, 10, TimeUnit.SECONDS);
		}

//		if (PowerUps.powerUpConsumed == 1){
//			int spec = PowerUps.randomPowerUp();
//			System.out.println("PowerUp: " + spec );
//			PowerUps.powerUpConsumed = 0;
//		}
		

		//		for (int i=0;i<=1024;i+=48){
		//		wall = new Walls(i,0);
		//		wall.paint(g);
		//			}
		Bullet playerOneBullet = playerOne.getBullet();
		if(playerOneBullet!=null){
			if (playerOne.x+40 > playerOneBullet.getX() && playerOneBullet.getX() > playerOne.x){
				if (playerOne.y+20 > playerOneBullet.getY() && playerOneBullet.getY() > playerOne.y){
					hitTank++;
					playerOne.setBulletToExplode();
					System.out.println("Tank hit!");
				}
			}
			if (enemy.x+40 > playerOneBullet.getX() && playerOneBullet.getX() > enemy.x){
				if (enemy.y+40 > playerOneBullet.getY() && playerOneBullet.getY() > enemy.y){
					hitTank++;
					respawn = 1;
					playerOne.setBulletToExplode();
					g.drawImage(explosion, (int)enemy.x, (int)enemy.y, 100, 100, null);
					System.out.println("Tank hit!");
				}
			}
		}
		Bullet enemyBullet = enemy.getBullet();
		if (enemyBullet!=null){
			if (playerOne.x+40 > enemyBullet.getX() && enemyBullet.getX() > playerOne.x){
				if (playerOne.y+40 > enemyBullet.getY() && enemyBullet.getY() > playerOne.y){
					hitTank++;
					enemy.setBulletToExplode();
					System.out.println("Tank hit!");
				}
			}
			if (enemy.x+40 > enemyBullet.getX() && enemyBullet.getX() > enemy.x){
				if (enemy.y+40 > enemyBullet.getY() && enemyBullet.getY() > enemy.y){
					hitTank++;
					enemy.setBulletToExplode();
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


	public void drawPowerUp(Graphics g){
		if (PowerUps.powerUpConsumed != 1){
			powerUps.paint(g);
			}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		playerOne.move();
		enemy.move();
		repaint();
	}

	// http://zetcode.com/tutorials/javagamestutorial/movingsprites/
	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			playerOne.keyReleased(e);
			enemy.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			playerOne.keyPressed(e);
			enemy.keyPressed(e);
		}
	}
}