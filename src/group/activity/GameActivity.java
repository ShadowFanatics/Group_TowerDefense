package group.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.WindowManager;
import group.engine.EventHandler;
import group.engine.Game;
import group.engine.Panel;

public class GameActivity extends Activity implements Runnable{
	private Panel scene;
	private Thread paintThread;
	private Game game;
	private EventHandler eventHandler;
	private boolean isPause = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//隱藏手機狀態
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//隱藏應用程式標題
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		int screenWidth = displayMetrics.widthPixels;
		int screenHeight = displayMetrics.heightPixels;
		scene = Panel.getObject(this, screenWidth, screenHeight);
		setContentView(scene);
		eventHandler = EventHandler.getObject();
		game = Game.getObject();
		paintThread = new Thread(this);
		paintThread.start();
	}
	
	public void run() {
		while (!isPause) {
			game.runGame();
			scene.draw();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		isPause = true;
		finish();
	}


}
