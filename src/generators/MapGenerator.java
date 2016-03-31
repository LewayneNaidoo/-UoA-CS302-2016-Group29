package generators;

public class MapGenerator {
public static int[][] numbers = new int[85][64];

public MapGenerator(){
}

public static int[][] MapOne() {
    int[][] numbers = new int[85][64];

    for (int x = 0; x <= 84; x++) 
    {
        for (int y = 0; y <= 61; y++) 
        {
            if (x == 0 || y == 0 || x == 84 || y == 61) {
                numbers[x][y] = 1;
            }
            else {
                numbers[x][y] = 0;
            }
        }
    }

    //add random walls
    //numbers[4][5] = 1;

    return numbers;
}
public static int[][] MapTwo() {
    int[][] numbers = new int[85][64];

    for (int x = 0; x <= 84; x++) 
    {
        for (int y = 0; y <= 61; y++) 
        {
            if (x == 0 || y == 0 || x == 84 || y == 61) {
                numbers[x][y] = 1;
            }
            else {
                numbers[x][y] = 0;
            }
        }
    }

    //add random walls
    numbers[4][5] = 1;
    numbers[14][15] = 1;
    numbers[2][25] = 1;
    numbers[3][25] = 1;
    numbers[4][25] = 1;

    return numbers;
}
}