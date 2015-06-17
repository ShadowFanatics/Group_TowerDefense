package group.objects;

import android.graphics.Bitmap;
import android.util.Log;

public class Object {
	private Sprite sprite;
	private float x = 0;
	private float y = 0;
	public Object(Bitmap bitmap, float x, float y) {
		if ( bitmap == null ) {
			Log.e("BitmapError", "Bitmap is null");
		}
		sprite = new Sprite(bitmap);
		setLocation(x, y);
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
}
