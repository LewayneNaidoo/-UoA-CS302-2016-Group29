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
	private double x;
	private double y;
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
			bullet = ImageIO.read(new File ("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\playerTankN.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void paint (Graphics g) {
		System.out.println("X: " + x + " Y: " + y);
		x += vel*Math.cos(Math.toRadians(direction));
		y += vel*Math.sin(Math.toRadians(direction));
		g.drawImage(bullet, (int)x, (int)y, 10, 10, null);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		isOutOfBounds = true;
	}
}
