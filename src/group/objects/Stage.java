package group.objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import group.activity.R;
import group.engine.Panel;


public class Stage {
	private Map mapData;
	private int[] pathImage = {R.drawable.map_block,R.drawable.map_path};
	private Bitmap[] pathBitmap;
	private Point[] startPoints;
	public Stage() {
		mapData = new Map(0);
		pathBitmap = new Bitmap[pathImage.length];
		for ( int i = 0; i < pathImage.length; i++ ) {
			pathBitmap[i] = BitmapFactory.decodeResource(Panel.getObject().getResources(), pathImage[i]);
		}
		startPoints = new Point[1];
		startPoints[0] = new Point();
		startPoints[0].x = 6;
		startPoints[0].y = 0;
	}
	
	public int[][] getPath() {
		return mapData.getPath();
	}
	
	public Bitmap getPathBitmap(int i) {
		return pathBitmap[i];
	}
	
	public Point getStartPoint(int i) {
		if ( i > startPoints.length ) {
			return startPoints[0];
		}
		return startPoints[i];
		
	}
}
