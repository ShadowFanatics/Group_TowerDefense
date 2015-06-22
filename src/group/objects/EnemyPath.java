package group.objects;

import android.graphics.Point;

public class EnemyPath {
	private Point[] locations;
	
	public EnemyPath(Point[] input) {
		locations = input;
	}
	
	public Point getNextLocation(int index) {
		return locations[index];
	}
	
	public int getLength() {
		return locations.length;
	}
}
