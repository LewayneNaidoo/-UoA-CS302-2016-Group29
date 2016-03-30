package objects;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Walls extends JPanel{
	private static final long serialVersionUID = 1L;

	private Image wall = null;
	
	public Walls(){
//		this.i = i;
//		this.j = j;
		
			try {
				wall = ImageIO.read(new File ("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\wall.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
 
	public void paint(Graphics g) {
		//top border
		for (int i=0;i<=1024;i+=48){
			int j = 0;
			g.drawImage(wall, i, j, 48, 48, null);
				}
		//bottom border
		for (int i=0;i<=1024;i+=48){
			int j = 690;
			g.drawImage(wall, i, j, 48, 48, null);
				}
		//left border
		for (int j=0;j<=690;j+=48){
			int i = 0;
			g.drawImage(wall, i, j, 48, 48, null);
				}
		//right border
		for (int j=0;j<=768;j+=48){
			int i = 1024-48;
			g.drawImage(wall, i, j, 48, 48, null);
				}
		

}
	public Image getImage(){
		return wall;
	}
}