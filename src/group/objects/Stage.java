package group.objects;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import group.activity.R;
import group.engine.Event;
import group.engine.EventHandler;
import group.engine.Panel;


public class Stage {
	private Map mapData;
	private int[] pathImage = {R.drawable.map_block,R.drawable.map_path};
	private Bitmap[] pathBitmap;
	private Point[] startPoints;
	private EnemyPath[] enemyPaths;
	private Enemy[] enemies;
	private int enemyIdCount = 0;
	private Bitmap backgroundBitmap;
	
	public Stage(int stageID) {
		switch (stageID) {
			default:
				mapData = new Map(0);
				backgroundBitmap = BitmapFactory.decodeResource(Panel.getObject().getResources(), R.drawable.back);
				pathBitmap = new Bitmap[pathImage.length];
				for ( int i = 0; i < pathImage.length; i++ ) {
					pathBitmap[i] = BitmapFactory.decodeResource(Panel.getObject().getResources(), pathImage[i]);
				}
				startPoints = new Point[1];
				startPoints[0] = new Point();
				startPoints[0].x = 6;
				startPoints[0].y = 0;
				enemies = new Enemy[1];
				enemyPaths = new EnemyPath[1];
				enemyPaths[0] = new EnemyPath(new Point[]{new Point(6,1),new Point(1,1),new Point(1,6),new Point(3,6),new Point(3,3),new Point(5,3),new Point(5,6),new Point(7,6)});
				break;
		}
	}
	
	public void runStage(int time) {
		if ( time == 5 ) {
			EventHandler.sendEvent(new Event(Event.ENEMY_CREAT, enemyIdCount++, 0, 0, 0));
			EventHandler.sendEvent(new Event(Event.BUILD_TOWER, 0, 2, 2));
		}
		
	}
	
	public int[][] getPath() {
		return mapData.getPath();
	}
	
	public EnemyPath getEnemyPath(int id) {
		return enemyPaths[id];
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
	
	public Bitmap getBackground() {
		return backgroundBitmap;
	}
}
