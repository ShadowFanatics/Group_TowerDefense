package group.engine;

import group.activity.R;
import group.objects.Bullet;
import group.objects.Object;
import group.objects.Tower;
import group.objects.TowerTypeList;

import java.io.Serializable;
import java.util.ArrayList;

import android.view.View;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
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
	private boolean canDrag;
	private ArrayList<Object> UITower = new ArrayList<Object>();
	private Object movingObject;
	
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
		this.setKeepScreenOn(true);
		Log.e("screeWidth",String.valueOf(screeWidth));
		Log.e("screenHeight",String.valueOf(screenHeight));
		res = context.getResources(); 
		surfaceHolder = this.getHolder();
		surfaceHolder.addCallback(this);
		density = res.getDisplayMetrics().density;
		setOnTouchListener(touchListener);
		/* create UI*/
		Object newUITower = new Object(BitmapFactory.decodeResource(res, R.drawable.blue), (float) (screeWidth*0.763), (float) (screenHeight*0.09));
		UITower.add(newUITower);
		newUITower = new Object(BitmapFactory.decodeResource(res, R.drawable.ball), (float) (screeWidth*0.89), (float) (screenHeight*0.09));
		UITower.add(newUITower);
		newUITower = new Object(BitmapFactory.decodeResource(res, R.drawable.red), (float) (screeWidth*0.763), (float) (screenHeight*0.30));
		UITower.add(newUITower);
		newUITower = new Object(BitmapFactory.decodeResource(res, R.drawable.purple), (float) (screeWidth*0.89), (float) (screenHeight*0.30));
		UITower.add(newUITower);
		newUITower = new Object(BitmapFactory.decodeResource(res, R.drawable.light), (float) (screeWidth*0.763), (float) (screenHeight*0.51));
		UITower.add(newUITower);
		newUITower = new Object(BitmapFactory.decodeResource(res, R.drawable.crystal), (float) (screeWidth*0.89), (float) (screenHeight*0.51));
		UITower.add(newUITower);
		
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
				paint.setColor(Color.WHITE);
				Paint paint2 = new Paint();
				paint2.setColor(Color.GREEN);
				paint2.setStyle(Style.STROKE);
				Rect block = new Rect(0, 0, Game.getObject().getStage().getPathBitmap(test[0][0]).getWidth(), Game.getObject().getStage().getPathBitmap(test[0][0]).getHeight());
				for ( int i = 0; i < 15; i++ ) {
					for (int j = 0; j < 13; j++) {
							canvas.drawBitmap(Game.getObject().getStage().getPathBitmap(test[j][i]), block, new RectF(offestOfMapX + i*blockSizeWidth, offestOfMapY + j*blockSizeHeight, offestOfMapX + i*blockSizeWidth + blockSizeWidth, offestOfMapY + j*blockSizeHeight + blockSizeHeight), null);
					}
				}
				Paint paint4 = new Paint();
				paint4.setColor(Color.WHITE);
				paint4.setTextSize(screenHeight*(float)0.02);
				for ( int i = 0; i < 3; i++ ) {
					layer = Game.getObject().getLayer(i);
					for (int j = 0; j < layer.size(); j++) {
						if ( i != 2 ) {
							canvas.drawBitmap(layer.get(j).getSprite().getBitmap(), new Rect(0,0,layer.get(j).getSprite().getBitmap().getWidth(), layer.get(j).getSprite().getBitmap().getHeight()), new RectF(layer.get(j).getX(),layer.get(j).getY(),layer.get(j).getX() + blockSizeWidth,layer.get(j).getY() + blockSizeHeight), null);
						}
						else {
							canvas.drawBitmap(layer.get(j).getSprite().getBitmap(), new Rect(0,0,layer.get(j).getSprite().getBitmap().getWidth(), layer.get(j).getSprite().getBitmap().getHeight()), new RectF(layer.get(j).getX(),layer.get(j).getY(),layer.get(j).getX() + layer.get(j).getWidth(),layer.get(j).getY() + layer.get(j).getHeight()), null);
						}
						if ( i == 1 ) {
							Tower tower = (Tower)layer.get(j);
							canvas.drawCircle(tower.getX() + blockSizeWidth/2, tower.getY() + blockSizeHeight/2, tower.getRadius(), paint2);
							canvas.drawText("Level: " + String.valueOf(tower.getLevel()), tower.getX() + 1, tower.getY() + blockSizeHeight* (float)0.9, paint4);
						}
					}
				}
				Paint paintMask = new Paint();
				paintMask.setColor(Color.BLACK);
				paintMask.setAlpha(200);
				Paint paint3 = new Paint();
				paint3.setColor(Color.BLACK);
				paint3.setTextSize(screenHeight*(float)0.02);
				for (int i = 0; i < UITower.size(); i++) {
					canvas.drawRect(UITower.get(i).getX(), UITower.get(i).getY(), UITower.get(i).getX() + blockSizeWidth, UITower.get(i).getY() + blockSizeHeight, paint);
					canvas.drawBitmap(UITower.get(i).getSprite().getBitmap(), new Rect( 0, 0, UITower.get(i).getSprite().getBitmap().getWidth(), UITower.get(i).getSprite().getBitmap().getHeight()), new RectF( UITower.get(i).getX(), UITower.get(i).getY(), UITower.get(i).getX() + blockSizeWidth, UITower.get(i).getY() + blockSizeHeight), null);
					canvas.drawText(String.valueOf(TowerTypeList.moneyCost[i]), UITower.get(i).getX(), UITower.get(i).getY() + blockSizeHeight*(float)1.2, paint3);
					if ( Game.getObject().getMoney() < TowerTypeList.moneyCost[i] ) {
						canvas.drawRect(UITower.get(i).getX(), UITower.get(i).getY(), UITower.get(i).getX() + blockSizeWidth, UITower.get(i).getY() + blockSizeHeight, paintMask);
					}
				}
				paint3.setColor(Color.BLACK);
				paint3.setTextSize(screenHeight*(float)0.05);
				canvas.drawText("Score: " + String.valueOf(Game.getObject().getScore()), screeWidth * (float)0.73, screenHeight * (float)0.75, paint3);
				canvas.drawText("Time: " + String.valueOf(Game.getObject().getTime()), screeWidth * (float)0.73, screenHeight * (float)0.8, paint3);
				canvas.drawText("Money: " + String.valueOf(Game.getObject().getMoney()), screeWidth * (float)0.73, screenHeight * (float)0.85, paint3);
				canvas.drawText("Life: " + String.valueOf(Game.getObject().getLife()), screeWidth * (float)0.73, screenHeight * (float)0.9, paint3);
				
				if ( canDrag ) {
					canvas.drawBitmap(movingObject.getSprite().getBitmap(), new Rect( 0, 0, movingObject.getSprite().getBitmap().getWidth(), movingObject.getSprite().getBitmap().getHeight()),  new RectF( movingObject.getX(), movingObject.getY(),movingObject.getX() + blockSizeWidth, movingObject.getY() + blockSizeHeight), null);
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
	
	private OnTouchListener touchListener = new  OnTouchListener() {
		private int tower_type;
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			Point point = new Point();
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				point.x = (int) event.getX();
				point.y = (int) event.getY();
				for (int i = 0; i < UITower.size(); i++) {
					Object t = UITower.get(i);
					RectF temp = new RectF(t.getX(), t.getY(), t.getX() + blockSizeWidth, t.getY() + blockSizeHeight);
					if (temp.contains(point.x, point.y) && Game.getObject().getMoney() >= TowerTypeList.moneyCost[i]){
						movingObject = t.clone();
						tower_type = i;
						//Log.e("touch","touch");
						canDrag = true;
						break;
					}
				}
				
				break;
			case MotionEvent.ACTION_MOVE:
				//Log.e("Move", "moving");
				point.x = (int) event.getX();
				point.y = (int) event.getY();
				if (canDrag) {
					movingObject.setLocation(point.x - blockSizeWidth / 2, point.y - blockSizeHeight / 2);
				}
				break;
			case MotionEvent.ACTION_UP:
				if ( canDrag ) {
					point.x = (int) event.getX();
					point.y = (int) event.getY();
					RectF check;
					for ( int i = 0; i < 15; i++ ) {
						for (int j = 0; j < 13; j++) {
							check = new RectF(offestOfMapX + i*blockSizeWidth, offestOfMapY + j*blockSizeHeight, offestOfMapX + i*blockSizeWidth + blockSizeWidth, offestOfMapY + j*blockSizeHeight + blockSizeHeight);
							if ( check.contains(point.x, point.y)) {
								if( Game.getObject().getStage().getPath()[j][i] == 0 ) {
									EventHandler.sendEvent(new Event(Event.BUILD_TOWER, tower_type, i, j));
								}
							}
						}
					}
					
					canDrag = false;
					tower_type= -1;
					movingObject = null;
				}
				break;
			default:
				break;
			}
			return true;
		}
	};
	
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
