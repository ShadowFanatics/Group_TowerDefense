package group.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import group.engine.EventHandler;
import group.engine.Game;
import group.engine.Panel;

public class GameActivity extends Activity implements Runnable{
	private Panel scene;
	private Thread paintThread;
	private Game game;
	private EventHandler eventHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		scene = Panel.getObject(this);
		setContentView(scene);
		eventHandler = EventHandler.getObject();
		game = Game.getObject();
		paintThread = new Thread(this);
		paintThread.start();
	}
	
	public void run() {
		while (true) {
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
}
