package group.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import audio.AudioManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menuactivity);
	}
	
	@Override
	protected void onResume()	//TODO
	{
		super.onResume();
		
		AudioManager.setContext(this);
		AudioManager.playBGM_title();
	}
	
	@Override
	protected void onDestroy()	//TODO
	{
		super.onDestroy();
		AudioManager.releaseAll();
	}
	
	public void start(View view)//開始遊戲
	{
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, GameActivity.class);
		startActivity(intent);
	}

	public void record(View view)//排行榜
	{
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, RankActivity.class);
		startActivity(intent);
		this.finish();
	}
	public void quit(View view)//離開遊戲
	{
		this.finish();
	}
}
