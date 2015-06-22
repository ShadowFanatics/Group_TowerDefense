package group.objects;

import android.graphics.Bitmap;

public class Enemy extends Object{
	private int ID;
	private int startTime;
	private float moveSpeed;
	private int HP;
	private int type;
	private int walkPathID;
	private int hadGoPath = 0;
	public Enemy(Bitmap bitmap, float x, float y,int id, int type, int walkPathID) {
		super(bitmap, x, y);
		ID = id;
		this.type = type;
		this.walkPathID = walkPathID;
		moveSpeed = EnemyTypeList.moveSpeed[type];
		HP = EnemyTypeList.HP[type];
	}
	
	public void move(float offestX, float offestY) {
		super.setLocation(this.getX() + offestX, this.getY() + offestY);
	}
	
	public int getID() {
		return ID;
	}
	
	public boolean giveDemage(int demage) {
		HP -= demage;
		if ( HP < 0 ) {
			return true;
		}
		return false;
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public void addHadGoPath() {
		hadGoPath++;
	}
	
	public int getHadGoPath() {
		return hadGoPath;
	}
	
	public float getMoveSpeed() {
		return moveSpeed;
	}
	
	public int getWalkPathID() {
		return walkPathID;
	}
}
