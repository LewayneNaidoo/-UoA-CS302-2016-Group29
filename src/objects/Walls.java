package objects;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Walls extends JPanel{
	private static final long serialVersionUID = 1L;
	//	private static MapGenerator MapOne = new MapGenerator();
	private Image wall = null;
//	MapGenerator MapOne;

	public Walls(){
		//		this.i = i;
		//		this.j = j;

	//	MapOne = new MapGenerator();
		try {
			wall = ImageIO.read(new File ("C:\\Users\\King\\workspace\\Compsys302_Project\\res\\wall.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void paint(Graphics g) {
//		MapGenerator();
//		int x = objects.MapGenerator.x;
//		int y = objects.MapGenerator.y;
//
//		for (x = 0; x < 64; x++) 
//		{
//			for (y = 0; y < 85; y++) 
//			{
//				if (objects.MapGenerator.numbers[x][y] == 1){
//					g.drawImage(wall, x, y, 48, 48, null);
//				}
//			}
//		}
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
//	private void MapGenerator() {
//
//	}


	public Image getImage(){
		return wall;
	}
}