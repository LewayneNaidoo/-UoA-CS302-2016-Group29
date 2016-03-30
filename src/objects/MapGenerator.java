package objects;


public class MapGenerator {

	static int x = 0;
	static int y = 0;
	public static int[][] numbers = new int[64][85];
	
	public MapGenerator(){
		int[][] numbers = new int[64][85];
		

		for (x = 0; x < 64; x++) 
		{
			for (y = 0; y < 85; y++) 
			{
				if (x == 0 || y == 0 || x == 64 || y == 85) {
					numbers[x][y] = 1;
				}
				else {
					numbers[x][y] = 0;
				}
			}
		}
	}
}
