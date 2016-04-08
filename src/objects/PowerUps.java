package objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PowerUps extends JPanel{
	
	public double x;
	public double y;
	static int shield = 0;
	static int tankSpeed = 0;
	static int bulletSpeed = 0;
	static int tankSlow = 0;
	static int bulletSlow = 0;
	public static int powerUpConsumed = 0;
	
	private static Image powerup = null;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public PowerUps(int x, int y){
//		Timer t = new Timer(10000, (ActionListener) this);
//		t.start();
		this.x = x;
		this.y = y;
		try {
			powerup = ImageIO.read(new File ("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\powerUp.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	public void paint(Graphics g) {

		g.drawImage(powerup, (int)x, (int)y, 24, 24, null);
		
	}
	
	
	public static int randomPowerUp(){
		double random = Math. random() * 6 + 1;
//		if (random == 1){
//			return shield = 1;
//		}
//		else if (random == 2){
//			return tankSpeed = 1;
//		}
//		else if (random == 3){
//			return tankSlow = 1;
//		}
//		else if (random == 4){
//			return bulletSpeed = 1;
//		}
//		else if (random == 5){
//			return bulletSlow = 1;
//		}
		return (int) random;
	}
	public int getX (){
		return (int)x;
	} 
	public int getY (){
		return (int)y;
	}
}
