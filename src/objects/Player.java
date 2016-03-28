package objects;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Player extends JPanel implements ActionListener, KeyListener{
	private Timer t = null;
	private String facing = "";
	private double x = 0;
	private double y = 0;
	private double velx = 0;
	private double vely = 0;
	private Image tankN = null;
	private Image tankS = null;
	private Image tankE = null;
	private Image tankW = null;
	private boolean isPrimary;

	private List<Bullet> bullets;

	public Player(int x, int y, String direction, boolean isPrimary) {
		// change to enums
		this.x = x;
		this.y = y;
		facing = direction;
		this.isPrimary = isPrimary;

		try 
		{
			tankN = ImageIO.read(new File ("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\playerTankN.png"));
			tankS = ImageIO.read(new File("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\playerTankS.png"));
			tankE = ImageIO.read(new File("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\playerTankE.png"));
			tankW = ImageIO.read(new File("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\playerTankW.png"));

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		t = new Timer(20, this);
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		bullets = new ArrayList<>();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		switch (facing) {
		case "RIGHT" :
			g.drawImage(tankE, (int)x, (int)y, 40, 40, null);
			//redraw or rotate image
			break;
		case "LEFT" :
			g.drawImage(tankW, (int)x, (int)y, 40, 40, null);
			//redraw or rotate image
			break;
		case "UP" :
			g.drawImage(tankN, (int)x, (int)y, 40, 40, null);
			//redraw or rotate image
			break;
		case "DOWN" :
			g.drawImage(tankS, (int)x, (int)y, 40, 40, null);
			//redraw or rotate image
			break;
		}

		Iterator<Bullet> b = bullets.iterator();
		while (b.hasNext()) {
			Bullet bullet = b.next(); // must be called before you can call i.remove()
			bullet.paint(g);

			if(bullet.isOutOfBounds) {    
				b.remove();
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (x<30){
			velx = 0;
			x = 30;
		}
		if (x>950) {
			velx = 0;
			x = 950;
		}
		if (y>670){
			velx = 0;
			y = 670;
		}
		if (y<30){
			vely = 0;
			y = 30;
		}
		repaint();
		x += velx;
		y += vely;
	}


	//WHen pressing up, move according to direction facing
	public void up() {
		switch (facing){
		case "RIGHT" :
			vely = 0;
			velx = 1.5;
			break;
		case "LEFT" :
			vely = 0;
			velx = -1.5;
			break;
		case "UP" :
			vely = -1.5;
			velx = 0;
			break;
		case "DOWN" :
			vely = 1.5;
			velx = 0;
			break;
		}
	}

	//WHen pressing down, move according to direction facing
	public void down() {
		switch (facing){
		case "RIGHT" :
			vely = 0;
			velx = -1.5;
			break;
		case "LEFT" :
			vely = 0;
			velx = 1.5;
			break;
		case "UP" :
			vely = 1.5;
			velx = 0;
			break;
		case "DOWN" :
			vely = -1.5;
			velx = 0;
			break;
		}
	}

	public void left() {
		switch (facing){
		case "RIGHT" :
			facing = "UP";
			break;
		case "LEFT" :
			facing = "DOWN";
			break;
		case "UP" :
			facing = "LEFT";
			break;
		case "DOWN" :
			facing = "RIGHT";
			break;
		}
	}
	public void right() {
		switch (facing){
		case "RIGHT" :
			facing = "DOWN";
			break;
		case "LEFT" :
			facing = "UP";
			break;
		case "UP" :
			facing = "RIGHT";
			break;
		case "DOWN" :
			facing = "LEFT";
			break;
		}
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
				left();
			}
			else if (code == KeyEvent.VK_RIGHT) {
				right();
			}
			else if (code == KeyEvent.VK_SPACE) {
				Bullet bullet = new Bullet (facing, x, y);
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
				left();
			}
			else if (code == KeyEvent.VK_D) {
				right();
			}
			else if (code == KeyEvent.VK_TAB) {
				Bullet bullet = new Bullet (facing, x, y);
				bullets.add(bullet);
			}
		}
	}
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {
		vely = 0;
		velx = 0;
	}
}
