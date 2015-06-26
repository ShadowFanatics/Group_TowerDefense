package group.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import audio.AudioManager;
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
		
		//TODO
		AudioManager.setContext(GameActivity.this);
		AudioManager.playBGM_battle();
		
		paintThread = new Thread(this);
		paintThread.start();
	}
	
	public void run() {
		while (!isPause) {
			if ( game.runGame() ) {
				/*end game*/
				isPause = true;
				mHandler.sendEmptyMessage(1);
				break;
			}
			scene.draw();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private Handler mHandler = new Handler() {
	    public void handleMessage(Message msg) {
	        switch (msg.what) {
	        case 1:
	        	final View v = LayoutInflater.from(GameActivity.this).inflate(R.layout.rank_dialog, null);
	        	new AlertDialog.Builder(GameActivity.this)
	    		.setTitle("遊戲結束!請輸入你的名字")
	    		.setView(v)
	    		.setPositiveButton("確定", new DialogInterface.OnClickListener() {
	    			@Override
	    			public void onClick(DialogInterface dialog, int which) {
	    				EditText editText = (EditText)(v.findViewById(R.id.rank_name));
	    				Intent intent = new Intent();
	    				intent.setClass(GameActivity.this, RankActivity.class);
	    				
	    				String player_name = editText.getText().toString();
	    				Bundle bundle = new Bundle();
	    				bundle.putBoolean("save", true);
	    				bundle.putString("name", player_name);
	    				bundle.putString("score", String.valueOf(Game.getObject().getScore()));
	    				bundle.putString("time", String.valueOf(Game.getObject().getTime()));
	    				bundle.putString("money", String.valueOf(Game.getObject().getMoney()));
	    				intent.putExtras(bundle);
	    				startActivity(intent);
	    				finish();
	    			}
	    		}).show();
	        	break;
	        }
	    }
	};
	
	@Override
	protected void onPause() {
		super.onPause();
		isPause = true;
		//scene.clean();
		//game.clean();
		AudioManager.releaseAll();
		finish();
	}
	
	
	//返回鍵 鎖住 要返回 註解掉以下code
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

}
