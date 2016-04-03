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

public class Player{
	public double x = 0;
	public double y = 0;
	private double vel = 0;
	private Image explosion = null;
	private Image originalTankImage = null;
	private Image tank = null;
	private boolean isPrimary;
	private double direction;
	private int hitTank = 0;


	private ArrayList<Bullet> bullets;

	public Player(int x, int y, double direction, boolean isPrimary) {
		// change to enums
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.isPrimary = isPrimary;

		try 
		{
			originalTankImage = ImageIO.read(new File ("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\playerOneTank.png"));

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		tank = createTransformedImage((BufferedImage) originalTankImage);
		bullets = new ArrayList<>();
		
		try {
			explosion = ImageIO.read(new File ("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\explosion.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void paint(Graphics g) {

		g.drawImage(tank, (int)x, (int)y, 48, 48, null);

		Iterator<Bullet> b = bullets.iterator();
		while (b.hasNext()) {
			Bullet bullet = b.next(); // must be called before you can call i.remove()
			bullet.paint(g);

			if(bullet.isOutOfBounds || bullet.bounceCount>1) { 
				
				b.remove();
				bullet.bounceCount = 0;
				g.drawImage(explosion, (int)bullet.x, (int)bullet.y, 40, 40, null);
			}
			if (x+20 > bullet.x && bullet.x > x-20){
				if (y+20 > bullet.y && bullet.y > y-20){
//				hitTank++;
				System.out.println("Tank hit!");
//				b.remove();
//				g.drawImage(explosion, (int)bullet.x, (int)bullet.y, 40, 40, null);
			}
			}
		}
	}

	public void move(){
		int[][] wall_pos = MapGenerator.MapTwo();

		x += vel*Math.cos(Math.toRadians(direction));
		y += vel*Math.sin(Math.toRadians(direction));
//		System.out.println("Xtank: " + x + "Ytank: " + y);
		
		if ((x > 966)){
			x = 966;
		}
		if (x < 12 ){
			x = 12;
		}
		if ((y > 686)){
			y = 686;
		}
		if (y < 12){
			y = 12;
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
				vel = 3;
			}
			else if (code == KeyEvent.VK_DOWN) {
				vel = -3;
			}
			else if (code == KeyEvent.VK_LEFT) {
				rotate(false);
			}
			else if (code == KeyEvent.VK_RIGHT) {
				rotate(true);
			}
			else if (code == KeyEvent.VK_SPACE) {
				Bullet bullet = new Bullet (direction, x, y, 9); //TODO fire at barrel, work out angles
				bullets.add(bullet);
			}
		}
		else {
			if (code == KeyEvent.VK_W) {
				vel = 3;
			}
			else if (code == KeyEvent.VK_S) {
				vel = -3;
			}
			else if (code == KeyEvent.VK_A) {
				rotate(false);
			}
			else if (code == KeyEvent.VK_D) {
				rotate(true);
			}
			else if (code == KeyEvent.VK_X) {
				Bullet bullet = new Bullet (direction, x, y, 9);
				bullets.add(bullet);
			}
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
	public ArrayList<Bullet> getBullets(){
		return bullets;
	}
	public Image getImage(){
		return tank;
	}
}
