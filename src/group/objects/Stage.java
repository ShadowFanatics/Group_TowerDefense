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
	private int[] pathImage = {R.drawable.tower_block,R.drawable.block};
	private Bitmap[] pathBitmap;
	private Point[] startPoints;
	private EnemyPath[] enemyPaths;
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
				startPoints = new Point[2];
				startPoints[0] = new Point(0,1);
				startPoints[1] = new Point(0,11);
				enemyPaths = new EnemyPath[2];
				enemyPaths[0] = new EnemyPath(new Point[]{new Point(0,1),new Point(13,1),new Point(13,6),new Point(6,6),new Point(6,3),new Point(2,3),new Point(2,9),new Point(12,9),new Point(12,11),new Point(14,11)});
				enemyPaths[1] = new EnemyPath(new Point[]{new Point(0,11),new Point(9,11),new Point(9,3),new Point(2,3),new Point(2,9),new Point(12,9),new Point(12,11),new Point(14,11)});
				break;
		}
	}
	private int wave = 0;
	private int currentWave = 0;
	private int enemyCount = 0;
	public void runStage(int time) {
		if ( time % 13 == 3 ) {
			wave++;
			enemyCount = 0;
		}
		if ( wave != currentWave ) {
			enemyCount++;
			EventHandler.sendEvent(new Event(Event.ENEMY_CREAT, enemyIdCount++, (wave % EnemyTypeList.types), 0, 0));
			EventHandler.sendEvent(new Event(Event.ENEMY_CREAT, enemyIdCount++, (wave % EnemyTypeList.types), 1, 1));
			if ( enemyCount >= 5 ) {
				currentWave++;
			}
		}
		
		if ( time % 91 == 3 ) {
			for ( int i = 0; i < EnemyTypeList.types; i++ ) {
				EnemyTypeList.HP[i] *= 1.5;
			}
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
