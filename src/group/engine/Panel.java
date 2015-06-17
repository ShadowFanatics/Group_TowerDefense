package group.engine;

import group.objects.Object;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Panel extends SurfaceView implements SurfaceHolder.Callback {
	private static Panel panel = null;
	
	private SurfaceHolder surfaceHolder;
	private boolean isReady = false;
	private Thread paintThread;
	private Resources res;
	
	public static Panel getObject(Context context) {
		if ( panel == null ) {
			panel = new Panel(context);
		}
		return panel;
	}
	
	public static Panel getObject() {
		if ( panel == null ) {
			Log.e("Panel Error", "在panel未初始化就使用了。請洽硬硬");
			return null;
		}
		return panel;
	}
	
	public Panel(Context context) {
		super(context);
		res = context.getResources(); 
		surfaceHolder = this.getHolder();
		surfaceHolder.addCallback(this);
	}
	private ArrayList<Object> layer = null;
	public void draw() {
		if ( !isReady ) return;
		Canvas canvas = null;
		try {
			canvas = surfaceHolder.lockCanvas(null);
			synchronized (surfaceHolder) {
				canvas.drawColor(Color.BLUE);
				int[][] test = Game.getObject().getStage().getPath();
				Paint paint = new Paint();
				paint.setColor(Color.DKGRAY);
				Paint paint2 = new Paint();
				paint2.setColor(Color.GREEN);
				
				for ( int i = 0; i < 8; i++ ) {
					for (int j = 0; j < 8; j++) {
							canvas.drawBitmap(Game.getObject().getStage().getPathBitmap(test[j][i]), i*40, j*40, null);
					}
				}
				
				for ( int i = 0; i < 3; i++ ) {
					layer = Game.getObject().getLayer(i);
					for (int j = 0; j < layer.size(); j++) {
						canvas.drawBitmap(layer.get(j).getSprite().getBitmap(), layer.get(j).getX(), layer.get(j).getY(), null);
					}
				}
				
			}
		} finally {
			if (canvas != null) {
				surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
	}
	
	public void loading() {
		if ( !isReady ) return;
		Canvas canvas = null;
		try {
			canvas = surfaceHolder.lockCanvas(null);
			synchronized (surfaceHolder) {
				canvas.drawColor(Color.GREEN);
			}
		} finally {
			if (canvas != null) {
				surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		isReady = true;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	
	public Resources getResources() {
		return res;
	}
	
	public boolean isReady() {
		return isReady;
	}
}
