package group.engine;

import group.objects.Object;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Panel extends SurfaceView implements SurfaceHolder.Callback {
	/* seetings */
	private float blockSizeWidth = 40;
	private float blockSizeHeight = 40;
	private float offestOfMapX = 40;
	private float offestOfMapY = 80;
	private float screeWidth;
	private float screenHeight;
	private float density;
	/* --------*/
	private static Panel panel = null;
	
	private SurfaceHolder surfaceHolder;
	private boolean isReady = false;
	private Thread paintThread;
	private Resources res;
	
	public static Panel getObject(Context context, int screeWidth, int screenHeight) {
		if ( panel == null ) {
			panel = new Panel(context, screeWidth, screenHeight);
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
	
	public Panel(Context context, int screeWidth, int screenHeight) {
		super(context);
		this.screeWidth = screeWidth;
		this.screenHeight = screenHeight;
		Log.e("screeWidth",String.valueOf(screeWidth));
		Log.e("screenHeight",String.valueOf(screenHeight));
		res = context.getResources(); 
		surfaceHolder = this.getHolder();
		surfaceHolder.addCallback(this);
		density = res.getDisplayMetrics().density;
		
	}
	private ArrayList<Object> layer = null;
	public void draw() {
		if ( !isReady ) return;
		Canvas canvas = null;
		try {
			canvas = surfaceHolder.lockCanvas(null);
			synchronized (surfaceHolder) {
				offestOfMapX = (float) (screeWidth * 0.05);
				offestOfMapY = (float) (screenHeight * 0.018);
				blockSizeWidth = (float) ( screeWidth * 0.0415 );
				blockSizeHeight = (float) ( screenHeight * 0.074 );
				canvas.drawColor(Color.BLUE);
				canvas.drawBitmap(Game.getObject().getBackground(), new Rect(0, 0, Game.getObject().getBackground().getWidth(), Game.getObject().getBackground().getHeight()), new RectF(0, 0, screeWidth, screenHeight), null);
				int[][] test = Game.getObject().getStage().getPath();
				Paint paint = new Paint();
				paint.setColor(Color.DKGRAY);
				Paint paint2 = new Paint();
				paint2.setColor(Color.GREEN);
				paint2.setStyle(Style.STROKE);
				Rect block = new Rect(0, 0, Game.getObject().getStage().getPathBitmap(test[0][0]).getWidth(), Game.getObject().getStage().getPathBitmap(test[0][0]).getHeight());
				for ( int i = 0; i < 8; i++ ) {
					for (int j = 0; j < 8; j++) {
							canvas.drawBitmap(Game.getObject().getStage().getPathBitmap(test[j][i]), block, new RectF(offestOfMapX + i*blockSizeWidth, offestOfMapY + j*blockSizeHeight, offestOfMapX + i*blockSizeWidth + blockSizeWidth, offestOfMapY + j*blockSizeHeight + blockSizeHeight), null);
					}
				}
				
				for ( int i = 0; i < 3; i++ ) {
					layer = Game.getObject().getLayer(i);
					for (int j = 0; j < layer.size(); j++) {
						//canvas.drawBitmap(layer.get(j).getSprite().getBitmap(), layer.get(j).getX(), layer.get(j).getY(), null);
						canvas.drawBitmap(layer.get(j).getSprite().getBitmap(), new Rect(0,0,layer.get(j).getSprite().getBitmap().getWidth() * (int)density, layer.get(j).getSprite().getBitmap().getHeight() * (int)density), new RectF(layer.get(j).getX(),layer.get(j).getY(),layer.get(j).getX() + layer.get(j).getWidth(),layer.get(j).getY() + layer.get(j).getHeight()), null);
						canvas.drawCircle(layer.get(j).getX() + layer.get(j).getWidth()/2, layer.get(j).getY() + layer.get(j).getHeight()/2, 100, paint2);
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
	
	public float[] getBlockSize() {
		return new float[]{blockSizeWidth, blockSizeHeight};
	}
	
	public float getOffestOfMapX() {
		return offestOfMapX;
	}
	
	public float getOffestOfMapY() {
		return offestOfMapY;
	}
}
