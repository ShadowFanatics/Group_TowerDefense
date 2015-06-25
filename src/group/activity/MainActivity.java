package group.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView tv1;
	TextView tv2;
	TextView tv3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menuactivity);	
		//setViewerDensity();

	}
	
	private void setViewerDensity(){
		tv1 = (TextView)findViewById(R.id.menu_tv1);
		tv2 = (TextView)findViewById(R.id.menu_tv2);
		tv3 = (TextView)findViewById(R.id.menu_tv3);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int display_width = metrics.widthPixels;
		int display_height = metrics.heightPixels;
		
		
		tv1.setWidth((int)(display_width / 5));
		tv2.setWidth((int)(display_width / 5));
		tv3.setWidth((int)(display_width / 5));
		//tv1.setHeight((int)(tv1.getHeight() * metrics.density));
		//tv2.setHeight((int)(tv2.getHeight() * metrics.density));
		//tv3.setHeight((int)(tv3.getHeight() * metrics.density));
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
