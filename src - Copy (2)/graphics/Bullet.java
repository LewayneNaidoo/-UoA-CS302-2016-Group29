package graphics;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Bullet extends JPanel {
	private double x = 0;
	private double y = 0;
	private double velx = 0;
	private double vely = 0;

	/**
	 * The projectile of the tank
	 * @param direction the direction the tank is facing
	 * @param x the starting x position of the tank
	 * @param y the starting y position of the tank
	 */
	public Bullet(String direction, double x, double y) {
		System.out.println("bullet constructor");
		this.x = x;
		this.y = y;
		shoot(direction);
	}
	public void paint(Graphics g) {
		System.out.println("draw gray bullet");
//		super.paintComponent(g);
		g.setColor(Color.GRAY);
		g.fillOval((int)x, (int)y, 10, 10);
	}
	
//	public void paintComponent(Graphics g) {
//		System.out.println("draw gray bullet");
//		super.paintComponent(g);
//		g.setColor(Color.GRAY);
//		g.fillOval((int)x, (int)y, 10, 10);
//	}

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
				repaint();
			}
			break;
		case "LEFT" :
			while (x > 30) {
				velx = -2;
				x += velx;
				repaint();
			}
			break;
		case "UP" :
			while (y > 30) {
				vely = -2;
				y += vely;
				repaint();
			}
			break;
		case "DOWN" :
			while ( y < 670) {
				vely = 2;
				y += vely;
				repaint();
			}
			break;
		}
	}
}
