package group.objects;

public class Map {
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
