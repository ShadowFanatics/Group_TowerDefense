package group.objects;

import android.graphics.Bitmap;

public class Tower extends Object {
	private int attack;
	private float bulletSpeed;
	private float radius;
	private int attackInterval;
	private int timeCount = 0;
	public Tower(Bitmap bitmap, float x, float y, int type) {
		super(bitmap, x, y);
		this.attack = TowerTypeList.attack[type];
		this.bulletSpeed = TowerTypeList.bulletSpeed[type];
		this.radius = TowerTypeList.radius[type];
		this.attackInterval = TowerTypeList.attackInterval[type];
	}
	
	public boolean detect(Enemy enemy) {
		if ( timeCount == 0 ) {
			float x = Math.abs(this.getX() + this.getWidth() / 2 - enemy.getX() - enemy.getWidth() / 2 );
			float y = Math.abs(this.getY() + this.getHeight() / 2 - enemy.getY() - enemy.getHeight() / 2 );
			if ( x * x + y * y < radius * radius ) {
				timeCount++;
				return true;
			}
		}
		return false;
	}
	
	public void runInterval() {
		if ( timeCount != 0) {
			timeCount++;
			if ( timeCount >= attackInterval ) {
				timeCount = 0;
			}
		}
	}
	
	public int getAttack() {
		return attack;
	}
	
	public float getBulletSpeed() {
		return bulletSpeed;
	}
}