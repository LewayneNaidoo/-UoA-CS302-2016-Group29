package objects;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import generators.MapGenerator;
import scenes.MultiplayerGame;

public class Enemy{
	public double x = 0;
	public double y = 0;
	private double vel = 0;
	private Image explosion = null;
	private Image originalTankImage = null;
	private Image tank = null;
	private boolean isPrimary;
	private double direction;
	private boolean bulletToExplode = false;


	private Bullet bullet;

	public Enemy(int x, int y, double direction, boolean isPrimary) {
		// change to enums
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.isPrimary = isPrimary;

		try 
		{
			originalTankImage = ImageIO.read(new File ("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\enemyTank1.png"));

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		tank = createTransformedImage((BufferedImage) originalTankImage);
		bullet = null;

		try {
			explosion = ImageIO.read(new File ("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\explosion.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void paint(Graphics g) {

		g.drawImage(tank, (int)x, (int)y, 48, 48, null);

		//		Iterator<Bullet> b = bullets.iterator();
		//		while (b.hasNext()) {
		//			Bullet bullet = b.next(); // must be called before you can call i.remove()

		if(bullet!=null){
			bullet.paint(g);

			if(bullet.isOutOfBounds || bullet.bounceCount>1 || bulletToExplode) { 
				//				bullet.bounceCount = 0;
				bulletToExplode = false;
				g.drawImage(explosion, (int)bullet.x, (int)bullet.y, 40, 40, null);
				bullet = null;
			}
		}
	}
	//	}

	public void move(){
		int[][] wall_pos = MapGenerator.MapOne();
		//		System.out.println("Xtank: " + x + "Ytank: " + y);

		double xTemp = x + vel*Math.cos(Math.toRadians(direction));
		double yTemp = y + vel*Math.sin(Math.toRadians(direction));


		for (int i=0; i<85;i++ ){
			for (int j=0; j<64;j++){
				//				double prevX = x;
				//				double prevY = y;
				if (wall_pos[i][j] == 1 && xTemp > i*12 - 48 && xTemp < i*12 + 12  && yTemp > j*12 - 48 && yTemp < j*12 + 12){
					System.out.println("hitWall!");
					return;
				}
			}
		}


		if ((xTemp > 966)){
			xTemp = 966;
		}
		if (xTemp < 12 ){
			xTemp = 12;
		}
		if ((yTemp > 686)){
			yTemp = 686;
		}
		if (yTemp < 12){
			yTemp = 12;
		}
		//			for (int i = 0; i <= 84; i++) 
		//		    {
		//		        for (int j = 0; j <= 61; j++) 
		//		        {
		//		            if (wall_pos[i][j] == 1){
		//		            	if ((x == i*12) && (y == j*12)){
		//		            		x = i*12 - 6;
		//		            		y = j*12 - 6;
		//		            	}
		//		            } 
		//		        }
		//		    }		


		x = xTemp;
		y = yTemp;

	}


	public void rotate(boolean right){
		if (right){
			direction += 22.5;
		}else{
			direction -= 22.5;
		}
		if (direction >=360){
			direction = 0;
		}
		if ( direction < 0){
			direction = 360-22.5;
		}
		tank = createTransformedImage((BufferedImage) originalTankImage);
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(isPrimary) {
			if (code == KeyEvent.VK_UP) {
//				if (tankSpeed){
//					vel = 4.5;
//				}else if (tankSlow){
//					vel = 1.5;
//				}else{
					vel = 3;
//				}
			}
			else if (code == KeyEvent.VK_DOWN) {
//				if (tankSpeed){
//					vel = -4.5;
//				}else if (tankSlow){
//					vel = -1.5;
//				}else{
					vel = -3;
//				}
			}
			else if (code == KeyEvent.VK_LEFT) {
				rotate(false);
			}
			else if (code == KeyEvent.VK_RIGHT) {
				rotate(true);
			}
			else if (code == KeyEvent.VK_SPACE) {
				if (bullet == null){
//					if (bulletSpeed){
//						bullet = new Bullet (direction, x, y, 9*1.5);
//					}else if (bulletSlow){
//						bullet = new Bullet (direction, x, y, 9*0.5);
//					}else{
						bullet = new Bullet (direction, x, y, 9);
//					}
					
				}
			}
		}
		else {
			if (code == KeyEvent.VK_W) {
//				if (tankSpeed){
//					vel = 4.5;
//				}else if (tankSlow){
//					vel = 1.5;
//				}else{
					vel = 3;
//				}
			}
			else if (code == KeyEvent.VK_S) {
//				if (tankSpeed){
//					vel = -4.5;
//				}else if (tankSlow){
//					vel = -1.5;
//				}else{
					vel = -3;
//				}
			}
			else if (code == KeyEvent.VK_A) {
				rotate(false);
			}
			else if (code == KeyEvent.VK_D) {
				rotate(true);
			}
			else if (code == KeyEvent.VK_X) {
				//				if (direction > 67.5 && direction < 202.5){
				if (bullet == null){
//					if (bulletSpeed){
//						bullet = new Bullet (direction, x, y, 9*1.5);
//					}else if (bulletSlow){
//						bullet = new Bullet (direction, x, y, 9*0.5);
//					}else{
						bullet = new Bullet (direction, x, y, 9);
					}
					
				}
				//					bullets.add(bullet);
				//				}
				//				else{
				//					Bullet bullet = new Bullet (direction, x, y, 9);
				//					bullets.add(bullet);
				//				}
//			}
		}
	}


	public BufferedImage createTransformedImage(BufferedImage image) {
		//		System.out.println(direction);
		//		System.out.println("Xtank: " + x + "Ytank: " + y);
		double sin = Math.abs(Math.sin(direction));
		double cos = Math.abs(Math.cos(direction));
		int w = image.getWidth();
		int h = image.getHeight();
		int neww = (int) Math.floor(w * cos + h * sin);
		int newh = (int) Math.floor(h * cos + w * sin);
		BufferedImage result = new BufferedImage(neww, newh, Transparency.TRANSLUCENT);
		Graphics2D g2d = result.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//		g2d.translate((neww - w), (newh - h));
		g2d.rotate(Math.toRadians(direction), w/2, h/2);
		g2d.drawRenderedImage(image, null);
		g2d.dispose();
		return result;
	}

	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {
		if (isPrimary){
			if (e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_DOWN){
				vel = 0;
			}
		}

		else {
			if (e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_S){
				vel = 0;
			}
		}
	}
	public double getX (){
		return x;
	}
	public double getY (){
		return y;
	}
	//	public ArrayList<Bullet> getBullets(){
	//		return bullets;
	//	}
	public Image getImage(){
		return tank;
	}

	public void setBulletToExplode(){
		bulletToExplode = true;
	}
	public Bullet getBullet(){
		return bullet;
	}
}
