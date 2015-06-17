package group.engine;

import group.activity.R;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import group.objects.*;
import group.objects.Object;


public class Game {
	private static Game game = null;
	private Stage stage;
	public static Game getObject() {
		if ( game == null ) {
			game = new Game();
		}
		return game;
	}
	
	private ArrayList<Object> backLayer = new ArrayList<Object>();
	private ArrayList<Object> middleLayer = new ArrayList<Object>();
	private ArrayList<Object> frontLayer = new ArrayList<Object>();
	
	public Game() {
		stage = new Stage();
		Object test = new Object(BitmapFactory.decodeResource(Panel.getObject().getResources(), R.drawable.ic_launcher),0,0);
		middleLayer.add(test);
	}
	
	public void runGame() {
		//Log.e("!!!", "!!!");
	}
	
	public Stage getStage() {
		return stage;
	}
	
	private void enemyMove() {
		
	}
	
	public ArrayList<Object> getLayer(int i) {
		switch(i) {
			case 0:
				return backLayer;
			case 1:
				return middleLayer;
			case 2:
				return frontLayer;
			default:
				return null;
		}
	}
}
