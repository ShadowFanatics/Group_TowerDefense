package group.engine;

import group.activity.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.Log;
import group.objects.*;
import group.objects.Object;


public class Game {
	private static Game game = null;
	private Stage stage;
	private Timer timer;
	private int gameTime = 0;//單位s
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Tower> towers = new ArrayList<Tower>();
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	public static Game getObject() {
		if ( game == null ) {
			game = new Game();
		}
		return game;
	}
	
	public Game() {
		stage = new Stage(0);
		timer = new Timer();
		timer.schedule(task, 0,1000);
		
	}
	
	public void runGame() {
		enemyMove();
		towerDetect();
		bulletFly();
		//Log.e("!!!", "!!!");
	}
	
	public void createEnemy(int id,int location, int type, int path) {
		Point createPoint = stage.getStartPoint(location);
		float[] blockSize = Panel.getObject().getBlockSize();
		float offestOfMapX = Panel.getObject().getOffestOfMapX();
		float offestOfMapY = Panel.getObject().getOffestOfMapY();
		Enemy newEnemy = new Enemy(BitmapFactory.decodeResource(Panel.getObject().getResources(), R.drawable.ic_launcher), offestOfMapX + createPoint.x*blockSize[0], offestOfMapY + createPoint.y*blockSize[1], id, type, path);
		enemies.add(newEnemy);
	}
	
	public void createTower(int tower_type, int x, int y) {
		float[] blockSize = Panel.getObject().getBlockSize();
		float offestOfMapX = Panel.getObject().getOffestOfMapX();
		float offestOfMapY = Panel.getObject().getOffestOfMapY();
		Tower newtower = new Tower(BitmapFactory.decodeResource(Panel.getObject().getResources(), TowerTypeList.images[tower_type]), offestOfMapX + x*blockSize[0], offestOfMapY + y*blockSize[1], tower_type);
		towers.add(newtower);
	}
	
	public void demageEnemy(int enemyID, int demage) {
		for ( int i = 0; i < enemies.size(); i++ ) {
			if ( enemies.get(i).getID() == enemyID ) {
				if ( enemies.get(i).giveDemage(demage) ) {
					enemies.remove(i);
				}
			}
		}
		
	}
	
	private TimerTask task = new TimerTask(){
		public void run() {
			stage.runStage(gameTime++);
		}
	};
	
	public Stage getStage() {
		return stage;
	}
	
	private void enemyMove() {
		Enemy movingEnemy = null;
		for ( int i = 0; i < enemies.size(); i++ ) {
			movingEnemy = enemies.get(i);
			Point nextLocation = stage.getEnemyPath(movingEnemy.getWalkPathID()).getNextLocation(movingEnemy.getHadGoPath());
			float offestX = nextLocation.x * Panel.getObject().getBlockSize()[0] + Panel.getObject().getOffestOfMapX() - movingEnemy.getX();
			float offestY = nextLocation.y * Panel.getObject().getBlockSize()[1] + Panel.getObject().getOffestOfMapY() - movingEnemy.getY();
			if ( offestX == 0.0 && offestY == 0 ) {
				if ( movingEnemy.getHadGoPath() < stage.getEnemyPath(movingEnemy.getWalkPathID()).getLength() - 1 ) {
					movingEnemy.addHadGoPath();
				}
				else {
					/* arrive end point*/
				}
			}
			else {
				float speed = movingEnemy.getMoveSpeed();
				if ( offestX > speed ) {
					movingEnemy.move(speed, 0);
				}
				else if ( offestX < -speed ) {
					movingEnemy.move(-speed, 0);
				}
				else {
					movingEnemy.move(offestX, 0);
				}
				if ( offestY > speed ) {
					movingEnemy.move(0, speed);
				}
				else if ( offestY < -speed ) {
					movingEnemy.move(0, -speed);
				}
				else {
					movingEnemy.move(0, offestY);
				}
			}
			
		}
	}
	
	private void towerDetect() {
		Tower tower = null;
		Enemy enemy = null;
		for ( int i = 0; i < towers.size(); i++ ) {
			tower = towers.get(i);
			tower.runInterval();
			for ( int j = 0; j < enemies.size(); j++ ) {
				enemy = enemies.get(j);	
				if ( tower.detect(enemy) ) {
					/* create bullet */
					Bullet newBullet = new Bullet(BitmapFactory.decodeResource(Panel.getObject().getResources(), R.drawable.bullet), tower.getX() + tower.getWidth() / 2, tower.getY() + tower.getHeight() / 2, tower.getBulletSpeed(), tower.getAttack(), enemy);
					bullets.add(newBullet);
				}
			}
		}
	}
	
	public void bulletFly() {
		for ( int i = 0; i < bullets.size(); i++ ) {
			if ( bullets.get(i).moveToTarget() ) {
				EventHandler.sendEvent(new Event(Event.ATTACK_EVENT, bullets.get(i).getTargetEnemyID(), bullets.get(i).getAttack() ));
				bullets.remove(i);
			}
		}
	}
	
	public ArrayList getLayer(int i) {
		switch(i) {
			case 0:
				return enemies;
			case 1:
				return towers;
			case 2:
				return bullets;
			default:
				return null;
		}
	}
	
	public Bitmap getBackground() {
		return stage.getBackground();
	}
}
