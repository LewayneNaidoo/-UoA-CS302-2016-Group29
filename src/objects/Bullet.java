package objects;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Bullet extends JPanel implements ActionListener{
private double x;
private double y;
private Image bullet;
public boolean isOutOfBounds = false;
private String direction;

/**
 * The projectile of the tank
 * @param direction the direction the tank is facing
 * @param x the starting x position of the tank
 * @param y the starting y position of the tank
 */
public Bullet(final String direction, double x, double y) {
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
    switch (direction) {
    case "RIGHT" :
        System.out.println("direction right - entering while loop");
        if (x < 950){
            x += 9;
            g.drawImage(bullet, (int)x, (int)y, 10, 10, null);
        }else {
            isOutOfBounds = true;
        }
        break;
    case "LEFT" :
        if (x > 30) {
            x += -9;
            g.drawImage(bullet, (int)x, (int)y, 10, 10, null);
        }else {
            isOutOfBounds = true;
        }
        break;
    case "UP" :
        if (y > 30) {
            y += -9;
            g.drawImage(bullet, (int)x, (int)y, 10, 10, null);
        }else {
            isOutOfBounds = true;
        }
        break;
    case "DOWN" :
        if ( y < 670) {
            y += 9;
            g.drawImage(bullet, (int)x, (int)y, 10, 10, null);
        }else {
            isOutOfBounds = true;
        }
        break;
    }
}

@Override
public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub

}
}
