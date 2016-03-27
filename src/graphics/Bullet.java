package graphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Bullet extends JPanel implements ActionListener{
	private double x = 0;
	private double y = 0;
	private double velx = 0;
	private double vely = 0;
	private Timer timer;
	private String direction;
	private Graphics g;
	
	/**
	 * The projectile of the tank
	 * @param direction the direction the tank is facing
	 * @param x the starting x position of the tank
	 * @param y the starting y position of the tank
	 */
	public Bullet(String direction, double x, double y) {
		timer = new Timer(200, this);
		System.out.println("bullet constructor");
		this.direction = direction;
		this.x = x;
		this.y = y;
		timer.start();
	}

	public void paint (Graphics g) {
		System.out.println("X: " + x + " Y: " + y);
		this.g = g;
		
		g.setColor(Color.GRAY);
		g.fillOval((int)x, (int)y, 10, 10);
		shoot(direction);
	}

	/**
	 * Shoot the bullet forward
	 * @param direction the direction the bullet should move
	 */
	public void shoot(String direction) {
		System.out.println("shoot in " + direction);
		switch (direction){
		case "RIGHT" :
			while (x < 950){
				velx = 2;
				x += velx;
				paint(g);
			}
			break;
		case "LEFT" :
			while (x > 30) {
				velx = -2;
				x += velx;
				paint(g);
			}
			break;
		case "UP" :
			while (y > 30) {
				vely = -2;
				y += vely;
				paint(g);
			}
			break;
		case "DOWN" :
			while ( y < 670) {
				vely = 2;
				y += vely;
				paint(g);
			}
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
