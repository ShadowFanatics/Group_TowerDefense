package group.objects;

import group.engine.Panel;
import android.graphics.Bitmap;

public class Bullet extends Object{
	private Enemy target;
	private float speed;
	private int attack;
	public Bullet(Bitmap bitmap, float x, float y, float speed, int attack, Enemy enemy) {
		super(bitmap, x, y);
		this.speed = speed;
		this.target = enemy;
		this.attack = attack;
		// TODO Auto-generated constructor stub
	}
	
	private void move(float offestX, float offestY) {
		super.setLocation(this.getX() + offestX, this.getY() + offestY);
	}
	
	public boolean moveToTarget() {
		float offestX = ( target.getX() + target.getWidth() / 2 ) - ( this.getX() + this.getWidth() / 2 );
		float offestY = ( target.getY() + target.getHeight() / 2 ) - ( this.getY() + this.getHeight() / 2 );
		if ( Math.abs(offestX) < target.getWidth() / 2 && Math.abs(offestY) < target.getHeight() / 2 ) {
			return true;
		}
		else {
			if ( offestX > speed ) {
				this.move(speed, 0);
			}
			else if ( offestX < -speed ) {
				this.move(-speed, 0);
			}
			else {
				this.move(offestX, 0);
			}
			if ( offestY > speed ) {
				this.move(0, speed);
			}
			else if ( offestY < -speed ) {
				this.move(0, -speed);
			}
			else {
				this.move(0, offestY);
			}
			return false;
		}
	}
	
	public int getTargetEnemyID() {
		return target.getID();
	}
	
	public int getAttack() {
		return attack;
	}
}
