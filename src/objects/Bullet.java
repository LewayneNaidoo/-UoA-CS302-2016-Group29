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

import generators.MapGenerator;

@SuppressWarnings("serial")
public class Bullet extends JPanel implements ActionListener{
	public double x;
	public double y;
	public int bounceCount = 0;
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
		this.x = x + 24 + 55*Math.cos(Math.toRadians(direction));
		this.y = y + 24 + 55*Math.sin(Math.toRadians(direction));

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

		int[][] wall_pos = MapGenerator.MapTwo();

		//		System.out.println("X: " + x + " Y: " + y);
		double xTemp = x + vel*Math.cos(Math.toRadians(direction));
		double yTemp = y + vel*Math.sin(Math.toRadians(direction));

		for (int i=0; i<85;i++ ){
			for (int j=0; j<64;j++){
				if (wall_pos[i][j] == 1 && xTemp > i*12 - 8 && xTemp < i*12 + 12  && yTemp > j*12 - 8 && yTemp < j*12 + 12){
					if (xTemp < i*12 + 12 && xTemp > i*12){
						vel = -vel;
						direction = 360 - direction;
						//					}
						//					if (yTemp < j*12 + 12 || yTemp > j*12){
						//					direction = 360 - direction;
						//					}
						bounceCount++;
						return;
					}
				}
			}
		}

		//		if ((xTemp > 1000)){
		//			vel = -vel;
		//			direction = 360 - direction;
		//			bounceCount++;
		//		}
		//		if (xTemp < 12 ){
		//			vel = -vel;
		//			direction = 360 - direction;
		//			bounceCount++;
		//		}
		//		if ((yTemp > 730)){
		//			direction = 360 - direction;
		//			bounceCount++;
		//		}
		//		if (yTemp < 12){
		//			direction = 360 - direction;
		//			bounceCount++;
		//		}



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

		x = xTemp;
		y = yTemp;

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
