package objects;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Bullet extends JPanel implements ActionListener{
	public double x;
	public double y;
	public static int bounceCount = 0;
	public static int hitTank = 0;
	private Image bullet;
	public boolean isOutOfBounds = false;
	private double direction;
	private double vel;

	/**
	 * The projectile of the tank
	 * @param direction the direction the tank is facing
	 * @param x the starting x position of the tank
	 * @param y the starting y position of the tank
	 */
	public Bullet(double direction, double x, double y, double vel) {
		Timer t = new Timer(11000, this);
		t.start();
		this.vel = vel;
		this.direction = direction;
		this.x = x+12;
		this.y = y+12;

		try 
		{
			bullet = ImageIO.read(new File ("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\bullet.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void paint (Graphics g) {

		//		int[][] wall_pos = MapGenerator.MapTwo();

		System.out.println("X: " + x + " Y: " + y);
		x += vel*Math.cos(Math.toRadians(direction));
		y += vel*Math.sin(Math.toRadians(direction));

		if ((x > 1000)){
			vel = -vel;
			direction = 360 - direction;
			bounceCount++;
		}
		if (x < 12 ){
			vel = -vel;
			direction = 360 - direction;
			bounceCount++;
		}
		if ((y > 730)){
			vel = vel;
			direction = 360 - direction;
			bounceCount++;
		}
		if (y < 12){
			vel = vel;
			direction = 360 - direction;
			bounceCount++;
		}
		
		// TODO Compare location of bullet and location of tank, if same then hitTank++
		// if hitTank = 1 in player class, paint explosion
		
		//		for (int i = 0; i <= 84; i++) 
		//		    {
		//		        for (int j = 0; j <= 61; j++) 
		//		        {
		//		            if (wall_pos[i][j] == 1){
		//		            	if ((x == i*12) && (y == j*12)){
		//		            		x = 200;
		//		            		y = 200;
		//		            	}
		//		            } 
		//		        }
		//		    }

		g.drawImage(bullet, (int)x, (int)y, 10, 10, null);




	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		isOutOfBounds = true;
	}
}
