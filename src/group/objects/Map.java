package group.objects;

public class Map {
							//0 1  2  3  4  5  6  7
	private int[][] path = {{0, 0, 0, 0, 0, 0, 1, 0},
							{0, 1, 1, 1, 1, 1, 1, 0},
							{0, 1, 0, 0, 0, 0, 0, 0},
							{0, 1, 0, 1, 1, 1, 0, 0},
							{0, 1, 0, 1, 0, 1, 0, 0},
							{0, 1, 0, 1, 0, 1, 0, 0},
							{0, 1, 1, 1, 0, 1, 1, 1},
							{0, 0, 0, 0, 0, 0, 0, 0}};
	public Map(int type) {
		
	}
	
	public int[][] getPath() {
		return path;
	}
	
}
