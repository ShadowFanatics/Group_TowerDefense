package group.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menuactivity);
		
	}
	
	public void start(View view)//開始遊戲
	{
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, GameActivity.class);
		startActivity(intent);
	}
	public void record(View view)//排行榜
	{
		Intent intent=new Intent();
		intent.setClass(MainActivity.this, MainActivity.class);
		startActivity(intent);
	}
	public void quit(View view)//離開遊戲
	{
		this.finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
