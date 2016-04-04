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
		this.x = x+20;
		this.y = y+20;

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

		//		System.out.println("X: " + x + " Y: " + y);
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

		g.drawImage(bullet, (int)x, (int)y, 8, 8, null);




	}
	public int getX (){
		return (int) x;
	}
	public int getY (){
		return (int)y;
	}
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			isOutOfBounds = true;
		}
	}
