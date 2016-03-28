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
import javax.swing.Timer;

public class Player{
	private Timer t = null;
	private double x = 0;
	private double y = 0;
	private double vel = 0;
	private Image originalTankImage = null;
	private Image tank = null;
	private boolean isPrimary;
	private double direction;

	private ArrayList<Bullet> bullets;

	public Player(int x, int y, double direction, boolean isPrimary) {
		// change to enums
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.isPrimary = isPrimary;

		try 
		{
			originalTankImage = ImageIO.read(new File ("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\playerTankE.png"));

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
//
//		t = new Timer(33, this);
//		t.start();
//		addKeyListener(this);
//		setFocusable(true);
//		setFocusTraversalKeysEnabled(false);

		tank = createTransformedImage((BufferedImage) originalTankImage);
		bullets = new ArrayList<>();
	}

	public void paint(Graphics g) {

		g.drawImage(tank, (int)x, (int)y, 48, 48, null);

		Iterator<Bullet> b = bullets.iterator();
		while (b.hasNext()) {
			Bullet bullet = b.next(); // must be called before you can call i.remove()
			bullet.paint(g);

			if(bullet.isOutOfBounds) {    
				b.remove();
			}
		}
	}

//		public void actionPerformed(ActionEvent e) {
//			if (x<30){
//				vel = 0;
//				x = 30;
//			}
//			if (x>950) {
//				vel = 0;
//				x = 950;
//			}
//			if (y>670){
//				vel = 0;
//				y = 670;
//			}
//			if (y<30){
//				vel = 0;
//				y = 30;
//			}
//			repaint();
//			move();
//		}

	public void move(){
		x += vel*Math.cos(Math.toRadians(direction));
		y += vel*Math.sin(Math.toRadians(direction));
	}


	//WHen pressing up, move according to direction facing
	public void up() {
		//		switch (facing){
		//		case "RIGHT" :
		//			vely = 0;
		//			velx = 1.5;
		//			break;
		//		case "LEFT" :
		//			vely = 0;
		//			velx = -1.5;
		//			break;
		//		case "UP" :
		//			vely = -1.5;
		//			velx = 0;
		//			break;
		//		case "DOWN" :
		//			vely = 1.5;
		//			velx = 0;
		//			break;
		//		}
		vel = 3;
	}

	//WHen pressing down, move according to direction facing
	public void down() {
		//		switch (facing){
		//		case "RIGHT" :
		//			vely = 0;
		//			velx = -1.5;
		//			break;
		//		case "LEFT" :
		//			vely = 0;
		//			velx = 1.5;
		//			break;
		//		case "UP" :
		//			vely = 1.5;
		//			velx = 0;
		//			break;
		//		case "DOWN" :
		//			vely = -1.5;
		//			velx = 0;
		//			break;
		//		}
		vel = -3;
	}

	//	public void left() {
	//		switch (facing){
	//		case "RIGHT" :
	//			facing = "UP";
	//			break;
	//		case "LEFT" :
	//			facing = "DOWN";
	//			break;
	//		case "UP" :
	//			facing = "LEFT";
	//			break;
	//		case "DOWN" :
	//			facing = "RIGHT";
	//			break;
	//		}
	//	}
	//	public void right() {
	//		switch (facing){
	//		case "RIGHT" :
	//			facing = "DOWN";
	//			break;
	//		case "LEFT" :
	//			facing = "UP";
	//			break;
	//		case "UP" :
	//			facing = "RIGHT";
	//			break;
	//		case "DOWN" :
	//			facing = "LEFT";
	//			break;
	//		}
	//	}

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
				up();
			}
			else if (code == KeyEvent.VK_DOWN) {
				down();
			}
			else if (code == KeyEvent.VK_LEFT) {
				rotate(false);
			}
			else if (code == KeyEvent.VK_RIGHT) {
				rotate(true);
			}
			else if (code == KeyEvent.VK_SPACE) {
							Bullet bullet = new Bullet (direction, x, y, 9);
							bullets.add(bullet);
			}
		}
		else {
			if (code == KeyEvent.VK_W) {
				up();
			}
			else if (code == KeyEvent.VK_S) {
				down();
			}
			else if (code == KeyEvent.VK_A) {
				rotate(false);
			}
			else if (code == KeyEvent.VK_D) {
				rotate(true);
			}
			else if (code == KeyEvent.VK_TAB) {
							Bullet bullet = new Bullet (direction, x, y, 9);
								bullets.add(bullet);
			}
		}
	}


	public BufferedImage createTransformedImage(BufferedImage image) {
		System.out.println(direction);
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
