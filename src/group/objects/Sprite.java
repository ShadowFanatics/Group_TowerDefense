package group.objects;

import android.graphics.Bitmap;
import android.util.Log;

public class Sprite {
	private Bitmap bitmap;
	private Sprite next;
	
	public Sprite(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
}
